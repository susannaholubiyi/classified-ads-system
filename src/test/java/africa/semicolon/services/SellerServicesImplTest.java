package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.*;
import africa.semicolon.exceptions.IllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SellerServicesImplTest {
    @Autowired
    private SellerServices sellerServices;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerContactInfoServices contactInfoServices;
    @Autowired
    private SellerContactInfoRepository contactInfoRepository;
    @BeforeEach
    public void setUp(){
        sellerRepository.deleteAll();
        adRepository.deleteAll();
        buyerRepository.deleteAll();
        contactInfoRepository.deleteAll();
    }

    @Test
    public void registerSellerTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");

        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());
    }
    @Test
    public void registerAnotherUserWithExistingUserNameInDb_usernameAlreadyExistsExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        assertThrows(UsernameAlreadyExistsException.class, ()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSellerWithNullUsername_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername(null);
        registerSellerRequest.setPassword("password");
        assertThrows(NullPointerException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSellerWithNullPassword_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword(null);
        assertThrows(NullPointerException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSellerWithEmptyUsername_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("");
        registerSellerRequest.setPassword("1111");
        assertThrows(IllegalArgumentException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSellerWithEmptyPassword_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("");
        assertThrows(IllegalArgumentException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptyHouseNo_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));

    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptyStreet_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));

    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptyCity_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptyState_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptyCountry_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptySellerUsername_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptySellerPhoneNumber_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithInvalidSellerPhoneNumberLength_IncorrectPhoneNumberLengthExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08167");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(IncorrectPhoneNumberLength.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithInvalidSellerPhoneNumberDigits_InvalidInputExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("-097yioy098");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");

        assertThrows(InvalidInputException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }
    @Test
    public void registerSeller_sellerCreatesContactInfoWithEmptySellerEmailAddress_IllegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);

        assertEquals(1, sellerRepository.count());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("09865432176");
        createSellerContactInfoRequest.setEmailAddress("");

        assertThrows(IllegalArgumentException.class,()->sellerServices.createContactInfo(createSellerContactInfoRequest));
    }


    @Test
    public void registereSeller_fillContactInfo_CreateAdTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());
    }
    @Test
    public void registeredSellerDoesNotFillContactInfo_CreatesAd_contactInfoNotFoundExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");

        assertThrows(NullPointerException.class, ()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void sellerThatIsNotRegisteredCannotCreateAdTest(){
        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        assertThrows(SellerDoesNotExistException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void registeredSellerCanCreateAdWithDifferentusernameCaseTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("USERNAME");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());
    }
    @Test
    public void registerSeller_createAdWithEmptyProductname_illegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());


        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        assertThrows(IllegalArgumentException.class,()->sellerServices.createAd(createAdRequest));
    }

    @Test
    public void registerSeller_createAdWithEmptyProductDescription_illegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());


        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("Product name");
        createAdRequest.setProductDescription("");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        assertThrows(IllegalArgumentException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void registerSeller_createAdWithNullProductName_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName(null);
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        assertThrows(NullPointerException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void registerSeller_createAdWithNullProductDescription_nullPointerExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());


        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription(null);
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        assertThrows(NullPointerException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void registerSeller_createAdWithLettersInsteadOfNumberInProductPrice_InvalidInputExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1ooo");
        createAdRequest.setSellerUsername("username");
        assertThrows(InvalidInputException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void registeredSeller_createContactInfo_CreateAd_editAdTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("15000");
        sellerServices.editAd(editAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("new product name", seller.get().getAds().get(0).getProductName());
    }
    @Test
    public void registeredSeller_editAdWithEmptySellerUsername_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("15000");

        assertThrows(SellerDoesNotExistException.class,()->sellerServices.editAd(editAdRequest));
    }
    @Test
    public void registeredSeller_editAdWithEmptyAdId_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId("");
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("15000");

        assertThrows(IllegalArgumentException.class,()->sellerServices.editAd(editAdRequest));
    }
    @Test
    public void registeredSeller_editAdWithEmptyProductName_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("15000");

        assertThrows(IllegalArgumentException.class,()->sellerServices.editAd(editAdRequest));
    }
    @Test
    public void registeredSeller_editAdWithEmptyProductDescription_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);

        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("");
        editAdRequest.setNewProductPrice("15000");

        assertThrows(IllegalArgumentException.class,()->sellerServices.editAd(editAdRequest));
    }
    @Test
    public void registeredSeller_editAdWithEmptyProductPrice_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("");

        assertThrows(IllegalArgumentException.class,()->sellerServices.editAd(editAdRequest));
    }
    @Test
    public void registeredSeller_editAdWithInvalidProductPrice_sellerDoesNotExistExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());

        EditAdRequest editAdRequest = new EditAdRequest();
        editAdRequest.setSellerUsername("username");
        Ad ad = seller.get().getAds().get(0);
        editAdRequest.setAdId(ad.getId());
        editAdRequest.setNewProductName("new product name");
        editAdRequest.setNewProductDescription("new product description");
        editAdRequest.setNewProductPrice("19-hfs");

        assertThrows(InvalidInputException.class,()->sellerServices.editAd(editAdRequest));
    }


    @Test
    public void buyerCanViewAllAdsTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());

        List<Ad> allAds = buyerService.viewAllAds();
        assertEquals(1, allAds.size());
    }
    @Test
    public void registeredBuyerCanViewOneParticularAdsTest() {
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        CreateAdRequest createAdRequest2 = new CreateAdRequest();
        createAdRequest2.setProductName("product name");
        createAdRequest2.setProductDescription("product description");
        createAdRequest2.setProductPrice("1000");
        createAdRequest2.setSellerUsername("username");
        sellerServices.createAd(createAdRequest2);
        seller = sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(2, adRepository.count());

        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
        registerBuyerRequest.setName("buyername");
        registerBuyerRequest.setUsername("buyerusername");
        buyerService.register(registerBuyerRequest);
        assertEquals(1, buyerRepository.count());
        Optional<Buyer> buyer = buyerRepository.findByUsername("buyerusername");
        assertFalse(buyer.get().isLocked());
        assertEquals(1, buyerRepository.count());

        ViewAdRequest viewAdRequest = new ViewAdRequest();
        viewAdRequest.setBuyerUsername("buyerusername");
        viewAdRequest.setSellerName("username");
        Ad ad = seller.get().getAds().get(0);
        viewAdRequest.setAdId(ad.getId());
        buyerService.viewOneParticularAdWith(viewAdRequest);
        seller = sellerRepository.findByUsername("username");
        ad = seller.get().getAds().get(0);
        assertEquals(1,ad.getNumberOfViews() );
    }
    @Test
    public void notRegisteredBuyerCannotViewOneParticularAdTest() {
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());


        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        CreateAdRequest createAdRequest2 = new CreateAdRequest();
        createAdRequest2.setProductName("product name");
        createAdRequest2.setProductDescription("product description");
        createAdRequest2.setProductPrice("1000");
        createAdRequest2.setSellerUsername("username");
        sellerServices.createAd(createAdRequest2);
        seller = sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(2, adRepository.count());

        ViewAdRequest viewAdRequest = new ViewAdRequest();
        viewAdRequest.setSellerName("username");
        Ad ad = seller.get().getAds().get(0);
        viewAdRequest.setAdId(ad.getId());
        assertThrows(NullPointerException.class,()->buyerService.viewOneParticularAdWith(viewAdRequest));
    }
    @Test
    public void registerBuyer_ViewOneParticularAd_viewSellerInfoTest() {
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());
        Optional<Seller> seller = sellerRepository.findByUsername("username");
        assertFalse(seller.get().isLocked());

        CreateSellerContactInfoRequest createSellerContactInfoRequest = new CreateSellerContactInfoRequest();
        createSellerContactInfoRequest.setHouseNo("5");
        createSellerContactInfoRequest.setStreet("allen");
        createSellerContactInfoRequest.setCity("yaba");
        createSellerContactInfoRequest.setState("lagos");
        createSellerContactInfoRequest.setCountry("nigeria");
        createSellerContactInfoRequest.setSellerUsername("username");
        createSellerContactInfoRequest.setPhoneNumber("08106317491");
        createSellerContactInfoRequest.setEmailAddress("email@address.com");
        sellerServices.createContactInfo(createSellerContactInfoRequest);
        seller = sellerRepository.findByUsername("username");
        assertEquals("08106317491",seller.get().getContactInformation().getPhoneNumber());

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerUsername("username");
        sellerServices.createAd(createAdRequest);

        CreateAdRequest createAdRequest2 = new CreateAdRequest();
        createAdRequest2.setProductName("product name");
        createAdRequest2.setProductDescription("product description");
        createAdRequest2.setProductPrice("1000");
        createAdRequest2.setSellerUsername("username");
        sellerServices.createAd(createAdRequest2);
        seller = sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(2, adRepository.count());

        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
        registerBuyerRequest.setName("buyername");
        registerBuyerRequest.setUsername("buyerusername");
        buyerService.register(registerBuyerRequest);
        assertEquals(1, buyerRepository.count());
        Optional<Buyer> buyer = buyerRepository.findByUsername("buyerusername");
        assertFalse(buyer.get().isLocked());
        assertEquals(1, buyerRepository.count());

        ViewAdRequest viewAdRequest = new ViewAdRequest();
        viewAdRequest.setBuyerUsername("buyerusername");
        viewAdRequest.setSellerName("username");
        Ad ad = seller.get().getAds().get(0);
        viewAdRequest.setAdId(ad.getId());
        buyerService.viewOneParticularAdWith(viewAdRequest);
        seller = sellerRepository.findByUsername("username");
        ad = seller.get().getAds().get(0);
        assertEquals(1,ad.getNumberOfViews() );

        ViewContactInfoRequest viewContactInfoRequest = new ViewContactInfoRequest();
        viewContactInfoRequest.setSellerUsername("username");
        buyerService.viewSellerContactInfo(viewContactInfoRequest);
        String sellerName = buyer.get().getSellerName();
        Optional<Seller> optionalSeller = sellerRepository.findByUsername("username");
        assertTrue(optionalSeller.isPresent());
        Seller seller1 = optionalSeller.get();
        assertNotNull(seller1.getContactInformation());
        assertEquals("08106317491",seller1.getContactInformation().getPhoneNumber());
    }


