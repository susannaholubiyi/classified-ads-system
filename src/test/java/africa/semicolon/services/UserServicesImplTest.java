package africa.semicolon.services;

import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserServicesImpl userServices;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void registerUserTest(){
        RegisterUserRequest registerSellerRequest = new RegisterUserRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        userServices.register(registerSellerRequest);
        assertEquals(1, userRepository.count());
    }
    @Test
    public void registerUserWithUsernameThatAlreadyExistsInDb_usernameAlreadyExistsExceptionIsThrown(){
        RegisterUserRequest registerSellerRequest = new RegisterUserRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        userServices.register(registerSellerRequest);
        assertEquals(1, userRepository.count());

        assertThrows(UsernameAlreadyExistsException.class, ()-> userServices.register(registerSellerRequest));
    }
    @Test
    public void registeredUserCanBuyAdTest(){
        RegisterUserRequest registerSellerRequest = new RegisterUserRequest();
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        userServices.register(registerSellerRequest);
        assertEquals(1, userRepository.count());



    }

}