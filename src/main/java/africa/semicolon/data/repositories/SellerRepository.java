package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {
        boolean existsByUsername(String username);

    Optional<Seller> findByUsername(String username);
}
