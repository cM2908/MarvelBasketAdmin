package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Category;
import com.dao.CategoryDao;

@RestController
public class CategoryRestController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value="/category/find", method=RequestMethod.GET)
	public List<Category> findCategory()
	{
		return categoryDao.findAll();
	}
}
