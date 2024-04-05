package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.CreateAdRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdServices {
    Ad createAd(CreateAdRequest createAdRequest);
}
