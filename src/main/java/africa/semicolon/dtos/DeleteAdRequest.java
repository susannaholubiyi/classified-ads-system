package africa.semicolon.dtos;

import lombok.Data;

@Data
public class DeleteAdRequest {
    private String sellerUsername;
    private String adId;
}
