package africa.semicolon.dtos;

import lombok.Data;

@Data
public class ReviewAdRequest {
    public String BuyerUsername;
    private String adId;
    private String sellerUsername;
    private String reviewBody;
}

