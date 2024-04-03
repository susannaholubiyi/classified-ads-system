package africa.semicolon.data.reposiories;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.models.Review;
import africa.semicolon.data.models.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class RepositoriesTest {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @BeforeEach
    public void setUp(){
        sellerRepository.deleteAll();
        buyerRepository.deleteAll();
        adRepository.deleteAll();
    }
    @Test
    public void sellerRepositoryCanSaveTest(){
        Seller seller = new Seller();
        sellerRepository.save(seller);
        assertEquals(1, sellerRepository.count());

    }
    @Test
    public void buyerRepositoryCanSaveTest(){
        Buyer buyer = new Buyer();
        buyerRepository.save(buyer);
        assertEquals(1, buyerRepository.count());
    }
    @Test
    public void adRepositoryCanSaveTest(){
        Ad ad = new Ad();
        adRepository.save(ad);
        assertEquals(1, adRepository.count());
    }
    @Test
    public void reviewRepositoryCanSaveTest(){
        Review review = new Review();
        reviewRepository.save(review);
        assertEquals(1, reviewRepository.count());
    }

}