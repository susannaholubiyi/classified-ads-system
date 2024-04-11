package africa.semicolon.controllers;

import africa.semicolon.dtos.*;
import africa.semicolon.services.AdServices;
import africa.semicolon.services.BuyerService;
import africa.semicolon.services.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
        @RequestMapping("/api/v1")
public class AdSystemController {
    @Autowired
    private SellerServices sellerServices;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private AdServices adServices;
    @PostMapping("/register-seller")
    public ResponseEntity<?> registerSeller(@RequestBody RegisterSellerRequest registerSellerRequest){
        try {
           var response =  sellerServices.register(registerSellerRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/fill-contact-info")
    public ResponseEntity<?> fillInContactInformation(@RequestBody CreateSellerContactInfoRequest contactInfoRequest){
        try {
            var response =  sellerServices.createContactInfo(contactInfoRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create-ad")
    public ResponseEntity<?> createAd(@RequestBody CreateAdRequest createAdRequest){
        try {
            var response =  sellerServices.createAd(createAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);        }
    }
    @PostMapping("/edit-ad")
    public ResponseEntity<?> editAd(@RequestBody EditAdRequest editAdRequest){
        try {
            var response =  sellerServices.editAd(editAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);        }
    }
    @GetMapping("/find-seller")
    public ResponseEntity<?> findSeller(String username){
        try {
            var response =  sellerServices.findUserBy(username);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/register-buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerRequest registerBuyerRequest){
        try {
            var response =  buyerService.register(registerBuyerRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view-an-Ad")
    public ResponseEntity<?> viewAnAd(@RequestBody ViewAdRequest viewAdRequest){
        try {
            var response =  buyerService.viewOneParticularAdWith(viewAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view-all-Ads")
    public ResponseEntity<?> viewAllAds(){
        try {
            var response =  buyerService.viewAllAds();
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
