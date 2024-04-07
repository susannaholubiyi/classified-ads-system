package africa.semicolon.dtos;

import lombok.Data;

@Data
public class ViewAdRequest {
    private String sellerName;
    private String adId;
    private String buyerUsername;
}
