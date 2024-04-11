package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SellerContactInformation {
    private String sellerUsername;
    private String phoneNumber;
    private String emailAddress;
    private Address address;
    private String id;
}
