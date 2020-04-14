package com.controller.web.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Book;
import com.bean.Category;
import com.bean.Electronic;
import com.bean.FashionBean;
import com.bean.General;
import com.bean.Seller;
import com.bean.User;
import com.dao.CategoryDao;
import com.dao.ElectronicDao;
import com.dao.SellerDao;
import com.dao.UserDao;
import com.service.BookService;
import com.service.ElectronicService;
import com.service.FashionService;
import com.service.GeneralService;
import com.service.SellerService;
import com.util.JsonUtil;

@Controller
public class DashboardController {

	@Autowired
	ElectronicDao electronicDao;
	@Autowired
	ElectronicService electronicService;
	@Autowired
	GeneralService generalService;
	@Autowired
	BookService bookService;
	@Autowired
	FashionService fashionService;
	@Autowired
	UserDao userDao;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	SellerService sellerService;
	@Autowired
	CategoryDao categoryDao;

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {

		/* CARDS */
		
		List<Seller> sellerList = sellerService.getApprovedAndStoppedSellers();
		List<User> userList = userDao.findAll();
		List<Category> categoryList = categoryDao.findAll();
		List<Electronic> electronicList = electronicService.getApprovedAndStoppedElectronics();
		List<Book> bookList = bookService.getApprovedAndStoppedBooks();
		List<General> generalList = generalService.getApprovedAndStoppedGeneral();
		List<FashionBean> fashionBeanList = fashionService.getApprovedAndStoppedFashion();

		model.addAttribute("sellerList", sellerList);
		model.addAttribute("sellerCount", sellerList.size());
		model.addAttribute("userCount", userList.size());
		model.addAttribute("categoryCount", categoryList.size());
		model.addAttribute("productCount", electronicList.size()+bookList.size()+generalList.size()+fashionBeanList.size());
		
		
		/* BAR CHART */
		
		Integer sellerId = sellerList.get(0).getSellerId();
		
		Integer approvedElectronicCountForSeller = electronicService.getApprovedElectronics(sellerId).size();
		Integer approvedBookCountForSeller = bookService.getApprovedBooks(sellerId).size();
		Integer approvedGeneralCountForSeller = generalService.getApprovedGeneral(sellerId).size();
		Integer approvedFashionCountForSeller = fashionService.getApprovedFashion(sellerId).size();
		
		List<Integer> approvedProductsCountForSeller = new ArrayList<Integer>();
		approvedProductsCountForSeller.add(approvedElectronicCountForSeller);
		approvedProductsCountForSeller.add(approvedBookCountForSeller);
		approvedProductsCountForSeller.add(approvedGeneralCountForSeller);
		approvedProductsCountForSeller.add(approvedFashionCountForSeller);
		String approvedProductsCountJsonForSeller = JsonUtil.listToJSON(approvedProductsCountForSeller);
		model.addAttribute("approvedProductsCountJsonForSeller", approvedProductsCountJsonForSeller);

		
		Integer stoppedElectronicCountForSeller = electronicService.getStoppedElectronics(sellerId).size();
		Integer stoppedBookCountForSeller = bookService.getStoppedBooks(sellerId).size();
		Integer stoppedGeneralCountForSeller = generalService.getStoppedGeneral(sellerId).size();
		Integer stoppedFashionCountForSeller = fashionService.getStoppedFashion(sellerId).size();
		
		List<Integer> stoppedProductsCountForSeller = new ArrayList<Integer>();
		stoppedProductsCountForSeller.add(stoppedElectronicCountForSeller);
		stoppedProductsCountForSeller.add(stoppedBookCountForSeller);
		stoppedProductsCountForSeller.add(stoppedGeneralCountForSeller);
		stoppedProductsCountForSeller.add(stoppedFashionCountForSeller);
		String stoppedProductsCountJsonForSeller = JsonUtil.listToJSON(stoppedProductsCountForSeller);
		model.addAttribute("stoppedProductsCountJsonForSeller", stoppedProductsCountJsonForSeller);
		
		
		Integer requestedElectronicCountForSeller = electronicService.getRequestedElectronics(sellerId).size();
		Integer requestedBookCountForSeller = bookService.getRequestedBooks(sellerId).size();
		Integer requestedGeneralCountForSeller = generalService.getRequestedGeneral(sellerId).size();
		Integer requestedFashionCountForSeller = fashionService.getRequestedFashion(sellerId).size();
		
		List<Integer> requestedProductsCountForSeller = new ArrayList<Integer>();
		requestedProductsCountForSeller.add(requestedElectronicCountForSeller);
		requestedProductsCountForSeller.add(requestedBookCountForSeller);
		requestedProductsCountForSeller.add(requestedGeneralCountForSeller);
		requestedProductsCountForSeller.add(requestedFashionCountForSeller);
		String requestedProductsCountJsonForSeller = JsonUtil.listToJSON(requestedProductsCountForSeller);
		model.addAttribute("requestedProductsCountJsonForSeller", requestedProductsCountJsonForSeller);
		
		/* PIE BAR CHART */
		
		Integer electronicCountForSeller = electronicService.getApprovedAndStoppedElectronicsForSeller(sellerId).size();
		Integer bookCountForSeller = bookService.getApprovedAndStoppedBookForSeller(sellerId).size();
		Integer generalCountForSeller = generalService.getApprovedAndStoppedGeneralForSeller(sellerId).size();
		Integer fashionCountForSeller = fashionService.getApprovedAndStoppedFashion(sellerId).size();
		
		List<Integer> productCountForSeller = new ArrayList<Integer>();
		productCountForSeller.add(electronicCountForSeller);
		productCountForSeller.add(bookCountForSeller);
		productCountForSeller.add(generalCountForSeller);
		productCountForSeller.add(fashionCountForSeller);
		
		String productCountJsonForSeller = JsonUtil.listToJSON(productCountForSeller);
		model.addAttribute("productCountJsonForSeller", productCountJsonForSeller);
		
		return "dashboard";
	}
}
