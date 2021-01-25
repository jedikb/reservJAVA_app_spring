package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import reservJAVA_app.dto.ProductDTO;

public class ProductDAO {
	DataSource dataSource;
	
	public ProductDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}
	
	//상품 리스트 조회
	public ArrayList<ProductDTO> businessSelect() {
		
		ArrayList<ProductDTO> proDTOs = new ArrayList<ProductDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from tbl_product" 
							+ " order by product_code desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int product_code = resultSet.getInt("product_code");
				int product_business_code = resultSet.getInt("product_business_code");
				String product_name = resultSet.getString("product_name");
				int product_price = resultSet.getInt("product_price");
				int product_price_deposit = resultSet.getInt("product_price_deposit");
				int product_stock = resultSet.getInt("product_stock");
				String product_image = resultSet.getString("product_image");
				String product_info = resultSet.getString("product_info");

				
				ProductDTO proDTO = new ProductDTO(product_code, product_business_code, product_name, product_price, product_price_deposit, product_stock, product_image, product_info);
				proDTOs.add(proDTO);			
			}	
			
			System.out.println("adtos크기" + proDTOs.size());
			
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
		return proDTOs;
	}
	
	
}
