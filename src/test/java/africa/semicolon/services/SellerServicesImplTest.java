package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.CreateAdRequest;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.exceptions.IllegalArgumentException;
import africa.semicolon.exceptions.InvalidInputException;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @BeforeEach
    public void setUp(){
        sellerRepository.deleteAll();
        adRepository.deleteAll();
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
    public void registerSellerWithEmptyPassword_illegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("");
        assertThrows(IllegalArgumentException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registerSellerWithEmptyUsername_illegalArgumentExceptionIsThrownTest(){
        RegisterSellerRequest registerSellerRequest = new RegisterSellerRequest();
        registerSellerRequest.setUsername("");
        registerSellerRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class,()->sellerServices.register(registerSellerRequest));
    }
    @Test
    public void registeredSellerCanCreateAdTest(){
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
        createAdRequest.setSellerName("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());
        assertEquals(1, seller.get().getAds().size());
        assertEquals("product name", seller.get().getAds().get(0).getProductName());
    }
    @Test
    public void sellerThatIsNotRegisteredCannotCreateAdTest(){
        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerName("username");
        assertThrows(SellerDoesNotExistException.class,()->sellerServices.createAd(createAdRequest));
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

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerName("username");
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

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("Product name");
        createAdRequest.setProductDescription("");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerName("username");
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

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName(null);
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerName("username");
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

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription(null);
        createAdRequest.setProductPrice("1000");
        createAdRequest.setSellerName("username");
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

        CreateAdRequest createAdRequest = new CreateAdRequest();
        createAdRequest.setProductName("product name");
        createAdRequest.setProductDescription("product description");
        createAdRequest.setProductPrice("1ooo");
        createAdRequest.setSellerName("username");
        assertThrows(InvalidInputException.class,()->sellerServices.createAd(createAdRequest));
    }
    @Test
    public void buyerCanViewAdTest(){
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
        createAdRequest.setSellerName("username");
        sellerServices.createAd(createAdRequest);

        seller= sellerRepository.findByUsername("username");
        assertTrue(seller.isPresent());
        assertEquals(1, adRepository.count());


    }

}