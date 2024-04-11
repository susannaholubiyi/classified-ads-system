//package africa.semicolon.controllers;
//
//import africa.semicolon.data.models.Ad;
//import africa.semicolon.data.models.Seller;
//import africa.semicolon.data.repositories.BuyerRepository;
//import africa.semicolon.data.repositories.SellerRepository;
//import africa.semicolon.dtos.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class AdSystemControllerTest {
//    @Autowired
//    private AdSystemController adSystemController;
//    @Autowired
//    private SellerRepository sellerRepository;
//    @Autowired
//    private BuyerRepository buyerRepository;
//    @BeforeEach
//    public void setUp(){
//        sellerRepository.deleteAll();
//        buyerRepository.deleteAll();
//    }
//    @Test
//    public void registerSellerTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("seyi");
//        registerSellerRequest.setPassword("1111");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//    }
//    @Test
//    public void registerSameSellerTwice_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("seyi");
//        registerSellerRequest.setPassword("1111");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithEmptyUsername_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("");
//        registerSellerRequest.setPassword("1111");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithNullUsername_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername(null);
//        registerSellerRequest.setPassword("1111");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithEmptyPassword_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithInvalidPhoneNumber_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("-o812h$3233");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithInvalidPhoneNumberLength_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("081098");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithEmptyEmailAddress_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registerSellerWithEmptyAddress_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.registerSeller(registerSellerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCreateAd_CreatedRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand Bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCreateAdWithEmptyProductName_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCreateAdWithDifferentSellerName_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("joy");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCreateAdWithEmptyProductDescription_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand bag");
//        createAdRequest.setProductDescription("");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCreateAdWithEmptyPrice_badRequestTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void notRegisteredSellerCreateAd_badRequestTest(){
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("30_000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.createAd(createAdRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
////    @Test
////    public void registereSeller_createAdUsingDifferentNameCasing_createdRequestTest(){
////        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
////        registerSellerRequest.setUsername("suzu");
////        registerSellerRequest.setPassword("password");
////        registerSellerRequest.setPhoneNumber("08674532145");
////        registerSellerRequest.setEmailAddress("email@address");
////        registerSellerRequest.setAddress("26 sholanke lagos");
////
////        var response = adSystemController.registerSeller(registerSellerRequest);
////        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//
////        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
////        registerSellerRequest.setUsername("seyi");
////        registerSellerRequest.setPassword("1111");
////        registerSellerRequest.setPhoneNumber("08123232323");
////        registerSellerRequest.setEmailAddress("email@address");
////        registerSellerRequest.setAddress("26 sholanke lagos");
////
////        var response = adSystemController.registerSeller(registerSellerRequest);
////        assertEquals(HttpStatus.CREATED,response.getStatusCode());
////
////        CreateAdRequest createAdRequest = new CreateAdRequest();
////        createAdRequest.setProductName("Hand bag");
////        createAdRequest.setProductDescription("product description");
////        createAdRequest.setProductPrice("20_000");
////        createAdRequest.setSellerName("suzu");
////        adSystemController.createAd(createAdRequest);
////
////        var response = adSystemController.createAd(createAdRequest);
////        assertEquals(HttpStatus.CREATED,response.getStatusCode());
////    }
//    @Test
//    public void buyerCanRegisterTest(){
//        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
//        registerBuyerRequest.setName("Dayo");
//        registerBuyerRequest.setUsername("dee");
//        var response = adSystemController.registerBuyer(registerBuyerRequest);
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//
//    }
//    @Test
//    public void buyerRegistersWithAlreadyExistingUsername_badRequestTest(){
//        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
//        registerBuyerRequest.setName("buyer name");
//        registerBuyerRequest.setUsername("username");
//        adSystemController.registerBuyer(registerBuyerRequest);
//
//        var response = adSystemController.registerBuyer(registerBuyerRequest);
//        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
//    @Test
//    public void registeredSellerCanBeFoundByUsername_sellerIsFoundTest(){
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        var response = adSystemController.findSeller("suzu");
//        assertEquals(HttpStatus.FOUND,response.getStatusCode());
//    }
//    @Test
//    public void findNonRegisteredSellerByUsername_sellerIsNotFoundTest(){
//        var response = adSystemController.findSeller("suzu");
//        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
//    }
//    @Test
//    public void viewAllAdsTest() {
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand Bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        CreateAdRequest createAdRequest2 = new CreateAdRequest();
//        createAdRequest.setProductName("shoe");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("10000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        var response = adSystemController.viewAllAds();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//    @Test
//    public void viewOneParticularAdsTest() {
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("suzu");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08109833333");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("26 sholanke lagos");
//        adSystemController.registerSeller(registerSellerRequest);
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("Hand Bag");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("20000");
//        createAdRequest.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest);
//
//        CreateAdRequest createAdRequest2 = new CreateAdRequest();
//        createAdRequest2.setProductName("shoe");
//        createAdRequest2.setProductDescription("product description");
//        createAdRequest2.setProductPrice("10000");
//        createAdRequest2.setSellerName("suzu");
//        adSystemController.createAd(createAdRequest2);
//
//        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
//        registerBuyerRequest.setName("Dayo");
//        registerBuyerRequest.setUsername("dee");
//        adSystemController.registerBuyer(registerBuyerRequest);
//
//        ViewAdRequest viewAdRequest = new ViewAdRequest();
//        viewAdRequest.setBuyerUsername("dee");
//        viewAdRequest.setSellerName("suzu");
//        Optional<Seller> seller = sellerRepository.findByUsername("suzu");
//        Ad ad = seller.get().getAds().get(0);
//        viewAdRequest.setAdId(ad.getId());
//
//        var response = adSystemController.viewAnAd(viewAdRequest);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//}