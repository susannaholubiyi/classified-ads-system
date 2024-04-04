package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Ad {
    private String sellerName;
    private String productName;
    private String productDescription;
    private String productPrice;
    @DBRef
    private List<Review> reviews;
    private LocalDateTime dateCreated;
    private String id;
}
