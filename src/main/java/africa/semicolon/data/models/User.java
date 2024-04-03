package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    private String id;
    private String username;
    private String password;
    @DBRef
    private List<Receipt> receipts;
    @DBRef
    private List<Ad> ads;
    private Review reviews;
    private PaymentInformation paymentInfo;
}
