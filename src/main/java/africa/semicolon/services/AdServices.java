package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdServices {
    Ad createAd(CreateAdRequest createAdRequest);

    Ad editAd(EditAdRequest editAdRequest);

    ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest);

    DeleteAdResponse deleteAd(DeleteAdRequest deleteAdRequest);

    List<Ad> viewAllAds();
}

