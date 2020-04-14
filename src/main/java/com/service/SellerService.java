package com.service;

import java.util.List;

import com.bean.Response;
import com.bean.Seller;

public interface SellerService {

	public static final String STATUS_REQUESTED = "REQUESTED";
	public static final String STATUS_APPROVED = "APPROVED";
	public static final String STATUS_STOPPED = "STOPPED";
	
	public Response<Seller> registerSeller(Seller seller);
	public Response<Seller> authenticateSeller(String email , String password);
	public boolean isNewEmailUnique(Seller seller);
	public boolean isSellerApproved(Seller seller);
	public boolean isEmailAvailable(String email);
	public Response<Seller> updateSellerProfile(Seller seller);
	public List<Seller> getRequsetedSellers();
	public Response<Seller> stopSellerAccount(Seller seller);
	public Response<String> rejectSeller(Seller seller);
	public Response<Seller> approveSeller(Seller seller);
	public List<Seller> getApprovedAndStoppedSellers();
}
