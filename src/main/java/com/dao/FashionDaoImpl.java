package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Fashion;
import com.rowmapper.FashionRowMapper;

@Repository
public class FashionDaoImpl implements FashionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private FashionRowMapper fashionRowMapper;

	@Override
	public void save(Fashion fashion) {
		String query = "INSERT INTO fashion (fashion_type , fashion_buyertype , fashion_name , fashion_brand , fashion_color , fashion_description , fashion_images , seller_id , fashion_status) VALUES (?,?,?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { fashion.getFashionType(), fashion.getFashionBuyerType(),
				fashion.getFashionName(), fashion.getFashionBrand(), fashion.getFashionColor(),
				fashion.getFashionDescription(), fashion.getFashionImages(), fashion.getSeller().getSellerId(),
				fashion.getFashionStatus() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(Fashion fashion) {
		String query = "UPDATE fashion SET fashion_type=? , fashion_buyertype=? , fashion_name=? , fashion_brand=? , fashion_color=? , fashion_description=? , fashion_images=? , seller_id=? , fashion_status=? WHERE fashion_id=?";
		Object[] parameters = new Object[] { fashion.getFashionType(), fashion.getFashionBuyerType(),
				fashion.getFashionName(), fashion.getFashionBrand(), fashion.getFashionColor(),
				fashion.getFashionDescription(), fashion.getFashionImages(), fashion.getSeller().getSellerId(),
				fashion.getFashionStatus(), fashion.getFashionId() };
		jdbcTemplate.update(query, parameters);

	}

	@Override
	public void delete(Fashion fashion) {
		this.deleteById(fashion.getFashionId());
	}

	@Override
	public void deleteById(Integer fashionId) {
		String query = "DELETE FROM fashion WHERE fashion_id = ?";
		jdbcTemplate.update(query, fashionId);
	}

	@Override
	public List<Fashion> findAll() {
		String query = "SELECT * FROM fashion";
		List<Fashion> fashions = jdbcTemplate.query(query, fashionRowMapper);
		return fashions;
	}

	@Override
	public Fashion findById(Integer fashionId) {
		String query = "SELECT * FROM fashion WHERE fashion_id = ?";
		Object[] parameters = new Object[] { fashionId };
		Fashion fashion = jdbcTemplate.queryForObject(query, parameters, fashionRowMapper);
		return fashion;
	}

	@Override
	public List<Fashion> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM fashion WHERE " + propName + " =?";
		Object[] parameters = new Object[] { propValue };
		List<Fashion> fashions = jdbcTemplate.query(query, parameters, fashionRowMapper);
		return fashions;
	}

}
