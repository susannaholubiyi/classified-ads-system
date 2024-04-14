package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.*;

import java.util.List;

public interface BuyerService {
    List<Ad> viewAllAds();

    ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest);

    RegisterBuyerResponse register(RegisterBuyerRequest registerBuyerRequest);

    ReviewAdResponse review(ReviewAdRequest reviewAdRequest);

    ViewContactInfoResponse viewSellerContactInfo(ViewContactInfoRequest viewContactInfoRequest);
}

