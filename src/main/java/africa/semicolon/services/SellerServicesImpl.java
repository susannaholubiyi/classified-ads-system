package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.reposiories.SellerRepository;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;
import africa.semicolon.exceptions.UserNameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mappers.map;

@Service
public class SellerServicesImpl implements SellerServices{
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest) {
        registerSellerRequest.setUsername(registerSellerRequest.getUsername().toLowerCase());
        validateUserName(registerSellerRequest.getUsername());
        Seller seller = map(registerSellerRequest);
        sellerRepository.save(seller);
        return map(seller);

    }
    private void validateUserName(String username) {
        boolean usernameExists = sellerRepository.existsByUsername(username);
        if(usernameExists) throw new UserNameAlreadyExistsException(String.format("%s is a registered user", username));
    }

}
