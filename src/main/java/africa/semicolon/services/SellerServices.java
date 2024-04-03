package africa.semicolon.services;

import africa.semicolon.dtos.RegisterSellerRequest;
import africa.semicolon.dtos.RegisterSellerResponse;

public interface SellerServices {
    RegisterSellerResponse register(RegisterSellerRequest registerSellerRequest);
}
