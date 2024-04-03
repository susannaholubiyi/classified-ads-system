package africa.semicolon.utils;

import africa.semicolon.data.models.Seller;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;


public class Mappers {
    public static Seller map(RegisterSellerRequest registerSellerRequest){
        Seller seller = new Seller();
        seller.setFirstName(registerSellerRequest.getFirstName());
        seller.setLastName(registerSellerRequest.getLastName());
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
}
