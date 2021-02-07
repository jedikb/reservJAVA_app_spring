package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.ui.Model;

import reservJAVA_app.dto.BusinessDTO;

public class BusinessDAO {

	DataSource dataSource;
	
	public BusinessDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}
	
	//비지니스 리스트 조회
	public ArrayList<BusinessDTO> businessSelect() {
		
		ArrayList<BusinessDTO> busiDTOs = new ArrayList<BusinessDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from tbl_business" 
							+ " order by business_code desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int business_code = resultSet.getInt("business_code");
				String business_name = resultSet.getString("business_name");
				int business_member_code = resultSet.getInt("business_member_code");
				int business_category_parent_code = resultSet.getInt("business_category_parent_code");
				int business_category_code = resultSet.getInt("business_category_code");
				String business_addr = resultSet.getString("business_addr");
				String business_tel = resultSet.getString("business_tel");
				String business_image = resultSet.getString("business_image");
				String business_info = resultSet.getString("business_info");
				int business_star_avg = resultSet.getInt("business_star_avg");
				String business_hashtag = resultSet.getString("business_hashtag");
				Double business_lat = resultSet.getDouble("business_lat");
				Double business_lng = resultSet.getDouble("business_lng");
				BusinessDTO busiDTO = new BusinessDTO(business_code, business_name, business_member_code, business_category_parent_code, business_category_code, business_addr, business_tel, business_image, business_info, business_star_avg, business_hashtag, business_lat, business_lng);
				busiDTOs.add(busiDTO);			
			}	
			
			System.out.println("adtos크기" + busiDTOs.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		return busiDTOs;
	}
	
	//매장 검색
	public ArrayList<BusinessDTO> searchBusiness(Model model) {
		
		ArrayList<BusinessDTO> busiDTOs = new ArrayList<BusinessDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		System.out.println("searchText" + model.getAttribute("searchText").toString());
		String searchData = "%"+model.getAttribute("searchText").toString()+"%";
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from tbl_business" 
							+ " where upper(business_name) like upper(?) or upper(business_hashtag) like upper(?)"
							+ " order by business_addr";
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, searchData);
			prepareStatement.setString(2, searchData);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int business_code = resultSet.getInt("business_code");
				String business_name = resultSet.getString("business_name");
				int business_member_code = resultSet.getInt("business_member_code");
				int business_category_parent_code = resultSet.getInt("business_category_parent_code");
				int business_category_code = resultSet.getInt("business_category_code");
				String business_addr = resultSet.getString("business_addr");
				String business_tel = resultSet.getString("business_tel");
				String business_image = resultSet.getString("business_image");
				String business_info = resultSet.getString("business_info");
				int business_star_avg = resultSet.getInt("business_star_avg");
				String business_hashtag = resultSet.getString("business_hashtag");
				Double business_lat = resultSet.getDouble("business_lat");
				Double business_lng = resultSet.getDouble("business_lng");
				//System.out.println("business_category_code1 : " + business_category_code1);
				BusinessDTO busiDTO = new BusinessDTO(business_code, business_name, business_member_code, business_category_parent_code, business_category_code, business_addr, business_tel, business_image, business_info, business_star_avg, business_hashtag, business_lat, business_lng);
				busiDTOs.add(busiDTO);			
			}	
			
			System.out.println("adtos크기" + busiDTOs.size());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		return busiDTOs;
	}

}
