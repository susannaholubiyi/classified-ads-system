package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.dtos.CreateAdRequest;

public interface AdServices {
    Ad createAd(CreateAdRequest createAdRequest);
}
