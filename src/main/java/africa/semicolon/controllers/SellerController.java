package africa.semicolon.controllers;

import africa.semicolon.dtos.*;
import africa.semicolon.services.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {
    @Autowired
    private SellerServices sellerServices;

    @GetMapping("/view-all-Ads")
    public ResponseEntity<?> viewAllAds(){
        try {
            var response =  sellerServices.viewAllAds();
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
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
    @PatchMapping("/edit-ad")
    public ResponseEntity<?> editAd(@RequestBody EditAdRequest editAdRequest){
        try {
            var response =  sellerServices.editAd(editAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);        }
    }
    @DeleteMapping("/delete-ad")
    public ResponseEntity<?> deleteAd(@RequestBody DeleteAdRequest deleteAdRequest){
        try {
            var response =  sellerServices.deleteAd(deleteAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);        }
    }


}
