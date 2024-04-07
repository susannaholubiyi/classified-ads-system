package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.EditAdRequest;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewAdResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdServices {
    Ad createAd(CreateAdRequest createAdRequest);

    Ad editAd(EditAdRequest editAdRequest);

    ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest);

}
