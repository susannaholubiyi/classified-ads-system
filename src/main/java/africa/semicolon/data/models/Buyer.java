package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Buyer {
    private String id;
    private String name;
    @DBRef
    private List<Receipt> receipts;
    private Review reviews;
}
