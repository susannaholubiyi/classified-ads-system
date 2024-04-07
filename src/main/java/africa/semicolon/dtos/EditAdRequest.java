package africa.semicolon.dtos;

import lombok.Data;

@Data
public class EditAdRequest {
    private String sellerUsername;
    private String newProductName;
    private String newProductDescription;
    private String newProductPrice;
    private String adId;
}
