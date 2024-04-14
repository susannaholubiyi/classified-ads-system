package africa.semicolon.services;

import africa.semicolon.data.models.Seller;
import africa.semicolon.data.models.SellerContactInformation;
import africa.semicolon.dtos.CreateSellerContactInfoRequest;
import africa.semicolon.dtos.CreateSellerContactInfoResponse;
import africa.semicolon.dtos.ViewContactInfoRequest;
import africa.semicolon.dtos.ViewContactInfoResponse;

public interface SellerContactInfoServices {

    SellerContactInformation createSellerInformationForm();
    CreateSellerContactInfoResponse createContactInfo(CreateSellerContactInfoRequest  createSellerContactInfoRequest, Seller seller);

    ViewContactInfoResponse viewSellerContactInfo(ViewContactInfoRequest viewContactInfoRequest);
}
