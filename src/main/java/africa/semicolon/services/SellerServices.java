package africa.semicolon.services;

import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.CreateAdResponse;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;

public interface SellerServices {
    RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest);
    CreateAdResponse createAd(CreateAdRequest createAdRequest);
}
