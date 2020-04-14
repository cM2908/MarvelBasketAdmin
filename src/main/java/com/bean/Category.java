package com.bean;

public class Category {
	
	int categoryId;
	String categoryName;
	
	public Category(){
		//Do nothing
	}
	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public int getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getcategoryName() {
		return categoryName;
	}
	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        if (!(obj instanceof Category)) {
	            return false;
	        }
	        Category otherMember = (Category)obj;
	        return otherMember.getcategoryName().equals(this.categoryName);
	    }
}
