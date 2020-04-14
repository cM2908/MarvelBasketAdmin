package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.SubFashion;
import com.dao.FashionDao;

@Component
public class SubFashionRowMapper implements RowMapper<SubFashion> {

	@Autowired
	FashionDao fashionDao;
	
	@Override
	public SubFashion mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SubFashion subFashion = new SubFashion();
		
		subFashion.setSubfashionId(rs.getInt("subfashion_id"));
		subFashion.setFashionSize(rs.getString("fashion_size"));
		subFashion.setFashionStock(rs.getLong("fashion_stock"));
		subFashion.setFashionPrice(rs.getDouble("fashion_price"));
		
		return subFashion;
	}

}
