package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PaymentInformation {
    private String accountName;
    private String accountNumber;
    private String bankName;
    private String id;
}
