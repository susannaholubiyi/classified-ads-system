package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.models.Buyer;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.RegisterBuyerRequest;
import africa.semicolon.dtos.RegisterBuyerResponse;
import africa.semicolon.dtos.ViewAdRequest;
import africa.semicolon.dtos.ViewAdResponse;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.utils.GlobalHelpers.validateIfEmpty;
import static africa.semicolon.utils.GlobalHelpers.validateIfNull;
import static africa.semicolon.utils.Mappers.mapRegisterSeller;
import static africa.semicolon.utils.Mappers.mapRegisterBuyer;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    AdServiceImpl adServices;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    AdRepository adRepository;
    @Override
    public List<Ad> viewAllAds() {
        return  adServices.viewAllAds();

    }

    @Override
    public ViewAdResponse viewOneParticularAdWith(ViewAdRequest viewAdRequest) {
      return adServices.viewOneParticularAdWith(viewAdRequest);
    }

    @Override
    public RegisterBuyerResponse register(RegisterBuyerRequest registerBuyerRequest) {
        registerBuyerRequest.setUsername(registerBuyerRequest.getUsername().toLowerCase());
        validateUserName(registerBuyerRequest.getUsername());
        validateIfNull(registerBuyerRequest.getUsername());
        validateIfEmpty(registerBuyerRequest.getUsername());
        validateIfNull(registerBuyerRequest.getName());
        validateIfEmpty(registerBuyerRequest.getName());
        Buyer buyer = mapRegisterBuyer(registerBuyerRequest);
        buyer.setLocked(false);
        buyerRepository.save(buyer);
        return mapRegisterSeller(buyer);
    }
    private void validateUserName(String username) {
        boolean usernameExists = buyerRepository.existsByUsername(username);
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered buyer", username));
    }
}
