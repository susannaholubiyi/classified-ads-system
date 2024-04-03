package africa.semicolon.dtos;

import lombok.Data;

@Data
public class RegisterSellerRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
