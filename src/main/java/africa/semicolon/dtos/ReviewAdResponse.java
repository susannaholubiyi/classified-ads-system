package africa.semicolon.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReviewAdResponse {
    private LocalDateTime dateCreated;
    private String content;
    private String reviewerName;
}
