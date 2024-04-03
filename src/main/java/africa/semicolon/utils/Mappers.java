package africa.semicolon.utils;

import africa.semicolon.data.models.User;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;


public class Mappers {
    public static User map(RegisterUserRequest registerSellerRequest){
        User user = new User();
        user.setUsername(registerSellerRequest.getUsername());
        user.setPassword(registerSellerRequest.getPassword());
        return user;
    }
    public static RegisterUserResponse map (User savedUser){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(savedUser.getId());
        registerUserResponse.setUsername(savedUser.getUsername());
        return registerUserResponse;
    }
}
