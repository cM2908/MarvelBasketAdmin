package com.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class FileEncodeCheck {
	
	public static void main(String[] args) {
		
		File f =  new File("C:\\Users\\CHINTAN\\Dropbox\\project_mb\\Images\\general5.jpg");
        String encodstring = encodeFileToBase64Binary(f);
        System.out.println(encodstring);
        
		/*
		 * Scanner sc = new Scanner(System.in); String image = sc.nextLine(); String
		 * sellerEmail = sc.nextLine(); String productType = sc.nextLine(); Integer
		 * productId = sc.nextInt(); Integer count = 1;
		 */
		/*
		 * File f = new
		 * File("D:\\CHINTAN\\JAVA\\Core_Java_Eclipse\\Codevita\\src\\main\\res\\drawable\\" + email + "
		 * \\" + pt + "\\" + id); f.mkdirs();
		 * 
		 * for (int i = 1; i <= 5; i++) { File f2 = new File(f, "product" + i + ".txt");
		 * f2.createNewFile(); }
		 * 
		 * File f3 = new
		 * File("D:\\CHINTAN\\JAVA\\Core_Java_Eclipse\\Codevita\\src\\main\\res\\drawable\\" + email + "
		 * \\" + pt + "\\" + id);
		 */

		/*
		 * File imagePath = new File(
		 * "C:\\Users\\CHINTAN\\Dropbox\\project_mb\\MarvelBasket\\src\\main\\resources\\static\\productImages\\"
		 * + sellerEmail + "\\" + productType + "\\" + productId); imagePath.mkdirs();
		 * 
		 * byte[] bytes = Base64.decodeBase64(image); try { FileOutputStream fos = new
		 * FileOutputStream(imagePath + "\\prod" + count + ".jpg"); fos.write(bytes);
		 * fos.close(); } catch (FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		
		/*
		 * File imagePath = new File(
		 * "C:\\Users\\CHINTAN\\Dropbox\\project_mb\\MarvelBasket\\src\\main\\resources\\static\\productImages\\"
		 * + "dp02@gmail.com" + "\\" + "electronic" + "\\" + "10"); try {
		 * System.out.println(imagePath); FileUtils.deleteDirectory(imagePath); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		
		
	}
	
	private static String encodeFileToBase64Binary(File file){
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
			fileInputStreamReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encodedfile;
	}
}
