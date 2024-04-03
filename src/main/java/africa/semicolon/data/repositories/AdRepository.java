package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdRepository extends MongoRepository<Ad, String > {
}
