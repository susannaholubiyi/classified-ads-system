package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.dtos.*;

public interface SellerServices {
    RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest);
    CreateAdResponse createAd(CreateAdRequest createAdRequest);

    CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest createSellerContactInfoRequest);

    Seller findUserBy(String username);

    EditAdResponse editAd(EditAdRequest editAdRequest);

    DeleteAdResponse deleteAd(DeleteAdRequest deleteAdRequest);
}
