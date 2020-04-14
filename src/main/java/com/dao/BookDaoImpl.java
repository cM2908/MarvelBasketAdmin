package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Book;
import com.rowmapper.BookRowMapper;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BookRowMapper bookRowMapper;

	@Override
	public void save(Book book) {
		String query = "INSERT INTO book (book_name , author_name , book_type , book_price , book_stock , book_description , book_images , seller_id , book_status) VALUES (?,?,?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { book.getBookName(), book.getAuthorName(), book.getBookType(),
				book.getBookPrice(), book.getBookStock(), book.getBookDescription(), book.getBookImages(),
				book.getSeller().getSellerId(), book.getBookStatus() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(Book book) {
		String query = "UPDATE book SET book_name=? , author_name=? , book_type=? , book_price=? , book_stock=? , book_description=? , book_images=? , seller_id=? , book_status=? WHERE book_id = ?";
		Object[] parameters = new Object[] { book.getBookName(), book.getAuthorName(), book.getBookType(),
				book.getBookPrice(), book.getBookStock(), book.getBookDescription(), book.getBookImages(),
				book.getSeller().getSellerId(), book.getBookStatus(), book.getBookId() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(Book book) {
		this.deleteById(book.getBookId());
	}

	@Override
	public void deleteById(Integer bookId) {
		String query = "DELETE FROM book WHERE book_id = ?";
		jdbcTemplate.update(query, bookId);
	}

	@Override
	public List<Book> findAll() {
		String query = "SELECT * FROM book";
		List<Book> books = jdbcTemplate.query(query, bookRowMapper);
		return books;
	}

	@Override
	public Book findById(Integer bookId) {
		String query = "SELECT * FROM book WHERE book_id = ?";
		Object[] parameters = new Object[] { bookId };
		Book book = jdbcTemplate.queryForObject(query, parameters, bookRowMapper);
		return book;
	}

	@Override
	public List<Book> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM book WHERE " + propName + " = ?";
		Object[] parameters = new Object[] { propValue };
		List<Book> books = jdbcTemplate.query(query, parameters, bookRowMapper);
		return books;
	}

}
