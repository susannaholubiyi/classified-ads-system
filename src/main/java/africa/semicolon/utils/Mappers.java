package africa.semicolon.utils;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.CreateAdResponse;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static africa.semicolon.utils.GlobalHelpers.validatePhoneNumber;


public class Mappers {
    public static Seller map(RegisterSellerRequest registerSellerRequest){
        Seller seller = new Seller();
        seller.setUsername(registerSellerRequest.getUsername().toLowerCase().strip());
        seller.setPassword(registerSellerRequest.getPassword().toLowerCase().strip());
        return seller;
    }
public static SellerContactInformation mapSellerInfo(RegisterSellerRequest registerSellerRequest) {
        SellerContactInformation contactInformation = new SellerContactInformation();
        validatePhoneNumber(registerSellerRequest.getPhoneNumber().strip());
        contactInformation.setPhoneNumber(registerSellerRequest.getPhoneNumber().strip());
        contactInformation.setEmailAddress(registerSellerRequest.getEmailAddress().toLowerCase().strip());
        contactInformation.setAddress(registerSellerRequest.getAddress().toLowerCase().strip());

        return contactInformation;
    }

    public static RegisterSellerResponse map (Seller savedUser){
        RegisterSellerResponse registerSellerResponse = new RegisterSellerResponse();
        registerSellerResponse.setId(savedUser.getId());
        registerSellerResponse.setUsername(savedUser.getUsername().toLowerCase().strip());
        return registerSellerResponse;
    }
    public static Ad mapCreateAd(CreateAdRequest createAdRequest){
        Ad newAd = new Ad();
        newAd.setSellerName(createAdRequest.getSellerName().toLowerCase().strip());
        newAd.setProductName(createAdRequest.getProductName().strip());
        newAd.setProductDescription(createAdRequest.getProductDescription().strip());
        newAd.setProductPrice(createAdRequest.getProductPrice());
        return newAd;
    }
    public static CreateAdResponse mapCreateAd(Ad createdAd){
        CreateAdResponse createAdResponse = new CreateAdResponse();
        createAdResponse.setId(createdAd.getId());
        createAdResponse.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return createAdResponse;
    }
}
