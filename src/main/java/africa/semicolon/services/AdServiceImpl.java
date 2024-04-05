package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.exceptions.InvalidInputException;
import africa.semicolon.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.utils.GlobalHelpers.*;

@Service
public class AdServiceImpl implements AdServices {
    @Autowired
    private AdRepository adRepository;
    @Override
    public Ad createAd(CreateAdRequest createAdRequest) {
        validateIfEmpty(createAdRequest.getProductName());
        validateIfEmpty(createAdRequest.getProductDescription());
        validateIfNull(createAdRequest.getProductName());
        validateIfNull(createAdRequest.getProductDescription());
        validateIfNull(createAdRequest.getSellerName());
        if(isNumeric(createAdRequest.getProductPrice())) {
            Ad newAd = Mappers.mapCreateAd(createAdRequest);
            adRepository.save(newAd);
            return newAd;
        }
        throw new InvalidInputException(String.format("%s is an invalid amount", createAdRequest.getProductPrice()));
    }
    public List<Ad> viewAllAds(){
        return adRepository.findAll();
    }


}
