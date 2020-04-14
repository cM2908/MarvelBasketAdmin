package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AdminNotification;
import com.rowmapper.AdminNotificationRowMapper;

@Repository
public class AdminNotificationDaoImpl implements AdminNotificationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	AdminNotificationRowMapper adminNotificationRowMapper;
	
	@Override
	public void save(AdminNotification adminNotification) {
		String query = "INSERT INTO adminnotification (from_id,visibility,description) VALUES (?,?,?)";
		Object[] parameters = new Object[]{adminNotification.getFrom().getSellerId(),adminNotification.getVisibility(),adminNotification.getDescription()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(AdminNotification adminNotification) {
		String query = "UPDATE adminnotification SET from_id = ?, time = ?,visibility = ?,description = ? WHERE notification_id = ?";
		Object[] parameters = new Object[]{adminNotification.getFrom().getSellerId(),adminNotification.getTime(),adminNotification.getVisibility(),adminNotification.getDescription(),adminNotification.getNotificationId()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(AdminNotification adminNotification) {
		this.deleteById(adminNotification.getNotificationId());
	}

	@Override
	public void deleteById(Integer adminNotificationId) {	
		String query = "DELETE FROM adminnotification WHERE notification_id = ?";
		jdbcTemplate.update(query,adminNotificationId);
	}

	@Override
	public List<AdminNotification> findAll() {
		String query = "SELECT * FROM adminnotification";
		List<AdminNotification> notifications = jdbcTemplate.query(query,adminNotificationRowMapper);
		return notifications;
	}

	@Override
	public AdminNotification findById(Integer adminNotificationId) {
		String query = "SELECT * FROM adminnotification WHERE notification_id = ?";
		Object[] parameters = new Object[] {adminNotificationId};
		AdminNotification adminNotification = jdbcTemplate.queryForObject(query,parameters,adminNotificationRowMapper);
		return adminNotification;
	}

	@Override
	public List<AdminNotification> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM adminnotification WHERE " + propName + " = ?";
		Object[] parameters = new Object[] {propValue};
		List<AdminNotification> notifications = jdbcTemplate.query(query, parameters,adminNotificationRowMapper);
		return notifications;
	}

}
