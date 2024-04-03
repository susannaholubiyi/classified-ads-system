package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mappers.map;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        registerUserRequest.setUsername(registerUserRequest.getUsername().toLowerCase());
        validateUserName(registerUserRequest.getUsername());
        User user = map(registerUserRequest);
        userRepository.save(user);
        return map(user);

    }
    private void validateUserName(String username) {
        boolean usernameExists = userRepository.existsByUsername(username);
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered user", username));
    }

}
