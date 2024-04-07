package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.dtos.*;

public interface SellerServices {
    RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest);
    CreateAdResponse createAd(CreateAdRequest createAdRequest);
    Seller findUserBy(String username);

    EditAdResponse editAd(EditAdRequest editAdRequest);
}
