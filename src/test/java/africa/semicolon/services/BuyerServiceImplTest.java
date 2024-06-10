package africa.semicolon.services;

import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.dtos.RegisterBuyerRequest;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BuyerServiceImplTest {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private BuyerService buyerService;
    @BeforeEach
    public void setUp(){
        buyerRepository.deleteAll();
    }
    @Test
    public void buyerCanRegisterTest(){
        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
        registerBuyerRequest.setName("buyer name");
        registerBuyerRequest.setUsername("username");
        buyerService.register(registerBuyerRequest);
        assertEquals(1, buyerRepository.count());
        Optional<Buyer> buyer = buyerRepository.findByUsername("username");
        assertFalse(buyer.get().isLocked());

    }
    @Test
    public void buyerCanOnlyRegisterWithUniqueUsernameTest(){
        RegisterBuyerRequest registerBuyerRequest = new RegisterBuyerRequest();
        registerBuyerRequest.setName("buyer name");
        registerBuyerRequest.setUsername("username");
        buyerService.register(registerBuyerRequest);
        assertEquals(1, buyerRepository.count());
        Optional<Buyer> buyer = buyerRepository.findByUsername("username");
        assertFalse(buyer.get().isLocked());
        assertThrows(UsernameAlreadyExistsException.class, ()->buyerService.register(registerBuyerRequest));

    }

}