package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.IllegalArgumentException;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.utils.GlobalHelpers.validateIfEmpty;
import static africa.semicolon.utils.GlobalHelpers.validateIfNull;
import static africa.semicolon.utils.Mappers.*;

@Service
public class SellerServicesImpl implements SellerServices{
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerContactInfoRepository sellerContactInfoRepository;
    @Autowired
    private AdServices adServices;
    @Autowired
    private SellerContactInfoServices sellerContactInfoServices;


    @Override
    public RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest) {
        registerSellerRequest.setUsername(registerSellerRequest.getUsername().toLowerCase());
        validateUserName(registerSellerRequest.getUsername());
        validateIfNull(registerSellerRequest.getUsername());
        validateIfNull(registerSellerRequest.getPassword());
        validateIfEmpty(registerSellerRequest.getUsername());
        validateIfEmpty(registerSellerRequest.getPassword());
        Seller seller = mapRegisterSeller(registerSellerRequest);
        seller.setLocked(false);
        SellerContactInformation contactInformation = sellerContactInfoServices.createSellerInformationForm();
        seller.setContactInformation(contactInformation);
        sellerRepository.save(seller);
        return mapRegisterSeller(seller);

    }

    @Override
    public CreateAdResponse createAd(CreateAdRequest createAdRequest) {
        Seller seller = findUserBy(createAdRequest.getSellerUsername().toLowerCase().strip());
        SellerContactInformation contactInformation = sellerContactInfoRepository.findBySellerUsername(createAdRequest.getSellerUsername().toLowerCase().strip());
        checkIfIsPresent(contactInformation.getSellerUsername());
        checkIfIsPresent(contactInformation.getEmailAddress());
        checkIfIsPresent(contactInformation.getPhoneNumber());
        checkIfIsPresent(contactInformation.getAddress().getHouseNo());
        checkIfIsPresent(contactInformation.getAddress().getStreet());
        checkIfIsPresent(contactInformation.getAddress().getCity());
        checkIfIsPresent(contactInformation.getAddress().getState());
        checkIfIsPresent(contactInformation.getAddress().getCountry());
        Ad createdAd = adServices.createAd(createAdRequest);
        seller.getAds().add(createdAd);
        sellerRepository.save(seller);
        return mapCreateAd(createdAd);
    }
    @Override
    public CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest createSellerContactInfoRequest){
        Optional<Seller> optionalSeller = sellerRepository.findByUsername(createSellerContactInfoRequest.getSellerUsername());
        if (optionalSeller.isEmpty())throw new IllegalArgumentException("Username is empty");
        Seller seller = optionalSeller.get();
       CreateSellerContactInfoResponse response = sellerContactInfoServices.createContactInfo(createSellerContactInfoRequest, seller);
        sellerRepository.save(seller);
        return response;
    }



    @Override
    public Seller findUserBy(String username) {
        Optional<Seller> seller =  sellerRepository.findByUsername(username.toLowerCase().strip());
        if(seller.isEmpty()) throw new SellerDoesNotExistException(String.format("%s is not a registered seller, kindly register", username));
        sellerRepository.save(seller.get());
        return seller.get();
    }

    @Override
    public EditAdResponse editAd(EditAdRequest editAdRequest) {
        Seller seller = findUserBy(editAdRequest.getSellerUsername().toLowerCase().strip());
        Ad ad = adServices.editAd(editAdRequest);
        sellerRepository.save(seller);

        return mapEditAd(ad);
    }

    @Override
    public DeleteAdResponse deleteAd(DeleteAdRequest deleteAdRequest) {
        return adServices.deleteAd(deleteAdRequest);
    }

    private void checkIfIsPresent(String request){
       if (request == null) throw new NullPointerException(String.format("%s you don't have a contact info, kindly create one", request));
    }


    private void validateUserName(String username) {
        boolean usernameExists = sellerRepository.existsByUsername(username.toLowerCase().strip());
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered seller", username));
    }

}
