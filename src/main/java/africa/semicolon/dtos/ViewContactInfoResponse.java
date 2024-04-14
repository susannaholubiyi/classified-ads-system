package africa.semicolon.dtos;

import lombok.Data;

@Data
public class ViewContactInfoResponse {
    private String sellerPhoneNumber;
    private String sellerEmailAddress;
    private String sellerHouseNo;
    private String sellerStreet;
    private String sellerCity;
    private String sellerState;
    private String sellerCountry;
}
