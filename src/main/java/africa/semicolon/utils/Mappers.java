package africa.semicolon.utils;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.CreateAdResponse;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Mappers {
    public static Seller map(RegisterSellerRequest registerSellerRequest){
        Seller seller = new Seller();
        seller.setUsername(registerSellerRequest.getUsername());
        seller.setPassword(registerSellerRequest.getPassword());
        return seller;
    }
    public static RegisterSellerResponse map (Seller savedUser){
        RegisterSellerResponse registerSellerResponse = new RegisterSellerResponse();
        registerSellerResponse.setId(savedUser.getId());
        registerSellerResponse.setUsername(savedUser.getUsername());
        return registerSellerResponse;
    }
    public static Ad mapCreateAd(CreateAdRequest createAdRequest){
        Ad newAd = new Ad();
        newAd.setSellerName(createAdRequest.getSellerName());
        newAd.setProductName(createAdRequest.getProductName());
        newAd.setProductDescription(createAdRequest.getProductDescription());
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
