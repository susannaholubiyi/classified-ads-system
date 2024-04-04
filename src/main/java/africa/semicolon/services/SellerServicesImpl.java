package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;
import africa.semicolon.exceptions.IllegalArgumentException;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
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
        validateIfNull(registerSellerRequest.getUsername());
        validateIfNull(registerSellerRequest.getPassword());
        validateIfEmpty(registerSellerRequest.getUsername());
        validateIfEmpty(registerSellerRequest.getPassword());
        Seller seller = map(registerSellerRequest);
        sellerRepository.save(seller);
        return map(seller);

    }

    private void validateIfEmpty(String request) {
        if (request.isEmpty()) throw new IllegalArgumentException("Empty request");
    }

    private void validateIfNull(String request) {
        if (request == null) throw new NullPointerException("Not a legal argument");
    }

    private void validateUserName(String username) {
        boolean usernameExists = sellerRepository.existsByUsername(username);
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered user", username));
    }

}
