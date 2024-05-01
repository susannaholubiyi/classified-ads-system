package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.AdNotFoundException;
import africa.semicolon.exceptions.InvalidInputException;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import africa.semicolon.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.utils.GlobalHelpers.*;
import static africa.semicolon.utils.Mappers.mapEditAd;
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
        validateIfNull(createAdRequest.getSellerUsername());
        if(isNumeric(createAdRequest.getProductPrice())) {
            Ad newAd = Mappers.mapCreateAd(createAdRequest);
            adRepository.save(newAd);
            return newAd;
        }
        throw new InvalidInputException(String.format("%s is an invalid amount", createAdRequest.getProductPrice()));
    }



    @Override
    public Ad editAd(EditAdRequest editAdRequest){
        validateEditAdRequest(editAdRequest);
        Seller seller = findUserBy(editAdRequest.getSellerUsername().toLowerCase().strip());
        Optional<Ad> optionalAd = getAd(editAdRequest,seller);
        if (optionalAd.isEmpty()) {
            throw new AdNotFoundException("Ad not found with ID: " + editAdRequest.getAdId());
        }        Ad ad = optionalAd.get();
        if (isNumeric(editAdRequest.getNewProductPrice())) {
            Ad editedAd = mapEditAd(editAdRequest, ad);
            adRepository.save(editedAd);
            return editedAd;
        }
        throw new InvalidInputException(String.format("%s is an invalid amount", editAdRequest.getNewProductPrice()));
    }

    private static void validateEditAdRequest(EditAdRequest editAdRequest) {
        validateIfEmpty(editAdRequest.getNewProductPrice());
        validateIfEmpty(editAdRequest.getNewProductName());
        validateIfEmpty(editAdRequest.getNewProductDescription());
        validateIfEmpty(editAdRequest.getSellerUsername());
        validateIfEmpty(editAdRequest.getAdId());
        validateIfNull(editAdRequest.getSellerUsername());
        validateIfNull(editAdRequest.getNewProductName());
        validateIfNull(editAdRequest.getNewProductPrice());
        validateIfNull(editAdRequest.getNewProductDescription());
    }

    private Seller findUserBy(String username) {
        Optional<Seller> seller =  sellerRepository.findByUsername(username.toLowerCase().strip());
        if(seller.isEmpty()) throw new SellerDoesNotExistException(String.format("%s is not a registered seller, kindly register", username));
        sellerRepository.save(seller.get());
        return seller.get();
    }

    @Override
    public ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest) {
        Optional<Buyer> buyer = buyerRepository.findByUsername(viewAdRequest.getBuyerUsername().toLowerCase().strip());
        String buyerUsername = buyer.get().getUsername();
        validateBuyer(buyerUsername, viewAdRequest.getBuyerUsername()) ;
        validateRequests(viewAdRequest);
        Optional<Seller> sellerOptional = sellerRepository.findByUsername(viewAdRequest.getSellerName().toLowerCase().strip());
        checkIfSellerExistsWith(viewAdRequest, sellerOptional);
        Optional<Ad> adOptional = getAdFrom(viewAdRequest, sellerOptional);
        validateAd(viewAdRequest.getAdId(), adOptional);
        Ad ad = adOptional.get();
        ad.setNumberOfViews(ad.getNumberOfViews() + 1);
        adRepository.save(ad);

        return mapViewAdResponse(ad);
    }

    @Override
    public DeleteAdResponse deleteAd(DeleteAdRequest deleteAdRequest) {
        validateDeleteAdRequests(deleteAdRequest);
        Optional<Seller> sellerOptional = sellerRepository.findByUsername(deleteAdRequest.getSellerUsername().toLowerCase().strip());
        if (sellerOptional.isEmpty()) {
            throw new SellerDoesNotExistException(String.format("Seller not found with username: " + deleteAdRequest.getSellerUsername()));
        }
        Seller seller = sellerOptional.get();
        Optional<Ad> adOptional = getAdToBeDeletedFrom(deleteAdRequest, sellerOptional);
        validateAd(deleteAdRequest.getAdId(), adOptional);
        Ad ad = adOptional.get();
        seller.getAds().remove(ad);
        adRepository.delete(ad);
        seller.getAds().remove(ad);
        sellerRepository.save(seller);
        return new DeleteAdResponse();
    }

    private static void validateDeleteAdRequests(DeleteAdRequest deleteAdRequest) {
        validateIfEmpty(deleteAdRequest.getSellerUsername());
        validateIfEmpty(deleteAdRequest.getAdId());
        validateIfNull(deleteAdRequest.getSellerUsername());
        validateIfNull(deleteAdRequest.getAdId());
    }

    private void validateBuyer(String storedBuyerUsername, String provideBuyerUsername) {
        if (!storedBuyerUsername.equalsIgnoreCase(provideBuyerUsername))
            throw new NullPointerException(String.format("%s is not a registered buyer",provideBuyerUsername));
    }

    private static void validateAd(String adId, Optional<Ad> adOptional) {
        if (adOptional.isEmpty()) {
            throw new AdNotFoundException("Ad not found with ID: " + adId);
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
    private static Optional<Ad> getAdToBeDeletedFrom(DeleteAdRequest deleteAdRequest, Optional<Seller> sellerOptional) {
        Seller seller = sellerOptional.get();
        Optional<Ad> adOptional;
        adOptional = seller.getAds().stream()
                .filter(ad -> ad.getId().equals(deleteAdRequest.getAdId()))
                .findFirst();
        return adOptional;
    }
    private static Optional<Ad> getAd(EditAdRequest editAdRequest, Seller seller) {
        Optional<Ad> adOptional;
        adOptional = seller.getAds().stream()
                .filter(ad -> ad.getId().equals(editAdRequest.getAdId()))
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
@Override
    public List<Ad> viewAllAds(){
        return adRepository.findAll();
    }


}
