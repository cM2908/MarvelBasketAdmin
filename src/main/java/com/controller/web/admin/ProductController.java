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
import com.dao.BookDao;
import com.dao.ElectronicDao;
import com.dao.FashionDao;
import com.dao.GeneralDao;
import com.dao.SellerDao;
import com.service.BookService;
import com.service.ElectronicService;
import com.service.FashionService;
import com.service.GeneralService;

@Controller
public class ProductController {

	@Autowired
	ElectronicDao electronicDao;
	@Autowired
	BookDao bookDao;
	@Autowired
	GeneralDao generalDao;
	@Autowired
	FashionDao fashionDao;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	ElectronicService electronicService;
	@Autowired
	BookService bookService;
	@Autowired
	GeneralService generalService;
	@Autowired
	FashionService fashionService;

	/*----------------------------------------------- Common --------------------------------------------------------------------*/

	@RequestMapping(value = "/productrequests", method = RequestMethod.GET)
	public String productRequestTable(Model model) {
		
		List<Electronic> electronicList = electronicService.getRequestedElectronic();
		List<Book> bookList = bookService.getRequestedBook();
		List<General> generalList = generalService.getRequestedGeneral();
		List<FashionBean> fashionBeanList = fashionService.getRequestedFashion();
		
		model.addAttribute("electronicList", electronicList);
		model.addAttribute("electronicListSize", electronicList.size());
		model.addAttribute("bookList", bookList);
		model.addAttribute("bookListSize", bookList.size());
		model.addAttribute("generalList", generalList);
		model.addAttribute("generalListSize", generalList.size());
		model.addAttribute("fashionBeanList", fashionBeanList);
		model.addAttribute("fashionBeanListSize", fashionBeanList.size());
		
		return "product_requests";
	}

	/*----------------------------------------------- Electronic --------------------------------------------------------------------*/

	@RequestMapping(value = "/electronics", method = RequestMethod.GET)
	public String electronicsTable(Model model) {
		List<Electronic> electronicList = electronicService.getApprovedAndStoppedElectronics();
		model.addAttribute("electronicList", electronicList);
		return "electronic_table";
	}

	@RequestMapping(value = "/electronicdetails/{id}", method = RequestMethod.GET)
	public String electronicDetails(@PathVariable("id") Integer electronicId, Model model) {
		Electronic electronic = electronicDao.findById(electronicId);
		model.addAttribute("electronic", electronic);
		return "product_details";
	}

	@RequestMapping(value = "/electronicrequestdetails/{id}", method = RequestMethod.GET)
	public String electronicRequestDetails(@PathVariable("id") Integer electronicId, Model model) {
		Electronic electronic = electronicDao.findById(electronicId);
		model.addAttribute("electronic", electronic);
		return "product_request_details";
	}

