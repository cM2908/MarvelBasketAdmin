package com.service;

import java.util.List;

import com.bean.FashionBean;
import com.bean.Response;

public interface FashionService {
	
	public static final String PRODUCT_REQUESTED = "REQUESTED";
	public static final String PRODUCT_APPROVED = "APPROVED";
	public static final String PRODUCT_STOPPED = "STOPPED";
	
	public Response<FashionBean> addFashion(FashionBean fashionBean);
	public Response<FashionBean> approveFashion(FashionBean fashionBean);
	public Response<FashionBean> rejectFashion(FashionBean fashionBean);
	public Response<FashionBean> stopFashion(FashionBean fashionBean);
	
	public List<FashionBean> getApprovedFashion();
	public List<FashionBean> getStoppedFashion();
	public List<FashionBean> getRequestedFashion();
	public List<FashionBean> getApprovedAndStoppedFashion();
	public List<FashionBean> getAllFashion();
	public FashionBean getFashionById(Integer fashionId);
	
	public List<FashionBean> getApprovedFashion(Integer sellerId);
	public List<FashionBean> getStoppedFashion(Integer sellerId);
	public List<FashionBean> getRequestedFashion(Integer sellerId);
	public List<FashionBean> getApprovedAndStoppedFashion(Integer sellerId);
	public List<FashionBean> getAllFashion(Integer sellerId);
	
}
