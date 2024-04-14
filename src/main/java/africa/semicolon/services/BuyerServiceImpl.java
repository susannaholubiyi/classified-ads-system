package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.models.Review;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.ReviewRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.AdNotFoundException;
import africa.semicolon.exceptions.BuyerDoesNotExistException;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.utils.GlobalHelpers.validateIfEmpty;
import static africa.semicolon.utils.GlobalHelpers.validateIfNull;
import static africa.semicolon.utils.Mappers.*;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    AdServiceImpl adServices;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    AdRepository adRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    private SellerContactInfoServices contactInfoServices;
    @Override
    public List<Ad> viewAllAds() {
        return  adServices.viewAllAds();

    }

    @Override
    public ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest) {

      return adServices.viewOneParticularAdWith(viewAdRequest);
    }

    @Override
    public RegisterBuyerResponse register(RegisterBuyerRequest registerBuyerRequest) {
        registerBuyerRequest.setUsername(registerBuyerRequest.getUsername().toLowerCase());
        validateUserName(registerBuyerRequest.getUsername());
        validateIfNull(registerBuyerRequest.getUsername());
        validateIfEmpty(registerBuyerRequest.getUsername());
        validateIfNull(registerBuyerRequest.getName());
        validateIfEmpty(registerBuyerRequest.getName());
        Buyer buyer = mapRegisterBuyer(registerBuyerRequest);
        buyer.setLocked(false);
        buyerRepository.save(buyer);
        return mapRegisterSeller(buyer);
    }

    @Override
    public ReviewAdResponse review(ReviewAdRequest reviewAdRequest) {
        validateReviewRequest(reviewAdRequest);
        Optional<Buyer> buyerOpt = buyerRepository.findByUsername(reviewAdRequest.getBuyerUsername());
        if (buyerOpt.isEmpty()) {
            throw new BuyerDoesNotExistException("Buyer not found with username: " + reviewAdRequest.getBuyerUsername());
        }
        Buyer buyer = buyerOpt.get();
        String buyerUsername = buyer.getUsername();
        validateIfRegisteredBuyer(buyerUsername, reviewAdRequest.getBuyerUsername());
        Optional<Seller> sellerOptional = sellerRepository.findByUsername(reviewAdRequest.getSellerUsername().toLowerCase().strip());
        checkIfSellerExistsWith(reviewAdRequest, sellerOptional);
        Optional<Ad> adOptional = getAdFrom(reviewAdRequest, sellerOptional);
        validateAd(reviewAdRequest, adOptional);
        Review review = mapReviewAdRequest(reviewAdRequest);
       Ad ad =  adOptional.get();
        ad.getReviews().add(review);
        System.out.println(ad);
        adRepository.save(ad);
        reviewRepository.save(review);
        return mapReviewAdRequest(review);
    }

    @Override
    public ViewContactInfoResponse viewSellerContactInfo(ViewContactInfoRequest viewContactInfoRequest) {
        return contactInfoServices.viewSellerContactInfo(viewContactInfoRequest);
    }


    private static void checkIfSellerExistsWith(ReviewAdRequest reviewAdRequest, Optional<Seller> sellerOptional) {
        if (sellerOptional.isEmpty()) {
            throw new SellerDoesNotExistException(String.format("Seller not found with username: " + reviewAdRequest.getSellerUsername()));
        }
    }
    private static Optional<Ad> getAdFrom(ReviewAdRequest reviewAdRequest, Optional<Seller> sellerOptional) {
        Seller seller = sellerOptional.get();
        Optional<Ad> adOptional;
        adOptional = seller.getAds().stream()
                .filter(ad -> ad.getId().equals(reviewAdRequest.getAdId()))
                .findFirst();
        return adOptional;
    }
    private static void validateAd(ReviewAdRequest reviewAdRequest, Optional<Ad> adOptional) {
        if (adOptional.isEmpty()) {
            throw new AdNotFoundException("Ad not found with ID: " + reviewAdRequest.getAdId());
        }
    }

    private static void validateReviewRequest(ReviewAdRequest reviewAdRequest) {
        validateIfNull(reviewAdRequest.getSellerUsername());
        validateIfNull(reviewAdRequest.getSellerUsername());
        validateIfNull(reviewAdRequest.getAdId());
        validateIfNull(reviewAdRequest.getReviewBody());
        validateIfEmpty(reviewAdRequest.getSellerUsername());
        validateIfEmpty(reviewAdRequest.getSellerUsername());
        validateIfEmpty(reviewAdRequest.getAdId());
        validateIfEmpty(reviewAdRequest.getReviewBody());
    }


    private void validateUserName(String username) {
        boolean usernameExists = buyerRepository.existsByUsername(username);
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered buyer", username));
    }
    private void validateIfRegisteredBuyer(String storedBuyerUsername, String provideBuyerUsername) {
        if (!storedBuyerUsername.equalsIgnoreCase(provideBuyerUsername.toLowerCase().strip()))
            throw new NullPointerException(String.format("%s is not a registered buyer",provideBuyerUsername));
    }
}
