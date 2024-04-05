package africa.semicolon.dtos;

import lombok.Data;

@Data
public class RegisterSellerRequest {
    private String username;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private String address;

}
