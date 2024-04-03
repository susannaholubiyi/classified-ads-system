package africa.semicolon.services;

import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;

public interface UserServices {
    RegisterUserResponse register(RegisterUserRequest registerSellerRequest);
}
