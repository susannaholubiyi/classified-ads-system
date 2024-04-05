package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewAdResponse;
import africa.semicolon.exceptions.AdNotFoundException;
import africa.semicolon.exceptions.BuyerDoesNotExistException;
import africa.semicolon.exceptions.InvalidInputException;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import africa.semicolon.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.utils.GlobalHelpers.*;
import static africa.semicolon.utils.Mappers.mapViewAdResponse;

@Service
public class AdServiceImpl implements AdServices {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    BuyerRepository buyerRepository;
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

    @Override
    public ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest) {
        Optional<Buyer> buyer = buyerRepository.findByUsername(viewAdRequest.getBuyerName().toLowerCase().strip());
        String buyerName = buyer.get().getName();
        validateBuyer(buyerName, viewAdRequest.getBuyerName()) ;
        validateRequests(viewAdRequest);
        Optional<Seller> sellerOptional = sellerRepository.findByUsername(viewAdRequest.getSellerName().toLowerCase().strip());
        checkIfSellerExistsWith(viewAdRequest, sellerOptional);
        Optional<Ad> adOptional = getAdFrom(viewAdRequest, sellerOptional);
        findAd(viewAdRequest, adOptional);
        Ad ad = adOptional.get();
        ad.setNumberOfViews(ad.getNumberOfViews() + 1);
        adRepository.save(ad);

        return mapViewAdResponse(ad);

    }

    private void validateBuyer(String storedBuyerName, String provideBuyerName) {
        if (!storedBuyerName.equalsIgnoreCase(provideBuyerName))
            throw new NullPointerException(String.format("%s is not a registered buyer",provideBuyerName));
    }

    private static void findAd(ViewAdRequest viewAdRequest, Optional<Ad> adOptional) {
        if (adOptional.isEmpty()) {
            throw new AdNotFoundException("Ad not found with ID: " + viewAdRequest.getAdId());
        }
    }

    private static Optional<Ad> getAdFrom(ViewAdRequest viewAdRequest, Optional<Seller> sellerOptional) {
        Seller seller = sellerOptional.get();
        Optional<Ad> adOptional;
        adOptional = seller.getAds().stream()
                .filter(ad -> ad.getId().equals(viewAdRequest.getAdId()))
                .findFirst();
        return adOptional;
    }

    private static void checkIfSellerExistsWith(ViewAdRequest viewAdRequest, Optional<Seller> sellerOptional) {
        if (sellerOptional.isEmpty()) {
            throw new SellerDoesNotExistException(String.format("Seller not found with username: " + viewAdRequest.getSellerName()));
        }
    }

    private static void validateRequests(ViewAdRequest viewAdRequest) {
        validateIfEmpty(viewAdRequest.getSellerName());
        validateIfNull(viewAdRequest.getSellerName());
        validateIfEmpty(viewAdRequest.getAdId());
        validateIfNull(viewAdRequest.getAdId());
    }

    public List<Ad> viewAllAds(){
        return adRepository.findAll();
    }


}
