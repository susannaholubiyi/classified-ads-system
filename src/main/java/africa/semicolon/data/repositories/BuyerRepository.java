package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findByUsername(String username);

    boolean existsByUsername(String username);
}
