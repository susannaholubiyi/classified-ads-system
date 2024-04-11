package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Seller {
    private String id;
    private String username;
    @DBRef
    private SellerContactInformation contactInformation;
    private String password;
    @DBRef
    private List<Ad> ads = new ArrayList<>();
    @DBRef
    private List<Buyer> buyers = new ArrayList<>();
    private boolean isLocked = true;
}
