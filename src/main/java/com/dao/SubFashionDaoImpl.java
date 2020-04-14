package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SubFashion;
import com.rowmapper.SubFashionRowMapper;

@Repository
public class SubFashionDaoImpl implements SubFashionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SubFashionRowMapper subFashionRowMapper;

	@Override
	public void save(SubFashion subFashion, Integer fashionId) {
		String query = "INSERT INTO subfashion (fashion_id , fashion_size , fashion_stock , fashion_price) VALUES (?,?,?,?)";
		Object[] parameters = new Object[] { fashionId, subFashion.getFashionSize(), subFashion.getFashionStock(),
				subFashion.getFashionPrice() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(SubFashion subFashion) {
		String query = "UPDATE subfashion SET fashion_size=? , fashion_stock=? , fashion_price=? WHERE subfashion_id=?";
		Object[] parameters = new Object[] { subFashion.getFashionSize(), subFashion.getFashionStock(),
				subFashion.getFashionPrice(), subFashion.getSubfashionId() };
		jdbcTemplate.update(query, parameters);

	}

	@Override
	public void delete(SubFashion subFashion) {
		this.deleteById(subFashion.getSubfashionId());
	}

	@Override
	public void deleteById(Integer subFashionId) {
		String query = "DELETE FROM subfashion WHERE subfashion_id = ?";
		jdbcTemplate.update(query, subFashionId);
	}

	@Override
	public List<SubFashion> findAll() {
		String query = "SELECT * FROM subfashion";
		List<SubFashion> subfashions = jdbcTemplate.query(query, subFashionRowMapper);
		return subfashions;
	}

	@Override
	public SubFashion findById(Integer subFashionId) {
		String query = "SELECT * FROM subfashion WHERE subfashion_id = ?";
		Object[] parameters = new Object[] { subFashionId };
		SubFashion subfashion = jdbcTemplate.queryForObject(query, parameters, subFashionRowMapper);
		return subfashion;
	}

	@Override
	public List<SubFashion> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM subfashion WHERE " + propName + " = ?";
		Object[] parameters = new Object[] { propValue };
		List<SubFashion> subfashions = jdbcTemplate.query(query, parameters, subFashionRowMapper);
		return subfashions;
	}

}
