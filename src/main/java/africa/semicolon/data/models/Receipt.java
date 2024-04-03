package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class Receipt {
    private String id;
    private LocalDateTime timeIssued;
    private PaymentStatus status;
    private String buyerId;
}
