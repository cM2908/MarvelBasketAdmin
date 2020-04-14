package com.service;

import java.util.List;

import com.bean.Electronic;
import com.bean.Response;

public interface ElectronicService {
	
	public static final String PRODUCT_REQUESTED = "REQUESTED";
	public static final String PRODUCT_APPROVED = "APPROVED";
	public static final String PRODUCT_STOPPED = "STOPPED";
	
	public Response<Electronic> addElectronic(Electronic electronic);	
	public Response<Electronic> approveElectronic(Electronic electronic);
	public Response<Electronic> rejectElectronic(Electronic electronic);
	public Response<Electronic> stopElectronic(Electronic electronic);
	
	public List<Electronic> getApprovedElectronics();
	public List<Electronic> getStoppedElectronics();
	public List<Electronic> getRequestedElectronic();
	public List<Electronic> getApprovedAndStoppedElectronics();
	
	public List<Electronic> getApprovedElectronics(Integer sellerId);
	public List<Electronic> getStoppedElectronics(Integer sellerId);
	public List<Electronic> getRequestedElectronics(Integer sellerId);
	public List<Electronic> getApprovedAndStoppedElectronicsForSeller(Integer sellerId);
	
}
