package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
