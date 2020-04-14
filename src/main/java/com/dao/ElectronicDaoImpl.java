package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Electronic;
import com.rowmapper.ElectronicRowMapper;

@Repository
public class ElectronicDaoImpl implements ElectronicDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ElectronicRowMapper electronicRowMapper;

	@Override
	public void save(Electronic electronic) {
		String query = "INSERT INTO electronic (electronic_type , electronic_name , electronic_brand , electronic_description , electronic_price , electronic_stock , electronic_size , electronic_color , electronic_weight , electronic_specification , electronic_images , seller_id , electronic_status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { electronic.getElectronicType(), electronic.getElectronicName(),
				electronic.getElectronicBrand(), electronic.getElectronicDescription(), electronic.getElectronicPrice(),
				electronic.getElectronicStock(), electronic.getElectronicSize(), electronic.getElectronicColor(),
				electronic.getElectronicWeight(), electronic.getElectronicSpecification(),
				electronic.getElectronicImages(), electronic.getSeller().getSellerId(),
				electronic.getElectronicStatus() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(Electronic electronic) {
		String query = "UPDATE electronic SET electronic_type=? , electronic_name=? , electronic_brand=? , electronic_description=? , electronic_price=? , electronic_stock=? , electronic_size=? , electronic_color=? , electronic_weight=? , electronic_specification=? , electronic_images=? , seller_id=? , electronic_status=? WHERE electronic_id=?";
		Object[] parameters = new Object[] { electronic.getElectronicType(), electronic.getElectronicName(),
				electronic.getElectronicBrand(), electronic.getElectronicDescription(), electronic.getElectronicPrice(),
				electronic.getElectronicStock(), electronic.getElectronicSize(), electronic.getElectronicColor(),
				electronic.getElectronicWeight(), electronic.getElectronicSpecification(),
				electronic.getElectronicImages(), electronic.getSeller().getSellerId(),
				electronic.getElectronicStatus(), electronic.getElectronicId() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(Electronic electronic) {
		this.deleteById(electronic.getElectronicId());
	}

	@Override
	public void deleteById(Integer electronicId) {
		String query = "DELETE FROM electronic WHERE electronic_id = ?";
		jdbcTemplate.update(query, electronicId);
	}

	@Override
	public List<Electronic> findAll() {
		String query = "SELECT * FROM electronic";
		List<Electronic> electronics = jdbcTemplate.query(query, electronicRowMapper);
		return electronics;
	}

	@Override
	public Electronic findById(Integer electronicId) {
		String query = "SELECT * FROM electronic WHERE electronic_id = ?";
		Object[] parameters = new Object[] { electronicId };
		Electronic electronic = jdbcTemplate.queryForObject(query, parameters, electronicRowMapper);
		return electronic;
	}

	@Override
	public List<Electronic> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM electronic WHERE " + propName + " =?";
		Object[] parameters = new Object[] { propValue };
		List<Electronic> electronics = jdbcTemplate.query(query, parameters, electronicRowMapper);
		return electronics;
	}

}
