package africa.semicolon.utils;

import africa.semicolon.data.models.*;
import africa.semicolon.dtos.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Mappers {
    public static Seller mapRegisterSeller(RegisterSellerRequest registerSellerRequest){
        Seller seller = new Seller();
        seller.setUsername(registerSellerRequest.getUsername().toLowerCase().strip());
        seller.setPassword(registerSellerRequest.getPassword());

        return seller;
    }
public static SellerContactInformation mapSellerInfo(CreateSellerContactInfoRequest createSellerContactInfoRequest, Seller seller) {
        SellerContactInformation contactInformation = seller.getContactInformation();
        contactInformation.setSellerUsername(createSellerContactInfoRequest.getSellerUsername());
        contactInformation.setPhoneNumber(createSellerContactInfoRequest.getPhoneNumber());
        contactInformation.setEmailAddress(createSellerContactInfoRequest.getEmailAddress().toLowerCase());
        Address address = new Address();
        address.setHouseNo(createSellerContactInfoRequest.getHouseNo().toLowerCase());
        address.setStreet(createSellerContactInfoRequest.getStreet().toLowerCase());
        address.setCity(createSellerContactInfoRequest.getCity().toLowerCase());
        address.setState(createSellerContactInfoRequest.getState().toLowerCase());
        address.setCountry(createSellerContactInfoRequest.getCountry().toLowerCase());
        contactInformation.setAddress(address);
        return contactInformation;
    }
    public static CreateSellerContactInfoResponse mapSellerResponse(SellerContactInformation contactInformation){
        CreateSellerContactInfoResponse infoResponse = new CreateSellerContactInfoResponse();
        infoResponse.setSellerUsername(contactInformation.getSellerUsername());
        infoResponse.setPhoneNumber(contactInformation.getPhoneNumber());
        infoResponse.setEmailAddress(contactInformation.getEmailAddress());
        infoResponse.setHouseNo(contactInformation.getAddress().getHouseNo());
        infoResponse.setStreet(contactInformation.getAddress().getStreet());
        infoResponse.setCity(contactInformation.getAddress().getCity());
        infoResponse.setState(contactInformation.getAddress().getState());
        infoResponse.setCountry(contactInformation.getAddress().getCountry());
        return infoResponse;
    }

    public static RegisterSellerResponse mapRegisterSeller(Seller savedUser){
        RegisterSellerResponse registerSellerResponse = new RegisterSellerResponse();
        registerSellerResponse.setId(savedUser.getId());
        registerSellerResponse.setUsername(savedUser.getUsername().toLowerCase().strip());
        return registerSellerResponse;
    }

    public static Ad mapCreateAd(CreateAdRequest createAdRequest){
        Ad newAd = new Ad();
        newAd.setSellerName(createAdRequest.getSellerUsername().toLowerCase().strip());
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
        viewAdResponse.setProductPrice(ad.getProductPrice());

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
