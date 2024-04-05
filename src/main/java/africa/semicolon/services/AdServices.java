package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewAdResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdServices {
    Ad createAd(CreateAdRequest createAdRequest);
    ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest);

}
