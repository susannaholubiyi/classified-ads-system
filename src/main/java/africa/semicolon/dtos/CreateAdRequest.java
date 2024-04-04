package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreateAdRequest {
    private String sellerName;
    private String productName;
    private String productDescription;
    private String productPrice;}
