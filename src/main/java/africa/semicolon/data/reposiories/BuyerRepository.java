package africa.semicolon.data.reposiories;

import africa.semicolon.data.models.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
}
