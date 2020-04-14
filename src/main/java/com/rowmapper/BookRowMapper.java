package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Book;
import com.bean.Seller;
import com.dao.SellerDao;

@Component
public class BookRowMapper implements RowMapper<Book> {

	@Autowired
	SellerDao sellerDao;
	
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Book book = new Book();
		
		book.setBookId(rs.getInt("book_id"));
		book.setBookName(rs.getString("book_name"));
		book.setAuthorName(rs.getString("author_name"));
		book.setBookType(rs.getString("book_type"));
		book.setBookPrice(rs.getDouble("book_price"));
		book.setBookStock(rs.getLong("book_stock"));
		book.setBookDescription(rs.getString("book_description"));
		book.setBookImages(rs.getString("book_images"));
		book.setBookStatus(rs.getString("book_status"));
		
		int sellerId = rs.getInt("seller_id");
		Seller seller = sellerDao.findById(sellerId);
		book.setSeller(seller);
		
		return book;
	}

	
}
