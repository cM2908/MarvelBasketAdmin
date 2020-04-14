package com.dao;

import java.util.List;

import com.bean.Book;

public interface BookDao {
	
	public void save(Book book);
	public void update(Book book);
	public void delete(Book book);
	public void deleteById(Integer bookId);
	public List<Book> findAll();
	public Book findById(Integer bookId);
	public List<Book> findByProperty(String propName , Object propValue);
	
}
