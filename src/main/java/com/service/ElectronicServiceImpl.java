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

import com.bean.Electronic;
import com.bean.Response;
import com.bean.Seller;
import com.dao.ElectronicDao;
import com.dao.SellerDao;
import com.rowmapper.ElectronicRowMapper;

@Service
public class ElectronicServiceImpl implements ElectronicService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ElectronicRowMapper electronicRowMapper;
	@Autowired
	private ElectronicDao electronicDao;
	@Autowired
	private AdminNotificationService adminNotificationService;
	@Autowired
	private SellerDao sellerDao;
	@Value("${project.path}")
	private String projectPath;
	@Value("${project.name}")
	private String projectName;
	
	/*------------------------------------------------------------------------------------------------------------------------------------------*/

	@Override
	public Response<Electronic> addElectronic(Electronic electronic) {
		String query = "SELECT * FROM electronic WHERE electronic_name = ? and electronic_brand = ? and electronic_size = ? and electronic_color = ? and electronic_price = ? and seller_id = ?";
		Object[] parameters = new Object[] { electronic.getElectronicName(), electronic.getElectronicBrand(),
				electronic.getElectronicSize(), electronic.getElectronicColor(), electronic.getElectronicPrice(),
				electronic.getSeller().getSellerId() };
		List<Electronic> electronicList = jdbcTemplate.query(query, parameters, electronicRowMapper);
		Seller seller = sellerDao.findById(electronic.getSeller().getSellerId());
		if (!seller.getSellerStatus().equals(SellerService.STATUS_APPROVED)) {
			System.out.println("if");
			return new Response<>(null, "Seller not approved", 1053);
		} else if (electronicList.size() > 0) {
			System.out.println("else if");
			return new Response<>(electronic, "Electronic product already exits", 1052);
		} else {
			System.out.println("else");
			// Get Encoded Image String
			String encodedImages = electronic.getElectronicImages();

			// Change Status and Image Field
			electronic.setElectronicStatus(PRODUCT_REQUESTED);
			electronic.setElectronicImages("prod1;prod2;prod3;prod4;prod5");

			// Save Electronic data into DATA-BASE
			electronicDao.save(electronic);

			// Retrieving Electronic Record from database for ElectronicId
			List<Electronic> electronicListTemp = jdbcTemplate.query(query, parameters, electronicRowMapper);

			// save Images into FILE-SYSTEM
			addProductImagesToFileSystem(encodedImages, seller.getSellerEmail(), "electronic",
					electronicListTemp.get(0).getElectronicId());

			// Send notification to admin
			adminNotificationService.addNotificationForProductRequest(electronicListTemp.get(0));

			return new Response<>(electronicListTemp.get(0), "Electronic product added , wait for approval", 1051);
		}
	}

	private void addProductImagesToFileSystem(String encodedString, String sellerEmail, String productType,
			Integer productId) {
		String[] imageArr = encodedString.split(";");
		int count = 1;
		for (String image : imageArr) {

			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ sellerEmail.trim() + "\\" + productType.trim() + "\\" + productId);
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

	/*------------------------------------------------------------------------------------------------------------------------------------------*/
	
	@Override
	public Response<Electronic> approveElectronic(Electronic electronic) {
		if (electronic != null) {
			electronic.setElectronicStatus(PRODUCT_APPROVED);
			electronicDao.update(electronic);
			return new Response<>(electronic, "Approve Success", 1061);
		} else {
			return new Response<>(electronic, "Approve Error", 1062);
		}
	}

	@Override
	public Response<Electronic> rejectElectronic(Electronic electronic) {
		if (electronic != null) {
			// delete electronic record from DATA-BASE
			electronicDao.delete(electronic);
			// delete electronic images from FILE-SYSTEM
			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ electronic.getSeller().getSellerEmail().trim() + "\\" + "electronic" + "\\"
							+ electronic.getElectronicId());
			try {
				System.out.println(imagePath);
				FileUtils.deleteDirectory(imagePath);
				return new Response<>(electronic, "Reject Success", 1061);
			} catch (IOException e) {
				e.printStackTrace();
				return new Response<>(electronic, "Reject Error", 1062);
			}
		} else {
			return new Response<>(electronic, "Reject Error", 1062);
		}
	}

	@Override
	public Response<Electronic> stopElectronic(Electronic electronic) {
		if (electronic != null) {
			electronic.setElectronicStatus(PRODUCT_STOPPED);
			electronicDao.update(electronic);
			return new Response<>(electronic, "Stop Success", 1061);
		} else {
			return new Response<>(electronic, "Stop Error", 1062);
		}
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Electronic> getApprovedElectronics() {
		List<Electronic> electronicList = electronicDao.findByProperty("electronic_status", PRODUCT_APPROVED);
		return electronicList;
	}

	@Override
	public List<Electronic> getStoppedElectronics() {
		List<Electronic> stoppedElectronicList = electronicDao.findByProperty("electronic_status", PRODUCT_STOPPED);
		return stoppedElectronicList;
	}

	@Override
	public List<Electronic> getRequestedElectronic() {
		List<Electronic> electronicList = electronicDao.findByProperty("electronic_status",
				ElectronicService.PRODUCT_REQUESTED);
		return electronicList;
	}
	
	@Override
	public List<Electronic> getApprovedAndStoppedElectronics() {
		List<Electronic> electronicList = electronicDao.findByProperty("electronic_status", PRODUCT_APPROVED);
		List<Electronic> stoppedElectronicList = electronicDao.findByProperty("electronic_status", PRODUCT_STOPPED);
		electronicList.addAll(stoppedElectronicList);
		return electronicList;
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Electronic> getApprovedElectronics(Integer sellerId) {
		String query = "SELECT * FROM electronic WHERE electronic_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_APPROVED, sellerId };
		List<Electronic> approvedElectronicListForSeller = jdbcTemplate.query(query, parameters, electronicRowMapper);
		return approvedElectronicListForSeller;
	}

	@Override
	public List<Electronic> getStoppedElectronics(Integer sellerId) {
		String query = "SELECT * FROM electronic WHERE electronic_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_STOPPED, sellerId };
		List<Electronic> stoppedElectronicListForSeller = jdbcTemplate.query(query, parameters, electronicRowMapper);
		return stoppedElectronicListForSeller;
	}

	@Override
	public List<Electronic> getRequestedElectronics(Integer sellerId) {
		String query = "SELECT * FROM electronic WHERE electronic_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_REQUESTED, sellerId };
		List<Electronic> requestedElectronicListForSeller = jdbcTemplate.query(query, parameters, electronicRowMapper);
		return requestedElectronicListForSeller;
	}

	@Override
	public List<Electronic> getApprovedAndStoppedElectronicsForSeller(Integer sellerId) {
		String query = "SELECT * FROM electronic WHERE seller_id = ? and ( electronic_status = ? or electronic_status = ? )";
		Object[] parameters = new Object[] { sellerId, "APPROVED", "STOPPED" };
		List<Electronic> electronicListForSeller = jdbcTemplate.query(query, parameters, electronicRowMapper);
		return electronicListForSeller;
	}
}