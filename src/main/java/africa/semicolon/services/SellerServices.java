package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Seller;
import africa.semicolon.dtos.*;

import java.util.List;

public interface SellerServices {
    List<Ad> viewAllAds();

    RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest);
    CreateAdResponse createAd(CreateAdRequest createAdRequest);

    CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest createSellerContactInfoRequest);

    Seller findUserBy(String username);

    EditAdResponse editAd(EditAdRequest editAdRequest);

    DeleteAdResponse deleteAd(DeleteAdRequest deleteAdRequest);
}
