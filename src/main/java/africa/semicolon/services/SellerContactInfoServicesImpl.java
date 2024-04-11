package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.dtos.CreateSellerContactInfoRequest;
import africa.semicolon.dtos.CreateSellerContactInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static africa.semicolon.utils.GlobalHelpers.validateIfEmpty;
import static africa.semicolon.utils.GlobalHelpers.validatePhoneNumber;
import static africa.semicolon.utils.Mappers.mapSellerInfo;
import static africa.semicolon.utils.Mappers.mapSellerResponse;

@Service
public class SellerContactInfoServicesImpl implements SellerContactInfoServices {
    @Autowired
    private SellerContactInfoRepository contactInfoRepository;



    @Override
    public SellerContactInformation createSellerInformation() {
        SellerContactInformation sellerContactInformation = new SellerContactInformation();
        contactInfoRepository.save(sellerContactInformation);
        return sellerContactInformation;
    }

    @Override
    public CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest createSellerContactInfoRequest, Seller seller) {
        validateIfEmpty(createSellerContactInfoRequest.getSellerUsername().strip());
        validateIfEmpty(createSellerContactInfoRequest.getPhoneNumber().strip());
        validateIfEmpty(createSellerContactInfoRequest.getEmailAddress().strip());
        validateIfEmpty(createSellerContactInfoRequest.getHouseNo().strip());
        validateIfEmpty(createSellerContactInfoRequest.getStreet().strip());
        validateIfEmpty(createSellerContactInfoRequest.getCity().strip());
        validateIfEmpty(createSellerContactInfoRequest.getState().strip());
        validateIfEmpty(createSellerContactInfoRequest.getCountry().strip());
        validatePhoneNumber(createSellerContactInfoRequest.getPhoneNumber());
        SellerContactInformation newSellerContactInformation = mapSellerInfo(createSellerContactInfoRequest, seller);
        contactInfoRepository.save(newSellerContactInformation);
        return mapSellerResponse(newSellerContactInformation);
    }


}
