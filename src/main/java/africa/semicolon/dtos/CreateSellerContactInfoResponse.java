package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreateSellerContactInfoResponse {
    private String sellerUsername;
    private String phoneNumber;
    private String emailAddress;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
}
