package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.AdNotFoundException;
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
    private AdServices adServices;
    @Autowired
    private SellerContactInfoRepository contactInfoRepository;

    @Override
    public RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest) {
        registerSellerRequest.setUsername(registerSellerRequest.getUsername().toLowerCase());
        validateUserName(registerSellerRequest.getUsername());
        validateIfNull(registerSellerRequest.getUsername());
        validateIfNull(registerSellerRequest.getPassword());
        validateIfNull(registerSellerRequest.getPhoneNumber());
        validateIfNull(registerSellerRequest.getEmailAddress());
        validateIfNull(registerSellerRequest.getAddress());
        validateIfEmpty(registerSellerRequest.getUsername());
        validateIfEmpty(registerSellerRequest.getPassword());
        validateIfEmpty(registerSellerRequest.getPhoneNumber());
        validateIfEmpty(registerSellerRequest.getEmailAddress());
        validateIfEmpty(registerSellerRequest.getAddress());
        SellerContactInformation contactInformation = mapSellerInfo(registerSellerRequest);
        Seller seller = mapRegisterSeller(registerSellerRequest);
        seller.setSellerContactInfo(contactInformation);
        seller.setLocked(false);
        contactInfoRepository.save(contactInformation);
        sellerRepository.save(seller);
        return mapRegisterSeller(seller);

    }

    @Override
    public CreateAdResponse createAd(CreateAdRequest createAdRequest) {
        Seller seller = findUserBy(createAdRequest.getSellerName().toLowerCase().strip());
        Ad createdAd = adServices.createAd(createAdRequest);
        seller.getAds().add(createdAd);
        sellerRepository.save(seller);
        return mapCreateAd(createdAd);
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


    private void validateUserName(String username) {
        boolean usernameExists = sellerRepository.existsByUsername(username.toLowerCase().strip());
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered seller", username));
    }
    public static void validateAd(EditAdRequest editAdRequest, Optional<Ad> adOptional) {
        if (adOptional.isEmpty()) {
            throw new AdNotFoundException("Ad not found with ID: " + editAdRequest.getAdId());
        }
    }

}
