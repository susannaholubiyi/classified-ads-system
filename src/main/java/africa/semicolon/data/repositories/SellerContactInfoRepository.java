package africa.semicolon.data.repositories;

import africa.semicolon.data.models.SellerContactInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerContactInfoRepository extends MongoRepository<SellerContactInformation, String> {
    SellerContactInformation findBySellerUsername(String sellerUsername);
}
