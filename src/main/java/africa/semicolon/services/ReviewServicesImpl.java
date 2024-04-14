package africa.semicolon.services;

import africa.semicolon.data.models.Review;
import africa.semicolon.data.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServicesImpl implements ReviewServices{
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review createReviewForm() {
        Review review = new Review();
        reviewRepository.save(review);
        return review;
    }
}
