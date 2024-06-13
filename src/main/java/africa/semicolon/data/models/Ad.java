package africa.semicolon.data.models;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "classified_ads")
public class Ad {
    private String sellerName;
    private String productName;
    private String productDescription;
    private String productPrice;
    private int numberOfViews;
    private Binary image;
    private String imageUrl;
    private String contentType;
    @DBRef
    private List<Review> reviews = new ArrayList<>();
    private LocalDateTime dateCreated = LocalDateTime.now();
    @Id
    private String id;
}
