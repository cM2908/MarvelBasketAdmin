package com.service;

import java.util.List;

import com.bean.General;
import com.bean.Response;

public interface GeneralService {

	public static final String PRODUCT_REQUESTED = "REQUESTED";
	public static final String PRODUCT_APPROVED = "APPROVED";
	public static final String PRODUCT_STOPPED = "STOPPED";
	
	public Response<General> addGeneral(General general);	
	public List<General> getRequestedGeneral();
	public Response<General> approveGeneral(General general);
	public Response<General> rejectGeneral(General general);
	public Response<General> stopGeneral(General general);
	public List<General> getApprovedAndStoppedGeneral();
	public List<General> getApprovedGeneral();
	public List<General> getStoppedGeneral();
	public List<General> getApprovedGeneral(Integer sellerId);
	public List<General> getStoppedGeneral(Integer sellerId);
	public List<General> getRequestedGeneral(Integer sellerId);
	public List<General> getApprovedAndStoppedGeneralForSeller(Integer sellerId);
}
