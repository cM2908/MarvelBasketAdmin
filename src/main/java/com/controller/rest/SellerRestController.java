package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Category;
import com.bean.Login;
import com.bean.Response;
import com.bean.Seller;
import com.bean.SellerCategory;
import com.dao.SellerCategoryDao;
import com.dao.SellerDao;
import com.service.SellerCategoryService;
import com.service.SellerService;

@RestController
public class SellerRestController {

	@Autowired
	SellerService sellerService;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	SellerCategoryDao sellerCategoryDao;
	@Autowired
	SellerCategoryService sellerCategoryService;

	@RequestMapping(value = "/seller/register", method = RequestMethod.POST)
	public ResponseEntity<Object> registerSeller(@RequestBody SellerCategory sellerCategory) {
		/*
		 * System.out.println("seller Name : "+sellerCategory.getSeller().getSellerName(
		 * ));
		 * System.out.println("shop Name : "+sellerCategory.getSeller().getShopName());
		 * System.out.println("Categories : "+sellerCategory.getCategory().size());
		 */
		if (sellerCategory.getCategory().size() > 0) {
			Response<Seller> response = sellerService.registerSeller(sellerCategory.getSeller());
			if (response.getCode() == 1004) {
				// email already taken
				return new ResponseEntity<>("email taken", HttpStatus.CONFLICT);
			} else {
				sellerCategory.setSeller(response.getData());
				sellerCategoryDao.save(sellerCategory);
				return new ResponseEntity<>(response.getData(), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/seller/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Seller> authenticateSeller(@RequestBody Login login) {
		/*
		 * System.out.println("Email : "+login.getEmail());
		 * System.out.println("Password : "+login.getPassword());
		 */
		Response<Seller> response = sellerService.authenticateSeller(login.getEmail(), login.getPassword());
		if (response.getCode() == 1001) {
			// Valid
			return new ResponseEntity<>(response.getData(), HttpStatus.OK);
		} else if (response.getCode() == 1003) {
			// Valid but Acc stopped
			return new ResponseEntity<>(response.getData(), HttpStatus.FORBIDDEN);
		} else if (response.getCode() == 1010) {
			// Valid but Acc Requested
			return new ResponseEntity<>(response.getData(), HttpStatus.PROCESSING);
		} else {
			// Invalid
			return new ResponseEntity<>(response.getData(), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/seller/find/{email}", method = RequestMethod.GET)
	public ResponseEntity<Seller> getSellerByEmail(@PathVariable String email) {
		if (email == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			List<Seller> sellerList = sellerDao.findByProperty("seller_email", email.trim());
			if (sellerList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(sellerList.get(0), HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = "/seller/update/", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSellerByEmail(@RequestBody Seller sellerP) {
		// TODO : Update Seller
		return null;
	}

	@RequestMapping(value = "seller/update/category/{sellerId}", method = RequestMethod.GET)
	public List<Category> updateCategory(@PathVariable int sellerId) {
		List<Category> categories = sellerCategoryService.findNewCategories(sellerId);
		return categories;
	}

}
