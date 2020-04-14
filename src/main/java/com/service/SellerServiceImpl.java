package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Response;
import com.bean.Seller;
import com.dao.SellerCategoryDao;
import com.dao.SellerDao;
import com.rowmapper.SellerRowMapper;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	SellerCategoryDao sellerCategoryDao;
	@Autowired
	AdminNotificationService adminNotificationService;

	/**
	 * Register a new Seller
	 * 
	 * @param seller the Seller which we have to register
	 * @return {@link Response} with code - '1005' for 'Successful seller
	 *         registration' , '1004' for 'Email taken error'
	 * @see Response
	 */
	@Override
	public Response<Seller> registerSeller(Seller seller) {
		seller.setSellerStatus(STATUS_REQUESTED);
		if (isEmailAvailable(seller.getSellerEmail())) {
			// Save seller data to database with the status 'REQUESTED'
			sellerDao.save(seller);
			List<Seller> dbSeller = sellerDao.findByProperty("seller_email", seller.getSellerEmail());
			// Send notification to admin about request
			adminNotificationService.addNotificationForSellerRequest(dbSeller.get(0));
			return new Response<Seller>(dbSeller.get(0), "Seller registration successful , wait for approval", 1005);
		} else {
			return new Response<Seller>(seller, "Email already taken", 1004);
		}
	}

	/**
	 * Check seller credentials for login
	 * 
	 * @param email    seller email for authentication
	 * @param password seller password for authentication
	 * @return {@link Response} with code - '1001' for 'Seller data found' , '1002'
	 *         for 'Invalid email or password' , '1003' for 'Seller account either
	 *         stopped or not approved'
	 * @see Response
	 */
	@Override
	public Response<Seller> authenticateSeller(String email, String password) {
		String query = "SELECT * FROM seller WHERE seller_email = ? and seller_password = ?";
		Object[] parameters = new Object[] { email, password };
		Seller seller = null;
		try {
			seller = jdbcTemplate.queryForObject(query, parameters, new SellerRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new Response<Seller>(seller, "Invalid email or password", 1002);
		}
		if (isSellerApproved(seller)) {
			return new Response<Seller>(seller, "seller data found", 1001);
		} else if (isSellerRequested(seller)) {
			return new Response<Seller>(seller, "seller not approved", 1010);
		} else {
			return new Response<Seller>(null, "Seller account stopped", 1003);
		}
	}

	/**
	 * Checks whether the email is unique or not while updating seller profile
	 * 
	 * @param seller seller object having new email
	 * @return true if this email is unique; false otherwise
	 */
	@Override
	public boolean isNewEmailUnique(Seller seller) {
		String query = "SELECT * FROM seller WHERE seller_email = ? and seller_id != ?";
		Object[] parameters = new Object[] { seller.getSellerEmail(), seller.getSellerId() };
		try {
			jdbcTemplate.queryForObject(query, parameters, new SellerRowMapper());
			return false;
		} catch (EmptyResultDataAccessException ex) {
			return true;
		}
	}

	/**
	 * Checks whether the seller is approved by admin or not
	 * 
	 * @param seller the reference object of seller for which we have to check
	 *               status(approved or not)
	 * @return true if this seller is approved; false otherwise
	 */
	public boolean isSellerApproved(Seller seller) {
		Seller sellerLocal = sellerDao.findById(seller.getSellerId());
		if (sellerLocal.getSellerStatus().equals(SellerService.STATUS_APPROVED)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the email is unique or not while registering the new seller
	 * 
	 * @param email the email for which we have to check availability
	 * @return true if this email is available; false otherwise
	 */
	@Override
	public boolean isEmailAvailable(String email) {
		List<Seller> seller = sellerDao.findByProperty("seller_email", email);
		if (seller.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Update the seller profile
	 * 
	 * @param seller the seller which we have to update
	 * @return {@link Response} with code - '1007' for 'Successful seller update' ,
	 *         '1006' for 'Email taken error'
	 * @see Response
	 */
	@Override
	public Response<Seller> updateSellerProfile(Seller seller) {
		if (isNewEmailUnique(seller)) {
			sellerDao.update(seller);
			return new Response<Seller>(seller, "Seller data updated succesfully", 1007);
		} else {
			return new Response<Seller>(seller, "Email already taken", 1006);
		}
	}

	/**
	 * Find the requested sellers
	 * 
	 * @return list of sellers who has requested
	 */
	public List<Seller> getRequsetedSellers() {
		List<Seller> sellerList = sellerDao.findByProperty("seller_status", STATUS_REQUESTED);
		return sellerList;
	}

	/**
	 * Approve the seller
	 * 
	 * @param seller to approve
	 * @return {@link Response} with code - '1011' for 'Successful seller approval'
	 *         , '1012' for 'seller not found'
	 */
	public Response<Seller> approveSeller(Seller seller) {
		if (seller != null) {
			sellerDao.updateProperty("seller_status", STATUS_APPROVED, seller.getSellerId());
			seller.setSellerStatus(STATUS_APPROVED);
			return new Response<Seller>(seller, "Seller Approved", 1011);
		} else {
			return new Response<Seller>(null, "Seller not found", 1012);
		}
	}

	/**
	 * Reject the seller
	 * 
	 * @param seller to reject
	 * @return {@link Response} with code - '1011' for 'Successful seller rejection'
	 *         , '1012' for 'seller not found'
	 */
	public Response<String> rejectSeller(Seller seller) {
		if (seller != null) {
			sellerDao.delete(seller);
			return new Response<String>("Seller Rejected", "Seller Rejected", 1011);
		} else {
			return new Response<String>("Seller not found", "Seller not found", 1012);
		}
	}

	/**
	 * Stop the seller account
	 * 
	 * @param seller to stop
	 * @return {@link Response} with code - '1011' for 'Successful seller account
	 *         stop' , '1012' for 'seller not found'
	 */
	public Response<Seller> stopSellerAccount(Seller seller) {
		if (seller != null) {
			sellerDao.updateProperty("seller_status", STATUS_STOPPED, seller.getSellerId());
			seller.setSellerStatus(STATUS_STOPPED);
			return new Response<Seller>(seller, "Seller account stopped", 1011);
		} else {
			return new Response<Seller>(null, "Seller not found", 1012);
		}
	}

	/**
	 * Find the approved and stopped sellers
	 * 
	 * @return list of sellers who has approved and stopped
	 */
	public List<Seller> getApprovedAndStoppedSellers() {
		List<Seller> sellerList = sellerDao.findByProperty("seller_status", STATUS_APPROVED);
		List<Seller> stoppedSellerList = sellerDao.findByProperty("seller_status", STATUS_STOPPED);
		sellerList.addAll(stoppedSellerList);
		return sellerList;
	}

	public boolean isSellerRequested(Seller seller) {
		Seller sellerLocal = sellerDao.findById(seller.getSellerId());
		if (sellerLocal.getSellerStatus().equals(SellerService.STATUS_REQUESTED)) {
			return true;
		}
		return false;
	}
}
