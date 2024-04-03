package africa.semicolon.data.reposiories;

import africa.semicolon.data.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
    boolean existsByUsername(String username);
}