package com.bean;

import java.util.List;

public class FashionBean {

	private Fashion fashion;
	private List<SubFashion> subFashion;

	public FashionBean() {
		// Do Nothing..
	}

	public FashionBean(Fashion fashion, List<SubFashion> subFashion) {
		super();
		this.fashion = fashion;
		this.subFashion = subFashion;
	}

	public List<SubFashion> getSubFashion() {
		return subFashion;
	}

	public void setSubFashion(List<SubFashion> subFashion) {
		this.subFashion = subFashion;
	}

	public Fashion getFashion() {
		return fashion;
	}

	public void setFashion(Fashion fashion) {
		this.fashion = fashion;
	}

}
