package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Ad {
    private String productName;
    private String productDescription;
    private int productPrice;
    @DBRef
    private List<Review> reviews;
    private String id;
}
