package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Fashion;
import com.bean.FashionBean;
import com.bean.Response;
import com.bean.Seller;
import com.bean.SubFashion;
import com.dao.FashionDao;
import com.dao.SellerDao;
import com.dao.SubFashionDao;
import com.rowmapper.FashionRowMapper;

@Service
public class FashionServiceImpl implements FashionService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	FashionRowMapper fashionRowMapper;
	@Autowired
	FashionDao fashionDao;
	@Autowired
	SubFashionDao subFashionDao;
	@Autowired
	AdminNotificationService adminNotificationService;
	@Autowired
	SellerDao sellerDao;
	@Value("${project.path}")
	private String projectPath;
	@Value("${project.name}")
	private String projectName;

	/*------------------------------------------------------------------------------------------------------------------------------------------*/

	@Override
	public Response<FashionBean> addFashion(FashionBean fashionBean) {
		
		// Query to check duplication 
		String query = "SELECT * FROM fashion WHERE fashion_name = ? and fashion_buyertype = ? and fashion_brand = ? and fashion_color = ? and seller_id = ?";
		Object[] parameters = new Object[] { fashionBean.getFashion().getFashionName(),
				fashionBean.getFashion().getFashionBuyerType(), fashionBean.getFashion().getFashionBrand(),
				fashionBean.getFashion().getFashionColor(), fashionBean.getFashion().getSeller().getSellerId() };
		List<Fashion> fashionList = jdbcTemplate.query(query, parameters, fashionRowMapper);
		
		// Check for seller status
		Seller seller = sellerDao.findById(fashionBean.getFashion().getSeller().getSellerId());
		
		if (!seller.getSellerStatus().equals(SellerService.STATUS_APPROVED)) {
			return new Response<>(null, "Seller not approved", 1053);
		} else if (fashionList.size() > 0) {
			return new Response<>(fashionBean, "Fashion product already exits", 1052);
		} else {
			// Get Encoded Image String
			String encodedImages = fashionBean.getFashion().getFashionImages();

			// Change Status and Image Field
			fashionBean.getFashion().setFashionStatus(PRODUCT_REQUESTED);
			fashionBean.getFashion().setFashionImages("prod1;prod2;prod3;prod4;prod5");

			// Save Fashion data into DATA-BASE
			fashionDao.save(fashionBean.getFashion());
			
			// Retrieving Fashion Record from database for FashionId
			List<Fashion> fashionListTemp = jdbcTemplate.query(query, parameters, fashionRowMapper);
			
			// Save SubFashion data into DATA-BASE
			for(SubFashion subFashion : fashionBean.getSubFashion()) {
				subFashionDao.save(subFashion, fashionListTemp.get(0).getFashionId());
			}
			
			// Retrieving SubFashion Record from database for SubFashionId
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",fashionListTemp.get(0).getFashionId());
			
			// For returning fashionBean we have to update it with updated fashion which
			// have fashionId and fashionStatus
			fashionBean.setFashion(fashionListTemp.get(0));
			fashionBean.setSubFashion(subFashionList);
			
			// save Images into FILE-SYSTEM
			addProductImagesToFileSystem(encodedImages, seller.getSellerEmail(), "fashion",
					fashionListTemp.get(0).getFashionId());

			// Send notification to admin
			adminNotificationService.addNotificationForProductRequest(fashionListTemp.get(0));

			return new Response<>(fashionBean, "Fashion product added , wait for approval", 1051);
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
	public Response<FashionBean> approveFashion(FashionBean fashionBean) {
		if (fashionBean != null) {
			fashionBean.getFashion().setFashionStatus(PRODUCT_APPROVED);
			fashionDao.update(fashionBean.getFashion());
			return new Response<>(fashionBean, "Approve Success", 1061);
		} else {
			return new Response<>(fashionBean, "Approve Error", 1062);
		}
	}

	@Override
	public Response<FashionBean> rejectFashion(FashionBean fashionBean) {
		if (fashionBean != null) {
			// delete fashion & sub-fashion record from DATA-BASE
			fashionDao.delete(fashionBean.getFashion());
			for (SubFashion subFashion : fashionBean.getSubFashion()) {
				subFashionDao.delete(subFashion);
			}
			// delete fashion images from FILE-SYSTEM
			File imagePath = new File(
					projectPath+"\\"+projectName+"\\src\\main\\resources\\static\\productImages\\"
							+ fashionBean.getFashion().getSeller().getSellerEmail().trim() + "\\" + "fashion" + "\\"
							+ fashionBean.getFashion().getFashionId());
			try {
				System.out.println(imagePath);
				FileUtils.deleteDirectory(imagePath);
				return new Response<>(fashionBean, "Reject Success", 1061);
			} catch (IOException e) {
				e.printStackTrace();
				return new Response<>(fashionBean, "Reject Error", 1062);
			}
		} else {
			return new Response<>(fashionBean, "Reject Error", 1062);
		}
	}

	@Override
	public Response<FashionBean> stopFashion(FashionBean fashionBean) {
		if (fashionBean != null) {
			fashionBean.getFashion().setFashionStatus(PRODUCT_STOPPED);
			fashionDao.update(fashionBean.getFashion());
			return new Response<>(fashionBean, "Stop Success", 1061);
		} else {
			return new Response<>(fashionBean, "Stop Error", 1062);
		}
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------*/

	@Override
	public List<FashionBean> getApprovedFashion() {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();
		List<Fashion> approvedFashionList = fashionDao.findByProperty("fashion_status", PRODUCT_APPROVED);
		for (Fashion approvedFashion : approvedFashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",
					approvedFashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(approvedFashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getStoppedFashion() {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();
		List<Fashion> stoppedFashionList = fashionDao.findByProperty("fashion_status", PRODUCT_STOPPED);
		for (Fashion stoppedFashion : stoppedFashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id", stoppedFashion.getFashionId());

			FashionBean stoppedfashionBean = new FashionBean();
			stoppedfashionBean.setFashion(stoppedFashion);
			stoppedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(stoppedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getRequestedFashion() {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();
		List<Fashion> requestedFashionList = fashionDao.findByProperty("fashion_status", PRODUCT_REQUESTED);
		for (Fashion requestedFashion : requestedFashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",
					requestedFashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(requestedFashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getApprovedAndStoppedFashion() {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();

		List<Fashion> approvedFashionList = fashionDao.findByProperty("fashion_status", PRODUCT_APPROVED);
		for (Fashion approvedFashion : approvedFashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",
					approvedFashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(approvedFashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}

		List<Fashion> stoppedFashionList = fashionDao.findByProperty("fashion_status", PRODUCT_STOPPED);
		for (Fashion stoppedFashion : stoppedFashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id", stoppedFashion.getFashionId());

			FashionBean stoppedfashionBean = new FashionBean();
			stoppedfashionBean.setFashion(stoppedFashion);
			stoppedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(stoppedfashionBean);
		}

		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getAllFashion() {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();
		
		List<Fashion> fashionList = fashionDao.findAll();
		for (Fashion fashion : fashionList) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",fashion.getFashionId());
			
			FashionBean fashionBean = new FashionBean();
			fashionBean.setFashion(fashion);
			fashionBean.setSubFashion(subFashionList);
			
			fashionBeanList.add(fashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public FashionBean getFashionById(Integer fashionId) {
		Fashion fashion = fashionDao.findById(fashionId);
		List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id", fashionId);
		
		FashionBean fashionBean = new FashionBean();
		fashionBean.setFashion(fashion);
		fashionBean.setSubFashion(subFashionList);
		
		return fashionBean;
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------------------*/

	@Override
	public List<FashionBean> getApprovedFashion(Integer sellerId) {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();

		String query = "SELECT * FROM fashion WHERE fashion_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_APPROVED, sellerId };
		List<Fashion> approvedFashionListForSeller = jdbcTemplate.query(query, parameters, fashionRowMapper);

		for (Fashion approvedFashion : approvedFashionListForSeller) {

			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",
					approvedFashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(approvedFashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getStoppedFashion(Integer sellerId) {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();

		String query = "SELECT * FROM fashion WHERE fashion_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_STOPPED, sellerId };
		List<Fashion> stoppedFashionListForSeller = jdbcTemplate.query(query, parameters, fashionRowMapper);

		for (Fashion stoppedFashion : stoppedFashionListForSeller) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id", stoppedFashion.getFashionId());

			FashionBean stoppedfashionBean = new FashionBean();
			stoppedfashionBean.setFashion(stoppedFashion);
			stoppedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(stoppedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getRequestedFashion(Integer sellerId) {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();

		String query = "SELECT * FROM fashion WHERE fashion_status = ? and seller_id = ?";
		Object[] parameters = new Object[] { PRODUCT_REQUESTED, sellerId };
		List<Fashion> requestedFashionListForSeller = jdbcTemplate.query(query, parameters, fashionRowMapper);

		for (Fashion requestedFashion : requestedFashionListForSeller) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",
					requestedFashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(requestedFashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}
		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getApprovedAndStoppedFashion(Integer sellerId) {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();

		String query = "SELECT * FROM fashion WHERE seller_id = ? and ( fashion_status = ? or fashion_status = ? )";
		Object[] parameters = new Object[] { sellerId, PRODUCT_APPROVED, PRODUCT_REQUESTED };
		List<Fashion> fashionListForSeller = jdbcTemplate.query(query, parameters, fashionRowMapper);

		for (Fashion fashion : fashionListForSeller) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id", fashion.getFashionId());

			FashionBean approvedfashionBean = new FashionBean();
			approvedfashionBean.setFashion(fashion);
			approvedfashionBean.setSubFashion(subFashionList);

			fashionBeanList.add(approvedfashionBean);
		}

		return fashionBeanList;
	}

	@Override
	public List<FashionBean> getAllFashion(Integer sellerId) {
		List<FashionBean> fashionBeanList = new ArrayList<FashionBean>();
		
		List<Fashion> fashionListForSeller = fashionDao.findByProperty("seller_id", sellerId);
		for (Fashion fashion : fashionListForSeller) {
			List<SubFashion> subFashionList = subFashionDao.findByProperty("fashion_id",fashion.getFashionId());
			
			FashionBean fashionBean = new FashionBean();
			fashionBean.setFashion(fashion);
			fashionBean.setSubFashion(subFashionList);
			
			fashionBeanList.add(fashionBean);
		}
		return fashionBeanList;
	}

	/*------------------------------------------------------------------------------------------------------------------------------------------*/
}
