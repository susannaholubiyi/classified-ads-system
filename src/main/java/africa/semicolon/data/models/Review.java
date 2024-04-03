package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Review {
    private String id;
    private User reviewer;
    private String content;
    private Ad productReviewed;
}
