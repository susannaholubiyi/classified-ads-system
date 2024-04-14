package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.data.repositories.SellerContactInfoRepository;
import africa.semicolon.data.repositories.SellerRepository;
import africa.semicolon.dtos.CreateSellerContactInfoRequest;
import africa.semicolon.dtos.CreateSellerContactInfoResponse;
import africa.semicolon.dtos.ViewContactInfoRequest;
import africa.semicolon.dtos.ViewContactInfoResponse;
import africa.semicolon.exceptions.SellerDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static africa.semicolon.utils.GlobalHelpers.validateIfEmpty;
import static africa.semicolon.utils.GlobalHelpers.validatePhoneNumber;
import static africa.semicolon.utils.Mappers.*;

@Service
public class SellerContactInfoServicesImpl implements SellerContactInfoServices {
    @Autowired
    private SellerContactInfoRepository contactInfoRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public SellerContactInformation createSellerInformationForm() {
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
    @Override
    public ViewContactInfoResponse viewSellerContactInfo(ViewContactInfoRequest viewContactInfoRequest) {
        validateIfEmpty(viewContactInfoRequest.getSellerUsername());
        Optional<Seller> sellerOptional = sellerRepository.findByUsername(viewContactInfoRequest.getSellerUsername());
        if (sellerOptional.isEmpty()) {
            throw new SellerDoesNotExistException("Seller not found with username: " + viewContactInfoRequest.getSellerUsername());
        }
        Seller seller = sellerOptional.get();
        SellerContactInformation contactInfo = seller.getContactInformation();
        return mapViewContactInfoResponse(contactInfo);
    }


}
