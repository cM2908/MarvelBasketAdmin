package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Book;
import com.bean.Response;
import com.bean.Seller;
import com.dao.BookDao;
import com.dao.SellerDao;
import com.rowmapper.BookRowMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	BookRowMapper bookRowMapper;
	@Autowired
	BookDao bookDao;
	@Autowired
	AdminNotificationService adminNotificationService;
	@Autowired
	SellerDao sellerDao;
	@Value("${project.path}")
	private String projectPath;
	@Value("${project.name}")
	private String projectName;

	@Override
	public Response<Book> addBook(Book book) {
		String query = "SELECT * FROM book WHERE book_name = ? and book_type = ? and author_name = ? and book_price = ? and seller_id = ?";
		Object[] parameters = new Object[] { book.getBookName(), book.getBookType(), book.getAuthorName(), book.getBookPrice(), book.getSeller().getSellerId()};
		List<Book> bookList = jdbcTemplate.query(query, parameters, bookRowMapper);
		Seller seller = sellerDao.findById(book.getSeller().getSellerId());
		if (!seller.getSellerStatus().equals(SellerService.STATUS_APPROVED)) {
			System.out.println("if");
			return new Response<>(null, "Seller not approved", 1053);
		} else if (bookList.size() > 0) {
			System.out.println("else if");
			return new Response<>(book, "Book already exits", 1052);
		} else {
			System.out.println("else");
			// Get Encoded Image String
			String encodedImages = book.getBookImages();

			// Change Status and Image Field
			book.setBookStatus(PRODUCT_REQUESTED);
			book.setBookImages("prod1;prod2;prod3;prod4;prod5");

			// Save Book data into DATA-BASE
			bookDao.save(book);

			// Retrieving Book Record from database for BookId
			List<Book> bookListTemp = jdbcTemplate.query(query, parameters, bookRowMapper);

			// save Images into FILE-SYSTEM
			addProductImagesToFileSystem(encodedImages, seller.getSellerEmail(), bookListTemp.get(0).getBookId());

			// Send notification to admin
			adminNotificationService.addNotificationForProductRequest(bookListTemp.get(0));

			return new Response<>(bookListTemp.get(0), "Book added , wait for approval", 1051);
		}
	}

	@Override
	public Response<Book> approveBook(Book book) {
		if (book != null) {
			book.setBookStatus(PRODUCT_APPROVED);
			bookDao.update(book);
			return new Response<>(book, "Approve Success", 1061);
		} else {
			return new Response<>(book, "Approve Error", 1062);
		}
	}

	private void addProductImagesToFileSystem(String encodedString, String sellerEmail, Integer productId) {
		String[] imageArr = encodedString.split(";");
		int count = 1;
		for (String image : imageArr) {

			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ sellerEmail.trim() + "\\" + "book" + "\\" + productId);
			imagePath.mkdirs();

			byte[] bytes = Base64.decodeBase64(image);
			try {
				FileOutputStream fos = new FileOutputStream(imagePath + "\\prod" + count + ".jpg");
				fos.write(bytes);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

	@Override
	public Response<Book> rejectBook(Book book) {
		if (book != null) {
			// delete book record from DATA-BASE
			bookDao.delete(book);
			// delete book images from FILE-SYSTEM
			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ book.getSeller().getSellerEmail().trim() + "\\" + "book" + "\\" + book.getBookId());
			try {
				System.out.println(imagePath);
				FileUtils.deleteDirectory(imagePath);
				return new Response<>(book, "Reject Success", 1061);
			} catch (IOException e) {
				e.printStackTrace();
				return new Response<>(book, "Reject Error", 1062);
			}
		} else {
			return new Response<>(book, "Reject Error", 1062);
		}
	}

	@Override
	public Response<Book> stopBook(Book book) {
		if (book != null) {
			book.setBookStatus(PRODUCT_STOPPED);
			bookDao.update(book);
			return new Response<>(book, "Stop Success", 1061);
		} else {
			return new Response<>(book, "Stop Error", 1062);
		}
	}

	@Override
	public List<Book> getApprovedAndStoppedBooks() {
		List<Book> bookList = bookDao.findByProperty("book_status", PRODUCT_APPROVED);
		List<Book> stoppedBookList = bookDao.findByProperty("book_status", PRODUCT_STOPPED);
		bookList.addAll(stoppedBookList);
		return bookList;
	}

	@Override
	public List<Book> getApprovedBooks() {
		List<Book> bookList = bookDao.findByProperty("book_status", PRODUCT_APPROVED);
		return bookList;
	}
	
	@Override
	public List<Book> getStoppedBooks() {
		List<Book> stoppedBookList = bookDao.findByProperty("book_status", PRODUCT_STOPPED);
		return stoppedBookList;
	}

	@Override
	public List<Book> getRequestedBook() {
		List<Book> bookList = bookDao.findByProperty("book_status", BookService.PRODUCT_REQUESTED);
		return bookList;
	}
	
	@Override
	public List<Book> getApprovedBooks(Integer sellerId) {
		String query = "SELECT * FROM book WHERE book_status = ? and seller_id = ?";
		Object[] parameters = new Object[] {"APPROVED",sellerId};
		List<Book> approvedBookListForSeller = jdbcTemplate.query(query,parameters,bookRowMapper);
		return approvedBookListForSeller;
	}

	@Override
	public List<Book> getStoppedBooks(Integer sellerId) {
		String query = "SELECT * FROM book WHERE book_status = ? and seller_id = ?";
		Object[] parameters = new Object[] {"STOPPED",sellerId};
		List<Book> stoppedBookListForSeller = jdbcTemplate.query(query,parameters,bookRowMapper);
		return stoppedBookListForSeller;
	}
	@Override
	public List<Book> getRequestedBooks(Integer sellerId) {
		String query = "SELECT * FROM book WHERE seller_id = ? and book_status = ?";
		Object[] parameters = new Object[] {sellerId,PRODUCT_REQUESTED};
		List<Book> requestedBookListForSeller = jdbcTemplate.query(query,parameters,bookRowMapper);
		return requestedBookListForSeller;
	}
	
	@Override
	public List<Book> getApprovedAndStoppedBookForSeller(Integer sellerId) {
		String query = "SELECT * FROM book WHERE seller_id = ? and ( book_status = ? or book_status = ?  )";
		Object[] parameters = new Object[] {sellerId,"APPROVED","STOPPED"};
		List<Book> bookListForSeller = jdbcTemplate.query(query,parameters,bookRowMapper);
		return bookListForSeller;
	}
	
}
