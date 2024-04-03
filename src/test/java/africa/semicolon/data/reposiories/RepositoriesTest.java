package africa.semicolon.data.reposiories;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.User;
import africa.semicolon.data.models.Review;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.ReviewRepository;
import africa.semicolon.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class RepositoriesTest {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        adRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    public void buyerRepositoryCanSaveTest(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());
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