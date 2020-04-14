package com.controller.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Book;
import com.bean.Electronic;
import com.bean.FashionBean;
import com.bean.General;
import com.bean.Response;
import com.bean.Seller;
import com.bean.SellerCategory;
import com.dao.BookDao;
import com.dao.ElectronicDao;
import com.dao.FashionDao;
import com.dao.GeneralDao;
import com.dao.SellerDao;
import com.service.BookService;
import com.service.ElectronicService;
import com.service.FashionService;
import com.service.GeneralService;
import com.service.SellerCategoryService;
import com.service.SellerService;

@Controller
public class SellerController {

	@Autowired
	SellerService sellerService;
	@Autowired
	SellerCategoryService sellerCategoryService;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	ElectronicDao electronicDao;
	@Autowired
	BookDao bookDao;
	@Autowired
	GeneralDao generalDao;
	@Autowired
	FashionDao fashionDao;
	@Autowired
	ElectronicService electronicService;
	@Autowired
	BookService bookService;
	@Autowired
	GeneralService generalService;
	@Autowired
	FashionService fashionService;

	@RequestMapping(value = "/sellers", method = RequestMethod.GET)
	public String sellerTable(Model model) {
		List<Seller> sellerList = sellerService.getApprovedAndStoppedSellers();
		model.addAttribute("sellerList", sellerList);
		return "sellers_table";
	}

	@RequestMapping(value = "/sellerdetails/{id}", method = RequestMethod.GET)
	public String sellerDetails(@PathVariable("id") Integer sellerId, Model model) {

		Seller seller = sellerDao.findById(sellerId);
		SellerCategory sellerCategory = sellerCategoryService.findBySellerId(sellerId);

		if (sellerCategory != null && sellerCategory.getCategory() != null) {

			List<Electronic> electronicList = electronicDao.findByProperty("seller_id", sellerId);
			List<Book> bookList = bookDao.findByProperty("seller_id", sellerId);
			List<General> generalList = generalDao.findByProperty("seller_id", sellerId);
			List<FashionBean> fashionBeanList = fashionService.getAllFashion(sellerId); 
			
			model.addAttribute("categories", sellerCategory.getCategory());
			model.addAttribute("numOfCategory", sellerCategory.getCategory().size());
			model.addAttribute("productCount", electronicList.size() + bookList.size() + generalList.size());
			model.addAttribute("electronicCount", electronicList.size());
			model.addAttribute("bookCount", bookList.size());
			model.addAttribute("generalCount", generalList.size());
			model.addAttribute("fashionCount", fashionBeanList.size());

		} else {
			model.addAttribute("numOfCategory", 0);
		}
		model.addAttribute("seller", seller);
		return "seller_details";
	}

	@RequestMapping(value = "/stopselleraccount/{id}", method = RequestMethod.GET)
	public String stopSellerAccount(@PathVariable("id") Integer sellerId, RedirectAttributes redirectAttr) {
		Seller seller = sellerDao.findById(sellerId);
		Response<Seller> response = sellerService.stopSellerAccount(seller);
		if (response.getCode() == 1011) {
			redirectAttr.addFlashAttribute("accountStopSuccess", "Seller account stopped successfully");
		} else {
			redirectAttr.addFlashAttribute("accountStopError", "Can't stop Seller account");
		}
		return "redirect:/sellerdetails/" + sellerId;
	}

	@RequestMapping(value = "/startselleraccount/{id}", method = RequestMethod.GET)
	public String startSellerAccount(@PathVariable("id") Integer sellerId, RedirectAttributes redirectAttr) {
		Seller seller = sellerDao.findById(sellerId);
		Response<Seller> response = sellerService.approveSeller(seller);
		if (response.getCode() == 1011) {
			redirectAttr.addFlashAttribute("accountStartSuccess", "Seller account started successfully");
		} else {
			redirectAttr.addFlashAttribute("accountStartError", "Can't start Seller account");
		}
		return "redirect:/sellerdetails/" + sellerId;
	}

	@RequestMapping(value = "/approveselleraccount/{id}", method = RequestMethod.GET)
	public String approveSellerAccount(@PathVariable("id") Integer sellerId, RedirectAttributes redirectAttr) {
		Seller seller = sellerDao.findById(sellerId);
		Response<Seller> response = sellerService.approveSeller(seller);
		if (response.getCode() == 1011) {
			redirectAttr.addFlashAttribute("accountApproveSuccess", "Seller approve successfully");
			return "redirect:/sellers";
		} else {
			redirectAttr.addFlashAttribute("accountApproveError", "Can't approve Seller");
			return "redirect:/sellerrequestdetails/" + sellerId;
		}
	}

	@RequestMapping(value = "/rejectselleraccount/{id}", method = RequestMethod.GET)
	public String rejectSellerAccount(@PathVariable("id") Integer sellerId, RedirectAttributes redirectAttr) {
		Seller seller = sellerDao.findById(sellerId);
		Response<String> response = sellerService.rejectSeller(seller);
		if (response.getCode() == 1011) {
			redirectAttr.addFlashAttribute("accountRejectSuccess", "Seller rejected successfully");
			return "redirect:/sellers";
		} else {
			redirectAttr.addFlashAttribute("accountRejectError", "Can't reject Seller");
			return "redirect:/sellerrequestdetails/" + sellerId;
		}
	}

	@RequestMapping(value = "/sellerrequests", method = RequestMethod.GET)
	public String sellerRequestTable(Model model) {
		List<Seller> sellerList = sellerService.getRequsetedSellers();
		model.addAttribute("sellerList", sellerList);
		model.addAttribute("sellerListSize", sellerList.size());
		return "sellers_requests";
	}

	@RequestMapping(value = "/sellerrequestdetails/{id}", method = RequestMethod.GET)
	public String sellerRequestDetails(@PathVariable("id") Integer sellerId, Model model) {
		Seller seller = sellerDao.findById(sellerId);
		SellerCategory sellerCategory = sellerCategoryService.findBySellerId(sellerId);
		if (sellerCategory != null && sellerCategory.getCategory() != null) {
			model.addAttribute("categories", sellerCategory.getCategory());
			model.addAttribute("numOfCategory", sellerCategory.getCategory().size());
		} else {
			model.addAttribute("numOfCategory", 0);
		}
		model.addAttribute("seller", seller);
		return "seller_request_details";
	}
}
