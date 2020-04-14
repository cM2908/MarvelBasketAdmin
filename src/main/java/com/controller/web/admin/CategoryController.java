package com.controller.web.admin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Category;
import com.comparator.CategoryLengthComparator;
import com.dao.CategoryDao;
import com.service.SellerCategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SellerCategoryService sellerCategoryService;

	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String allCategories(Model model) {
		List<Category> categories = categoryDao.findAll();
		Map<Category,Long> map = new HashMap<>();
		for (Category c : categories) {
			Long sellers = sellerCategoryService.sellersOfCategory(c.getcategoryId());
			map.put(c, sellers);
		}
		Collections.sort(categories, new CategoryLengthComparator());
		model.addAttribute("map", map);

		Category category = new Category();
		model.addAttribute("categoryCommand", category);
		return "category";
	}

	@RequestMapping(value = "/admin/addcategory", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("categoryCommand") Category c, RedirectAttributes redirectAttr) {
		List<Category> categories = categoryDao.findByProperty("category_name", c.getcategoryName().trim());
		if (categories.size() > 0) {
			redirectAttr.addFlashAttribute("categoryError", "Category '" + c.getcategoryName() + "' already exists");
		} else {
			categoryDao.save(c);
			redirectAttr.addFlashAttribute("categorySuccess",
					"Category '" + c.getcategoryName() + "' added succesfully");
		}
		return "redirect:/admin/category";
	}
}
