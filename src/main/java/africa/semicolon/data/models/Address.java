package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address {
    private String id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
}
