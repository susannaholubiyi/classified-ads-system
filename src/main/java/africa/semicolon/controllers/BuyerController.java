package africa.semicolon.controllers;

import africa.semicolon.dtos.ApiResponse;
import africa.semicolon.dtos.RegisterBuyerRequest;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewContactInfoRequest;
import africa.semicolon.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;
    @PostMapping("/register-buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerRequest registerBuyerRequest){
        try {
            var response =  buyerService.register(registerBuyerRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/find-seller")
//    public ResponseEntity<?> findSeller(String username){
//        try {
//            var response =  buyerService.findUserBy(username);
//            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.FOUND);
//        }catch (Exception e){
//            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
//        }
//
//    }
    @GetMapping("/view-an-Ad")
    public ResponseEntity<?> viewAnAd(@RequestBody ViewAdRequest viewAdRequest){
        try {
            var response =  buyerService.viewOneParticularAdWith(viewAdRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view-seller-contact-info")
    public ResponseEntity<?> viewSellerContactInfo(@RequestBody ViewContactInfoRequest viewContactInfoRequest){
        try {
            var response =  buyerService.viewSellerContactInfo(viewContactInfoRequest);
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