//    @Test
//    public void registerSeller_CreateOneAd_registerBuyer_registeredBuyerCanReviewAnAdTest() {
//        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
//        registerSellerRequest.setUsername("username");
//        registerSellerRequest.setPassword("password");
//        registerSellerRequest.setPhoneNumber("08123232323");
//        registerSellerRequest.setEmailAddress("email@address");
//        registerSellerRequest.setAddress("address");
//        sellerServices.register(registerSellerRequest);
//        assertEquals(1, sellerRepository.count());
//        Optional<Seller> seller = sellerRepository.findByUsername("username");
//        assertFalse(seller.get().isLocked());
//
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setProductName("product name");
//        createAdRequest.setProductDescription("product description");
//        createAdRequest.setProductPrice("1000");
//        createAdRequest.setSellerName("username");
//        sellerServices.createAd(createAdRequest);
//
//        CreateAdRequest createAdRequest2 = new CreateAdRequest();
//        createAdRequest2.setProductName("product name");
//        createAdRequest2.setProductDescription("product description");
//        createAdRequest2.setProductPrice("1000");
//        createAdRequest2.setSellerName("username");
//        sellerServices.createAd(createAdRequest2);
//        seller = sellerRepository.findByUsername("username");
//        assertTrue(seller.isPresent());
//        assertEquals(2, adRepository.count());
//
//        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
//        registerBuyerRequest.setName("buyername");
//        registerBuyerRequest.setUsername("buyerusername");
//        buyerService.register(registerBuyerRequest);
//        assertEquals(1, buyerRepository.count());
//        Optional<Buyer> buyer = buyerRepository.findByUsername("buyerusername");
//        assertFalse(buyer.get().isLocked());
//        assertEquals(1, buyerRepository.count());
//
//        ViewAdRequest viewAdRequest = new ViewAdRequest();
//        viewAdRequest.setBuyerUsername("buyerusername");
//        viewAdRequest.setSellerName("username");
//        Ad ad = seller.get().getAds().get(0);
//        viewAdRequest.setAdId(ad.getId());
//        buyerService.viewOneParticularAdWith(viewAdRequest);
//        seller = sellerRepository.findByUsername("username");
//        ad = seller.get().getAds().get(0);
//        assertEquals(1,ad.getNumberOfViews() );
//
//        ReviewAdRequest reviewAdRequest = new ReviewAdRequest();
//        reviewAdRequest.setSellerUsername("username");
//        reviewAdRequest.setBuyerUsername("buyerusername");
//        reviewAdRequest.setAdId(ad.getId());
//        reviewAdRequest.setReviewBody("review body");
//        buyerService.review(reviewAdRequest);
//        buyer = buyerRepository.findByUsername("buyerusername");
//        seller = sellerRepository.findByUsername("username");
//        Optional<Ad> ad1 = adRepository.findById(ad.getId());
//        assertEquals(1,ad.getReviews().size());
//    }
//
//
//
//
   }