package africa.semicolon.services;

import africa.semicolon.data.reposiories.SellerRepository;
import africa.semicolon.dtos.RegisterSellerRequest;
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
        registerSellerRequest.setFirstName("firstName");
        registerSellerRequest.setLastName("lastName");
        registerSellerRequest.setUsername("username");
        registerSellerRequest.setPassword("password");
        sellerServices.register(registerSellerRequest);
        assertEquals(1, sellerRepository.count());

    }

}