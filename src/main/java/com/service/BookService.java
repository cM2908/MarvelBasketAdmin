package com.service;

import java.util.List;

import com.bean.Book;
import com.bean.Response;

public interface BookService {
	
	public static final String PRODUCT_REQUESTED = "REQUESTED";
	public static final String PRODUCT_APPROVED = "APPROVED";
	public static final String PRODUCT_STOPPED = "STOPPED";
	
	public Response<Book> addBook(Book book);	
	public List<Book> getRequestedBook();
	public Response<Book> approveBook(Book book);
	public Response<Book> rejectBook(Book book);
	public Response<Book> stopBook(Book book);
	public List<Book> getApprovedAndStoppedBooks();
	public List<Book> getApprovedBooks();
	public List<Book> getStoppedBooks();
	public List<Book> getApprovedBooks(Integer sellerId);
	public List<Book> getStoppedBooks(Integer sellerId);
	public List<Book> getRequestedBooks(Integer sellerId);
	public List<Book> getApprovedAndStoppedBookForSeller(Integer sellerId);

}
