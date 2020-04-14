package com.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.bean.Category;

@Component
public class CategoryLengthComparator implements Comparator<Category> {

	@Override
	public int compare(Category c1, Category c2) {
		if (c1.getcategoryName().length() >= c2.getcategoryName().length()) {
			return 1;
		} else {
			return -1;
		}
	}

}
