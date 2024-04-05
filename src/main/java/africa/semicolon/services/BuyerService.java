package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.RegisterBuyerRequest;
import africa.semicolon.dtos.RegisterBuyerResponse;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewAdResponse;

import java.util.List;

public interface BuyerService {
    List<Ad> viewAllAds();

    ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest);

    RegisterBuyerResponse register(RegisterBuyerRequest registerBuyerRequest);
}

