package africa.semicolon.utils;

import africa.semicolon.data.models.*;
import africa.semicolon.dtos.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static africa.semicolon.utils.GlobalHelpers.validatePhoneNumber;


public class Mappers {
    public static Seller mapRegisterSeller(RegisterSellerRequest registerSellerRequest){
        Seller seller = new Seller();
        seller.setUsername(registerSellerRequest.getUsername().toLowerCase().strip());
        seller.setPassword(registerSellerRequest.getPassword().toLowerCase().strip());
        return seller;
    }
public static SellerContactInformation mapSellerInfo(RegisterSellerRequest registerSellerRequest) {
        SellerContactInformation contactInformation = new SellerContactInformation();
        validatePhoneNumber(registerSellerRequest.getPhoneNumber().strip());
        contactInformation.setPhoneNumber(registerSellerRequest.getPhoneNumber().strip());
        contactInformation.setEmailAddress(registerSellerRequest.getEmailAddress().toLowerCase().strip());
        contactInformation.setAddress(registerSellerRequest.getAddress().toLowerCase().strip());

        return contactInformation;
    }

    public static RegisterSellerResponse mapRegisterSeller(Seller savedUser){
        RegisterSellerResponse registerSellerResponse = new RegisterSellerResponse();
        registerSellerResponse.setId(savedUser.getId());
        registerSellerResponse.setUsername(savedUser.getUsername().toLowerCase().strip());
        return registerSellerResponse;
    }
    public static Ad mapCreateAd(CreateAdRequest createAdRequest){
        Ad newAd = new Ad();
        newAd.setSellerName(createAdRequest.getSellerName().toLowerCase().strip());
        newAd.setProductName(createAdRequest.getProductName().strip());
        newAd.setProductDescription(createAdRequest.getProductDescription().strip());
        newAd.setProductPrice(createAdRequest.getProductPrice());
        return newAd;
    }
    public static CreateAdResponse mapCreateAd(Ad createdAd){
        CreateAdResponse createAdResponse = new CreateAdResponse();
        createAdResponse.setId(createdAd.getId());
        createAdResponse.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return createAdResponse;
    }
    public static Ad mapEditAd(EditAdRequest editAdRequest, Ad ad){
        ad.setProductName(editAdRequest.getNewProductName());
        ad.setProductDescription(editAdRequest.getNewProductDescription());
        ad.setProductPrice(editAdRequest.getNewProductPrice());
        return ad;
    }
    public static EditAdResponse mapEditAd(Ad ad){
        EditAdResponse editAdResponse = new EditAdResponse();
        editAdResponse.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return editAdResponse;
    }
    public static Buyer mapRegisterBuyer(RegisterBuyerRequest registerBuyerRequest){
        Buyer buyer = new Buyer();
        buyer.setUsername(registerBuyerRequest.getUsername().toLowerCase().strip());
        buyer.setName(registerBuyerRequest.getName().toLowerCase().strip());
        return buyer;
    }
    public static RegisterBuyerResponse mapRegisterSeller(Buyer savedBuyer){
        RegisterBuyerResponse registerBuyerResponse = new RegisterBuyerResponse();
        registerBuyerResponse.setId(savedBuyer.getId());
        registerBuyerResponse.setUsername(savedBuyer.getUsername().toLowerCase().strip());
        return registerBuyerResponse;
    }

    public static ViewAdResponse mapViewAdResponse(Ad ad) {
        ViewAdResponse viewAdResponse = new ViewAdResponse();
        viewAdResponse.setProductName(ad.getProductName());
        viewAdResponse.setSellerName(ad.getSellerName());
        viewAdResponse.setDateCreated(ad.getDateCreated());
        viewAdResponse.setProductDescription(ad.getProductDescription());
        viewAdResponse.setNumberOfViews(ad.getNumberOfViews());
        viewAdResponse.setSellerAddress(ad.getSellerInfo().getAddress());
        viewAdResponse.setSellerPhoneNumber(ad.getSellerInfo().getPhoneNumber());
        viewAdResponse.setSellerEmailAddress(ad.getSellerInfo().getEmailAddress());
        viewAdResponse.setReviews(ad.getReviews());
    return viewAdResponse;
    }
    public static Review mapReviewAdRequest(ReviewAdRequest reviewAdRequest){
        Review review = new Review();
        review.setContent(reviewAdRequest.getReviewBody());
        review.setReviewerName(reviewAdRequest.getBuyerUsername());
        return review;
    }
    public static ReviewAdResponse mapReviewAdRequest(Review review){
        ReviewAdResponse reviewAdResponse = new ReviewAdResponse();
        reviewAdResponse.setReviewerName(review.getReviewerName());
        reviewAdResponse.setContent(review.getContent());
        reviewAdResponse.setDateCreated(review.getDateCreated());
        return reviewAdResponse;
    }
}
