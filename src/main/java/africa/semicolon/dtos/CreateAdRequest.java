package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreateAdRequest {
    private String sellerUsername;
    private String productName;
    private String productDescription;
    private String productPrice;
}
