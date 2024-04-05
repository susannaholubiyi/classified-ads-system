package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CheckOut {
    private CreditCard buyerCreditCard;
    private String productName;
    private int productPrice;
    private String productDescription;
}
