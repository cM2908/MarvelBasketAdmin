package com.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Book;
import com.bean.Electronic;
import com.bean.FashionBean;
import com.bean.General;
import com.bean.Response;
import com.dao.BookDao;
import com.dao.ElectronicDao;
import com.dao.GeneralDao;
import com.service.BookService;
import com.service.ElectronicService;
import com.service.FashionService;
import com.service.GeneralService;

@RestController
public class ProductRestController {

	@Autowired
	ElectronicService electronicService;
	@Autowired
	BookService bookService;
	@Autowired
	GeneralService generalService;
	@Autowired
	FashionService fashionService;
	@Autowired
	ElectronicDao electronicDao;
	@Autowired
	BookDao bookDao;
	@Autowired
	GeneralDao generalDao;

	@RequestMapping(value = "/electronic/add", method = RequestMethod.POST)
	public ResponseEntity<Electronic> addElectronic(@RequestBody Electronic electronic) {
		Response<Electronic> response = electronicService.addElectronic(electronic);
		if (response.getCode() == 1052) {
			// Electronic already exist
			return new ResponseEntity<Electronic>(response.getData(), HttpStatus.CONFLICT);
		} else if (response.getCode() == 1053) {
			// Seller not approved (stopped or requested)
			return new ResponseEntity<Electronic>(response.getData(), HttpStatus.UNAUTHORIZED);
		} else {
			// Success
			return new ResponseEntity<Electronic>(response.getData(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/book/add", method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Response<Book> response = bookService.addBook(book);
		if (response.getCode() == 1052) {
			// Book already exist
			return new ResponseEntity<Book>(response.getData(), HttpStatus.CONFLICT);
		} else if (response.getCode() == 1053) {
			// Seller not approved (stopped or requested)
			return new ResponseEntity<Book>(response.getData(), HttpStatus.UNAUTHORIZED);
		} else {
			// Success
			return new ResponseEntity<Book>(response.getData(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/general/add", method = RequestMethod.POST)
	public ResponseEntity<General> addGeneral(@RequestBody General general) {
		Response<General> response = generalService.addGeneral(general);
		if (response.getCode() == 1052) {
			// General product already exist
			return new ResponseEntity<General>(response.getData(), HttpStatus.CONFLICT);
		} else if (response.getCode() == 1053) {
			// Seller not approved (stopped or requested)
			return new ResponseEntity<General>(response.getData(), HttpStatus.UNAUTHORIZED);
		} else {
			// Success
			return new ResponseEntity<General>(response.getData(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/fashion/add", method = RequestMethod.POST)
	public ResponseEntity<FashionBean> addFashion(@RequestBody FashionBean fashionBean) {
		Response<FashionBean> response = fashionService.addFashion(fashionBean);
		if (response.getCode() == 1052) {
			// Fashion already exist
			return new ResponseEntity<FashionBean>(response.getData(), HttpStatus.CONFLICT);
		} else if (response.getCode() == 1053) {
			// Seller not approved (stopped or requested)
			return new ResponseEntity<FashionBean>(response.getData(), HttpStatus.UNAUTHORIZED);
		} else {
			// Success
			return new ResponseEntity<FashionBean>(response.getData(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/productcount/approved/{id}", method = RequestMethod.GET)
	public List<Integer> approvedProductCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer approvedElectronicCountForSeller = electronicService.getApprovedElectronics(sellerId).size();
		Integer approvedBookCountForSeller = bookService.getApprovedBooks(sellerId).size();
		Integer approvedGeneralCountForSeller = generalService.getApprovedGeneral(sellerId).size();
		Integer approvedFashionCountForSeller = fashionService.getApprovedFashion(sellerId).size();

		List<Integer> approvedProductCountForSeller = new ArrayList<Integer>();
		approvedProductCountForSeller.add(approvedElectronicCountForSeller);
		approvedProductCountForSeller.add(approvedBookCountForSeller);
		approvedProductCountForSeller.add(approvedGeneralCountForSeller);
		approvedProductCountForSeller.add(approvedFashionCountForSeller);

		System.out.println("apc : " + approvedProductCountForSeller);

		return approvedProductCountForSeller;
	}

	@RequestMapping(value = "/productcount/stopped/{id}", method = RequestMethod.GET)
	public List<Integer> stoppedProductCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer stoppedElectronicCountForSeller = electronicService.getStoppedElectronics(sellerId).size();
		Integer stoppedBookCountForSeller = bookService.getStoppedBooks(sellerId).size();
		Integer stoppedGeneralCountForSeller = generalService.getStoppedGeneral(sellerId).size();
		Integer stoppedFashionCountForSeller = fashionService.getStoppedFashion(sellerId).size();

		List<Integer> stoppedProductCountForSeller = new ArrayList<Integer>();
		stoppedProductCountForSeller.add(stoppedElectronicCountForSeller);
		stoppedProductCountForSeller.add(stoppedBookCountForSeller);
		stoppedProductCountForSeller.add(stoppedGeneralCountForSeller);
		stoppedProductCountForSeller.add(stoppedFashionCountForSeller);

		System.out.println("spc : " + stoppedProductCountForSeller);

		return stoppedProductCountForSeller;
	}

	@RequestMapping(value = "/productcount/requested/{id}", method = RequestMethod.GET)
	public List<Integer> requestedProductCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer requestedElectronicCountForSeller = electronicService.getRequestedElectronics(sellerId).size();
		Integer requestedBookCountForSeller = bookService.getRequestedBooks(sellerId).size();
		Integer requestedGeneralCountForSeller = generalService.getRequestedGeneral(sellerId).size();
		Integer requestedFashionCountForSeller = fashionService.getRequestedFashion(sellerId).size();

		List<Integer> requestedProductCountForSeller = new ArrayList<Integer>();
		requestedProductCountForSeller.add(requestedElectronicCountForSeller);
		requestedProductCountForSeller.add(requestedBookCountForSeller);
		requestedProductCountForSeller.add(requestedGeneralCountForSeller);
		requestedProductCountForSeller.add(requestedFashionCountForSeller);

		System.out.println("rpc : " + requestedProductCountForSeller);

		return requestedProductCountForSeller;
	}

	@RequestMapping(value = "/productcount/{id}", method = RequestMethod.GET)
	public List<Integer> productCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer electronicCountForSeller = electronicDao.findByProperty("seller_id", sellerId).size();
		Integer bookCountForSeller = bookDao.findByProperty("seller_id", sellerId).size();
		Integer generalCountForSeller = generalDao.findByProperty("seller_id", sellerId).size();
		Integer fashionCountForSeller = fashionService.getAllFashion(sellerId).size();

		List<Integer> productCountForSeller = new ArrayList<Integer>();
		productCountForSeller.add(electronicCountForSeller);
		productCountForSeller.add(bookCountForSeller);
		productCountForSeller.add(generalCountForSeller);
		productCountForSeller.add(fashionCountForSeller);

		System.out.println("pc : " + productCountForSeller);

		return productCountForSeller;
	}

	@RequestMapping(value = "/productcount/electronic/{id}", method = RequestMethod.GET)
	public Integer electronicCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer electronicCount = electronicDao.findByProperty("seller_id", sellerId).size();
		return electronicCount;
	}

	@RequestMapping(value = "/productcount/fashion/{id}", method = RequestMethod.GET)
	public Integer fashionCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer fashionCount = fashionService.getAllFashion(sellerId).size();
		return fashionCount;
	}

	@RequestMapping(value = "/productcount/general/{id}", method = RequestMethod.GET)
	public Integer generalCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer generalCount = generalDao.findByProperty("seller_id", sellerId).size();
		return generalCount;
	}

	@RequestMapping(value = "/productcount/book/{id}", method = RequestMethod.GET)
	public Integer bookCountForSeller(@PathVariable("id") Integer sellerId) {
		Integer bookCount = bookDao.findByProperty("seller_id", sellerId).size();
		return bookCount;
	}

	@RequestMapping(value = "/product/electronic/{id}", method = RequestMethod.GET)
	public List<Electronic> electronicProductsForSeller(@PathVariable("id") Integer sellerId) {
		List<Electronic> electronicList = electronicService.getApprovedElectronics(sellerId);
		return electronicList;
	}

	@RequestMapping(value = "/product/book/{id}", method = RequestMethod.GET)
	public List<Book> bookProductsForSeller(@PathVariable("id") Integer sellerId) {
		List<Book> bookList = bookService.getApprovedBooks(sellerId);
		return bookList;
	}

	@RequestMapping(value = "/product/general/{id}", method = RequestMethod.GET)
	public List<General> generalProductsForSeller(@PathVariable("id") Integer sellerId) {
		List<General> generalList = generalService.getApprovedGeneral(sellerId);
		return generalList;
	}

	@RequestMapping(value = "/product/fashion/{id}", method = RequestMethod.GET)
	public List<FashionBean> fashionProductsForSeller(@PathVariable("id") Integer sellerId) {
		List<FashionBean> fashionBeanList = fashionService.getApprovedFashion(sellerId);
		return fashionBeanList;
	}
}