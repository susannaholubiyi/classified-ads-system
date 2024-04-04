package africa.semicolon.services;

import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.exceptions.IllegalArgumentException;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SellerServicesImplTest {
    @Autowired
    private SellerServicesImpl sellerServices;
    @Autowired
    private SellerRepository sellerRepository;
    @BeforeEach
    public void setUp(){
        sellerRepository.deleteAll();
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

    }

}