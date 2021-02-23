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

import reservJAVA_app.dto.BookingDTO;

public class BookingDAO {

	//Data 議고쉶 �삤瑜섎뒗 memberDAO 李몄“
	DataSource dataSource;
	
	public BookingDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}
	
	//�삁�빟 由ъ뒪�듃 議고쉶
	public ArrayList<BookingDTO> bookingSelect() {
		
		ArrayList<BookingDTO> bDTOs = new ArrayList<BookingDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from tbl_ing_booking" 
							+ " order by booking_code desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int booking_code = resultSet.getInt("booking_code");
				int booking_kind = resultSet.getInt("booking_kind");
				int booking_member_code = resultSet.getInt("booking_member_code");
				int booking_business_code = resultSet.getInt("booking_business_code");
				int booking_product_code = resultSet.getInt("booking_product_code");
				int booking_price = resultSet.getInt("booking_price");
				int booking_price_deposit = resultSet.getInt("booking_price_deposit");
				int booking_num = resultSet.getInt("booking_num");
				Date booking_date = resultSet.getDate("booking_date");
				String booking_date_reservation = resultSet.getString("booking_date_reservation");
				String booking_etc = resultSet.getString("booking_etc");
				int booking_appraisal_star = resultSet.getInt("booking_appraisal_star");
				String booking_appraisal = resultSet.getString("booking_appraisal");
				
				
				BookingDTO bDTO = new BookingDTO(booking_code, booking_kind, booking_member_code, booking_business_code,
						booking_product_code, booking_price, booking_price_deposit, booking_num, booking_date,
						booking_date_reservation, booking_etc, booking_appraisal_star, booking_appraisal);
				bDTOs.add(bDTO);			
			}	
			
			System.out.println("bDTOs�겕湲�" + bDTOs.size());
			
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
		return bDTOs;
	}

	public void bookingInsert(BookingDTO dto) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		int succ = 0;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into tbl_ing_booking(booking_member_code,"
					+ " booking_business_code, booking_product_code, booking_price,"
					+ " booking_price_deposit, booking_num, booking_date, booking_date_reservation)"
					+ " values(?, ?, ?, ?, ?, ?, sysdate, ?)";
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, dto.getBooking_member_code());
			prepareStatement.setInt(2, dto.getBooking_business_code());
			prepareStatement.setInt(3, dto.getBooking_product_code());
			prepareStatement.setInt(4, dto.getBooking_price());
			prepareStatement.setInt(5, dto.getBooking_price_deposit());
			prepareStatement.setInt(6, dto.getBooking_num());
			prepareStatement.setString(7, dto.getBooking_date_reservation());
			succ = prepareStatement.executeUpdate();
				
			
			System.out.println("insert success? : " + succ);
			
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
	}

	
}
