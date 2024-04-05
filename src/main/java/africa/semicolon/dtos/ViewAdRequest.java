package africa.semicolon.dtos;

import africa.semicolon.data.models.Ad;
import lombok.Data;

import java.util.List;

@Data
public class ViewAdRequest {
    private String sellerName;
    private String adId;
    private String buyerName;
}