	@RequestMapping(value = "/approveelectronic/{id}", method = RequestMethod.GET)
	public String approveElectronic(@PathVariable("id") Integer electronicId, RedirectAttributes redirectAttr) {
		Electronic electronic = electronicDao.findById(electronicId);
		Response<Electronic> response = electronicService.approveElectronic(electronic);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productApproveSuccess", "Electronic product approved successfully");
			return "redirect:/electronics";
		} else {
			redirectAttr.addFlashAttribute("productApproveError", "Can't approve electronic product");
			return "redirect:/electronicrequestdetails/" + electronicId;
		}
	}

	@RequestMapping(value = "/rejectelectronic/{id}", method = RequestMethod.GET)
	public String rejectElectronic(@PathVariable("id") Integer electronicId, RedirectAttributes redirectAttr) {
		Electronic electronic = electronicDao.findById(electronicId);
		Response<Electronic> response = electronicService.rejectElectronic(electronic);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productRejectSuccess", "Electronic product rejected successfully");
			return "redirect:/electronics";
		} else {
			redirectAttr.addFlashAttribute("productRejectError", "Can't reject electronic product");
			return "redirect:/electronicrequestdetails/" + electronicId;
		}
	}

	@RequestMapping(value = "/stopelectronic/{id}", method = RequestMethod.GET)
	public String stopElectronic(@PathVariable("id") Integer electronicId, RedirectAttributes redirectAttr) {
		Electronic electronic = electronicDao.findById(electronicId);
		Response<Electronic> response = electronicService.stopElectronic(electronic);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStopSuccess", "Electronic product stopped successfully");
		} else {
			redirectAttr.addFlashAttribute("productStopError", "Can't stop electronic product");
		}
		return "redirect:/electronicdetails/" + electronicId;
	}

	@RequestMapping(value = "/startelectronic/{id}", method = RequestMethod.GET)
	public String startElectronic(@PathVariable("id") Integer electronicId, RedirectAttributes redirectAttr) {
		Electronic electronic = electronicDao.findById(electronicId);
		Response<Electronic> response = electronicService.approveElectronic(electronic);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStartSuccess", "Electronic product started successfully");
		} else {
			redirectAttr.addFlashAttribute("productStartError", "Can't start electronic product");
		}
		return "redirect:/electronicdetails/" + electronicId;
	}

	/*-------------------------------------------  Book  -----------------------------------------------------------------------------*/

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String booksTable(Model model) {
		List<Book> bookList = bookService.getApprovedAndStoppedBooks();
		model.addAttribute("bookList", bookList);
		return "book_table";
	}

	@RequestMapping(value = "/bookdetails/{id}", method = RequestMethod.GET)
	public String bookDetails(@PathVariable("id") Integer bookId, Model model) {
		Book book = bookDao.findById(bookId);
		model.addAttribute("book", book);
		return "product_details";
	}

	@RequestMapping(value = "/bookrequestdetails/{id}", method = RequestMethod.GET)
	public String bookRequestDetails(@PathVariable("id") Integer bookId, Model model) {
		Book book = bookDao.findById(bookId);
		model.addAttribute("book", book);
		return "product_request_details";
	}

	@RequestMapping(value = "/approvebook/{id}", method = RequestMethod.GET)
	public String approveBook(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttr) {
		Book book = bookDao.findById(bookId);
		Response<Book> response = bookService.approveBook(book);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productApproveSuccess", "Book approved successfully");
			return "redirect:/books";
		} else {
			redirectAttr.addFlashAttribute("productApproveError", "Can't approve book");
			return "redirect:/bookrequestdetails/" + bookId;
		}
	}

	@RequestMapping(value = "/rejectbook/{id}", method = RequestMethod.GET)
	public String rejectBook(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttr) {
		Book book = bookDao.findById(bookId);
		Response<Book> response = bookService.rejectBook(book);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productRejectSuccess", "Book rejected successfully");
			return "redirect:/books";
		} else {
			redirectAttr.addFlashAttribute("productRejectError", "Can't reject book");
			return "redirect:/bookrequestdetails/" + bookId;
		}
	}

	@RequestMapping(value = "/stopbook/{id}", method = RequestMethod.GET)
	public String stopBook(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttr) {
		Book book = bookDao.findById(bookId);
		Response<Book> response = bookService.stopBook(book);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStopSuccess", "Book stopped successfully");
		} else {
			redirectAttr.addFlashAttribute("productStopError", "Can't stop book");
		}
		return "redirect:/bookdetails/" + bookId;
	}

	@RequestMapping(value = "/startbook/{id}", method = RequestMethod.GET)
	public String startBook(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttr) {
		Book book = bookDao.findById(bookId);
		Response<Book> response = bookService.approveBook(book);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStartSuccess", "Book started successfully");
		} else {
			redirectAttr.addFlashAttribute("productStartError", "Can't start book");
		}
		return "redirect:/bookdetails/" + bookId;
	}

	/*-------------------------------------------  General  -----------------------------------------------------------------------------*/

	@RequestMapping(value = "/general", method = RequestMethod.GET)
	public String generalTable(Model model) {
		List<General> generalList = generalService.getApprovedAndStoppedGeneral();
		model.addAttribute("generalList", generalList);
		return "general_table";
	}

	@RequestMapping(value = "/generaldetails/{id}", method = RequestMethod.GET)
	public String generalDetails(@PathVariable("id") Integer productId, Model model) {
		General general = generalDao.findById(productId);
		model.addAttribute("general", general);
		return "product_details";
	}

	@RequestMapping(value = "/generalrequestdetails/{id}", method = RequestMethod.GET)
	public String generalRequestDetails(@PathVariable("id") Integer productId, Model model) {
		General general = generalDao.findById(productId);
		model.addAttribute("general", general);
		return "product_request_details";
	}

	@RequestMapping(value = "/approvegeneral/{id}", method = RequestMethod.GET)
	public String approveGeneral(@PathVariable("id") Integer productId, RedirectAttributes redirectAttr) {
		General general = generalDao.findById(productId);
		Response<General> response = generalService.approveGeneral(general);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productApproveSuccess", "Product approved successfully");
			return "redirect:/general";
		} else {
			redirectAttr.addFlashAttribute("productApproveError", "Can't approve product");
			return "redirect:/generalrequestdetails/" + productId;
		}
	}

	@RequestMapping(value = "/rejectgeneral/{id}", method = RequestMethod.GET)
	public String rejectGeneral(@PathVariable("id") Integer productId, RedirectAttributes redirectAttr) {
		General general = generalDao.findById(productId);
		Response<General> response = generalService.rejectGeneral(general);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productRejectSuccess", "Product rejected successfully");
			return "redirect:/general";
		} else {
			redirectAttr.addFlashAttribute("productRejectError", "Can't reject product");
			return "redirect:/generalrequestdetails/" + productId;
		}
	}

	@RequestMapping(value = "/stopgeneral/{id}", method = RequestMethod.GET)
	public String stopGeneral(@PathVariable("id") Integer productId, RedirectAttributes redirectAttr) {
		General general = generalDao.findById(productId);
		Response<General> response = generalService.stopGeneral(general);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStopSuccess", "Product stopped successfully");
		} else {
			redirectAttr.addFlashAttribute("productStopError", "Can't stop product");
		}
		return "redirect:/generaldetails/" + productId;
	}

	@RequestMapping(value = "/startgeneral/{id}", method = RequestMethod.GET)
	public String startGeneral(@PathVariable("id") Integer productId, RedirectAttributes redirectAttr) {
		General general = generalDao.findById(productId);
		Response<General> response = generalService.approveGeneral(general);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStartSuccess", "Product started successfully");
		} else {
			redirectAttr.addFlashAttribute("productStartError", "Can't start product");
		}
		return "redirect:/generaldetails/" + productId;
	}

	/*-------------------------------------------  Book  -----------------------------------------------------------------------------*/

	@RequestMapping(value = "/fashion", method = RequestMethod.GET)
	public String fashionTable(Model model) {
		List<FashionBean> fashionBeanList = fashionService.getApprovedAndStoppedFashion();
		model.addAttribute("fashionBeanList", fashionBeanList);
		return "fashion_table";
	}

	@RequestMapping(value = "/fashiondetails/{id}", method = RequestMethod.GET)
	public String fashionDetails(@PathVariable("id") Integer fashionId, Model model) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		model.addAttribute("fashionBean", fashionBean);
		return "product_details";
	}

	@RequestMapping(value = "/fashionrequestdetails/{id}", method = RequestMethod.GET)
	public String fashionRequestDetails(@PathVariable("id") Integer fashionId, Model model) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		model.addAttribute("fashionBean", fashionBean);
		return "product_request_details";
	}

	@RequestMapping(value = "/approvefashion/{id}", method = RequestMethod.GET)
	public String approveFashion(@PathVariable("id") Integer fashionId, RedirectAttributes redirectAttr) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		Response<FashionBean> response = fashionService.approveFashion(fashionBean);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productApproveSuccess", "Fashion product approved successfully");
			return "redirect:/fashion";
		} else {
			redirectAttr.addFlashAttribute("productApproveError", "Can't approve fashion product");
			return "redirect:/fashionrequestdetails/" + fashionId;
		}
	}

	@RequestMapping(value = "/rejectfashion/{id}", method = RequestMethod.GET)
	public String rejectFashion(@PathVariable("id") Integer fashionId, RedirectAttributes redirectAttr) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		Response<FashionBean> response = fashionService.rejectFashion(fashionBean);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productRejectSuccess", "Fashion product rejected successfully");
			return "redirect:/fashion";
		} else {
			redirectAttr.addFlashAttribute("productRejectError", "Can't reject fashion product");
			return "redirect:/fashionrequestdetails/" + fashionId;
		}
	}

	@RequestMapping(value = "/stopfashion/{id}", method = RequestMethod.GET)
	public String stopFashion(@PathVariable("id") Integer fashionId, RedirectAttributes redirectAttr) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		Response<FashionBean> response = fashionService.stopFashion(fashionBean);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStopSuccess", "Fashion product stopped successfully");
		} else {
			redirectAttr.addFlashAttribute("productStopError", "Can't stop fashion product");
		}
		return "redirect:/fashiondetails/" + fashionId;
	}

	@RequestMapping(value = "/startfashion/{id}", method = RequestMethod.GET)
	public String startFashion(@PathVariable("id") Integer fashionId, RedirectAttributes redirectAttr) {
		FashionBean fashionBean = fashionService.getFashionById(fashionId);
		Response<FashionBean> response = fashionService.approveFashion(fashionBean);
		if (response.getCode() == 1061) {
			redirectAttr.addFlashAttribute("productStartSuccess", "Fashion product started successfully");
		} else {
			redirectAttr.addFlashAttribute("productStartError", "Can't start fashion product");
		}
		return "redirect:/fashiondetails/" + fashionId;
	}
	
	/*-------------------------------------------  Seller wise  -----------------------------------------------------------------------------*/

	@RequestMapping(value = "/sellerproducts/{id}", method = RequestMethod.GET)
	public String sellerProducts(@PathVariable("id") Integer sellerId, Model model) {

		Seller seller = sellerDao.findById(sellerId);
		List<Electronic> electronicList = electronicDao.findByProperty("seller_id", sellerId);
		List<Book> bookList = bookDao.findByProperty("seller_id", sellerId);
		List<General> generalList = generalDao.findByProperty("seller_id", sellerId);
		List<FashionBean> fashionBeanList = fashionService.getAllFashion(sellerId);
		
		model.addAttribute("seller", seller);
		model.addAttribute("electronicList", electronicList);
		model.addAttribute("electronicListSize", electronicList.size());
		model.addAttribute("bookList", bookList);
		model.addAttribute("bookListSize", bookList.size());
		model.addAttribute("generalList", generalList);
		model.addAttribute("generalListSize", generalList.size());
		model.addAttribute("fashionBeanList", fashionBeanList);
		model.addAttribute("fashionBeanListSize", fashionBeanList.size());
		
		return "seller_products";
	}
}