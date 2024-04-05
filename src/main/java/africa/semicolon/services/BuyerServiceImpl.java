package africa.semicolon.services;

import africa.semicolon.data.models.Ad;
import africa.semicolon.data.repositories.AdRepository;
import africa.semicolon.data.repositories.BuyerRepository;
import africa.semicolon.data.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    AdServiceImpl adServices;
    @Override
    public List<Ad> viewAllAds() {
        return  adServices.viewAllAds();

    }
}
