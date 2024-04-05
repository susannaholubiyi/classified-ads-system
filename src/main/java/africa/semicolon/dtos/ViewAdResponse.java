package africa.semicolon.dtos;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ViewAdResponse {
    private String productName;
    private String productDescription;
    private String productPrice;
    private int numberOfViews;
    private List<Review> reviews;
    private LocalDateTime dateCreated;
    private String sellerPhoneNumber;
    private String sellerEmailAddress;
    private String sellerAddress;
    private String sellerName;
}
