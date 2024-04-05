package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.CreateAdResponse;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;
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
        Seller seller = findUserBy(createAdRequest.getSellerName());
        Ad createdAd = adServices.createAd(createAdRequest);
        seller.getAds().add(createdAd);
        sellerRepository.save(seller);
        return mapCreateAd(createdAd);
    }
    public Seller findUserBy(String username) {
        Optional<Seller> seller =  sellerRepository.findByUsername(username);
        if(seller.isEmpty()) throw new SellerDoesNotExistException(String.format("%s is not a registered seller, kindly register", username));
        return seller.get();
    }


    private void validateUserName(String username) {
        boolean usernameExists = sellerRepository.existsByUsername(username);
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered seller", username));
    }

}
