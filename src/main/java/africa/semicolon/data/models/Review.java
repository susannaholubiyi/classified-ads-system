package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Review {
    private String id;
    private String content;
    private String reviewerName;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
