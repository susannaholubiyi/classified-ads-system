package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Seller {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private PaymentInformation paymentInfo;
    @DBRef
    private List<Ad> ads;
    @DBRef
    private List<Buyer> buyers;
}
