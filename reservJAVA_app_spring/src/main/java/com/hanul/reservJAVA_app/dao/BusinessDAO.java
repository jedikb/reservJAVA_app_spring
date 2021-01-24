package com.hanul.reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.reservJAVA_app.dto.BusinessDTO;

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
				int business_category_code1 = resultSet.getInt("business_category_code1");
				int business_category_code2 = resultSet.getInt("business_category_code2");
				String business_addr = resultSet.getString("business_addr");
				String business_tel = resultSet.getString("business_tel");
				String business_image = resultSet.getString("business_image");
				String business_info = resultSet.getString("business_info");
				int business_star_avg = resultSet.getInt("business_star_avg");
				
				BusinessDTO busiDTO = new BusinessDTO(business_code, business_name, business_member_code, business_category_code1, business_category_code2, business_addr, business_tel, business_image, business_info, business_star_avg);
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
