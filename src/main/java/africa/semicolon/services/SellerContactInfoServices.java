package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.dtos.CreateSellerContactInfoRequest;
import africa.semicolon.dtos.CreateSellerContactInfoResponse;

public interface SellerContactInfoServices {

    SellerContactInformation createSellerInformation();
    CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest  createSellerContactInfoRequest, Seller seller);
}
