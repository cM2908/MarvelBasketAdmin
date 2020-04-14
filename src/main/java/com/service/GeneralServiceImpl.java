package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.General;
import com.bean.Response;
import com.bean.Seller;
import com.dao.GeneralDao;
import com.dao.SellerDao;
import com.rowmapper.GeneralRowMapper;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	GeneralRowMapper generalRowMapper;
	@Autowired
	GeneralDao generalDao;
	@Autowired
	AdminNotificationService adminNotificationService;
	@Autowired
	SellerDao sellerDao;
	@Value("${project.path}")
	private String projectPath;
	@Value("${project.name}")
	private String projectName;

	@Override
	public Response<General> addGeneral(General general) {
		String query = "SELECT * FROM general WHERE product_name = ? and product_brand = ? and product_type = ? and product_price = ? and seller_id = ?";
		Object[] parameters = new Object[] { general.getProductName(), general.getProductBrand(), general.getProductType(), general.getProductPrice(), general.getSeller().getSellerId()};
		List<General> generalList = jdbcTemplate.query(query, parameters, generalRowMapper);
		Seller seller = sellerDao.findById(general.getSeller().getSellerId());
		if (!seller.getSellerStatus().equals(SellerService.STATUS_APPROVED)) {
			System.out.println("if");
			return new Response<>(null, "Seller not approved", 1053);
		} else if (generalList.size() > 0) {
			System.out.println("else if");
			return new Response<>(general, "General product already exits", 1052);
		} else {
			System.out.println("else");
			// Get Encoded Image String
			String encodedImages = general.getProductImages();

			// Change Status and Image Field
			general.setProductStatus(PRODUCT_REQUESTED);
			general.setProductImages("prod1;prod2;prod3;prod4;prod5");

			// Save General data into DATA-BASE
			generalDao.save(general);

			// Retrieving General Record from database for GeneralId
			List<General> generalListTemp = jdbcTemplate.query(query, parameters, generalRowMapper);

			// save Images into FILE-SYSTEM
			addProductImagesToFileSystem(encodedImages, seller.getSellerEmail(), generalListTemp.get(0).getProductId());

			// Send notification to admin
			adminNotificationService.addNotificationForProductRequest(generalListTemp.get(0));

			return new Response<>(generalListTemp.get(0), "General product added , wait for approval", 1051);
		}
	}

	@Override
	public List<General> getRequestedGeneral() {
		List<General> generalList = generalDao.findByProperty("product_status", GeneralService.PRODUCT_REQUESTED);
		return generalList;
	}

	@Override
	public Response<General> approveGeneral(General general) {
		if (general != null) {
			general.setProductStatus(PRODUCT_APPROVED);
			generalDao.update(general);
			return new Response<>(general, "Approve Success", 1061);
		} else {
			return new Response<>(general, "Approve Error", 1062);
		}
	}

	private void addProductImagesToFileSystem(String encodedString, String sellerEmail, Integer productId) {
		String[] imageArr = encodedString.split(";");
		int count = 1;
		for (String image : imageArr) {

			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ sellerEmail.trim() + "\\" + "general" + "\\" + productId);
			imagePath.mkdirs();

			byte[] bytes = Base64.decodeBase64(image);
			try {
				FileOutputStream fos = new FileOutputStream(imagePath + "\\prod" + count + ".jpg");
				fos.write(bytes);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

	@Override
	public Response<General> rejectGeneral(General general) {
		if (general != null) {
			// delete general product record from DATA-BASE
			generalDao.delete(general);
			// delete general product images from FILE-SYSTEM
			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ general.getSeller().getSellerEmail().trim() + "\\" + "general" + "\\" + general.getProductId());
			try {
				System.out.println(imagePath);
				FileUtils.deleteDirectory(imagePath);
				return new Response<>(general, "Reject Success", 1061);
			} catch (IOException e) {
				e.printStackTrace();
				return new Response<>(general, "Reject Error", 1062);
			}
		} else {
			return new Response<>(general, "Reject Error", 1062);
		}
	}

	@Override
	public Response<General> stopGeneral(General general) {
		if (general != null) {
			general.setProductStatus(PRODUCT_STOPPED);
			generalDao.update(general);
			return new Response<>(general, "Stop Success", 1061);
		} else {
			return new Response<>(general, "Stop Error", 1062);
		}
	}

	@Override
	public List<General> getApprovedAndStoppedGeneral() {
		List<General> generalList = generalDao.findByProperty("product_status", PRODUCT_APPROVED);
		List<General> stoppedGeneralList = generalDao.findByProperty("product_status", PRODUCT_STOPPED);
		generalList.addAll(stoppedGeneralList);
		return generalList;
	}
	
	@Override
	public List<General> getApprovedGeneral() {
		List<General> generalList = generalDao.findByProperty("product_status", PRODUCT_APPROVED);
		return generalList;
	}
	
	@Override
	public List<General> getStoppedGeneral() {
		List<General> stoppedGeneralList = generalDao.findByProperty("product_status", PRODUCT_STOPPED);
		return stoppedGeneralList;
	}

	@Override
	public List<General> getApprovedGeneral(Integer sellerId) {
		String query = "SELECT * FROM general WHERE product_status = ? and seller_id = ?";
		Object[] parameters = new Object[] {"APPROVED",sellerId};
		List<General> approvedGeneralListForSeller = jdbcTemplate.query(query,parameters,generalRowMapper);
		return approvedGeneralListForSeller;
	}

	@Override
	public List<General> getStoppedGeneral(Integer sellerId) {
		String query = "SELECT * FROM general WHERE product_status = ? and seller_id = ?";
		Object[] parameters = new Object[] {"STOPPED",sellerId};
		List<General> stoppedGeneralListForSeller = jdbcTemplate.query(query,parameters,generalRowMapper);
		return stoppedGeneralListForSeller;
	}
	
	@Override
	public List<General> getRequestedGeneral(Integer sellerId) {
		String query = "SELECT * FROM general WHERE seller_id = ? and product_status = ?";
		Object[] parameters = new Object[] {sellerId,PRODUCT_REQUESTED};
		List<General> requestedGeneralListForSeller = jdbcTemplate.query(query,parameters,generalRowMapper);
		return requestedGeneralListForSeller;
	}
	
	@Override
	public List<General> getApprovedAndStoppedGeneralForSeller(Integer sellerId) {
		String query = "SELECT * FROM general WHERE seller_id = ? and ( product_status = ? or product_status = ? )";
		Object[] parameters = new Object[] {sellerId,"APPROVED","STOPPED"};
		List<General> generalListForSeller = jdbcTemplate.query(query,parameters,generalRowMapper);
		return generalListForSeller;
	}
	
}
