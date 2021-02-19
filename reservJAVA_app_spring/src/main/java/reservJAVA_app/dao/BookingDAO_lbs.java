package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import reservJAVA_app.dto.BookingDTO;

public class BookingDAO_lbs {
    private static final String TAG = "BookingDAOlbs.";

    private Connection connection = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet resultSet = null;
	private DataSource dataSource;

	//생성자 메소드()
	public BookingDAO_lbs() { 
	    String TAG2 = TAG + "BookingDAOlbs() 생성자 메소드: ";
	    try {	Context context = new InitialContext();
				//DB접속정보(dataSource)를 가져온다(위치:tomcat/context.xml->name="bteam")
				dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (Exception e) { e.printStackTrace(); System.out.println(TAG2 + "Exception!!!"); }
	}//BookingDAOlbs() DB접속정보 가져옴.

	//DB접속(dataSource로 DB connection을 생성한다)
	public Connection getConn() {
	    String TAG2 = TAG + "getConn(): ";
	    try {	connection = dataSource.getConnection();
		} catch (Exception e) {	e.printStackTrace(); System.out.println(TAG2 + "Exception!!!"); }
		return connection;
	}//getConn() DB접속.
	
	//DB접속해제
	public void dbClose() {
	    String TAG2 = TAG + "dbClose(): ";
		try {	if(resultSet != null)			resultSet.close();
				if(prepareStatement != null)	prepareStatement.close();
				if(connection != null)			connection.close();
		} catch (Exception e) { e.printStackTrace(); System.out.println(TAG2 + "Exception!!!"); }
	}//dbClose() DB접속해제.
	
	//예약확인(상세보기) 처리
	//1. tbl_ing_booking.예약 1건의 정보를 가져온다.
	public BookingDTO anBookingView( int booking_code ) {
	    String TAG2 = TAG + "anBookingView(): ";
		String query = "";
		BookingDTO adto = null;

		try {
			connection = getConn();
			System.out.println(TAG2 + "booking_code= " + booking_code );
			
			query = "select b.* "
					+ ",(select member_name from tbl_member m where m.member_code = b.booking_member_code) booking_member_name "
					+ ",(select business_name from tbl_business m where m.business_code = b.booking_business_code) booking_business_name "
					+ ",(select product_name from tbl_product m where m.product_code = b.booking_product_code) booking_product_name "
					+ " from tbl_ing_booking b "	//조회: 예약테이블.
					+ " where booking_code	= ? ";		//검색조건: 예약코드.
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, booking_code);

			resultSet = prepareStatement.executeQuery();	//DB조회 실행.
			
			while (resultSet.next()) {
				adto = new BookingDTO();
				adto.setBooking_code				(resultSet.getInt	("booking_code"				));
				adto.setBooking_kind				(resultSet.getInt	("booking_kind"				));
				adto.setBooking_member_code			(resultSet.getInt	("booking_member_code"		));
				adto.setBooking_member_name			(resultSet.getString("booking_member_name"		));
				adto.setBooking_business_code		(resultSet.getInt	("booking_business_code"	));
				adto.setBooking_business_name		(resultSet.getString("booking_business_name"	));
				adto.setBooking_product_code		(resultSet.getInt	("booking_product_code"		));
				adto.setBooking_product_name		(resultSet.getString("booking_product_name"		));
				adto.setBooking_price				(resultSet.getInt	("booking_price"			));
				adto.setBooking_price_deposit		(resultSet.getInt	("booking_price_deposit"	));
				adto.setBooking_num					(resultSet.getInt	("booking_num"				));
				adto.setBooking_date				(resultSet.getString("booking_date"				));
				adto.setBooking_date_reservation	(resultSet.getString("booking_date_reservation"	));
				adto.setBooking_etc					(resultSet.getString("booking_etc"				));
				adto.setBooking_appraisal_star		(resultSet.getInt	("booking_appraisal_star"	));
				adto.setBooking_appraisal			(resultSet.getString("booking_appraisal"		));
			}	
		} catch(Exception e){ e.printStackTrace(); System.out.println(TAG2 + "Exception!!!");
		} finally{ dbClose(); }

		return adto;	//예약 1건 정보 반환.
	}//anBookingView() 예약확인(상세보기)

	//예약취소 처리
	//1. tbl_ing_booking.예약 1건을 삭제한다.
	public int anBookingCancel( int booking_code ) {
		String TAG2 = TAG + "anBookingCancel(): ";
		String query = "";
		int state = -1;
		
		try {
			connection = getConn();
			System.out.println(TAG2 + "booking_code= " + booking_code );
			
			query = "delete from tbl_ing_booking "		//삭제: 예약테이블.
					+ " where booking_code	= ? ";		//검색조건: 예약코드.
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, booking_code);
			
			state = prepareStatement.executeUpdate();	//DB업데이트 실행.
			
			if (state > 0)	System.out.println(TAG2 + "예약정보 삭제 성공!");				
			else			System.out.println(TAG2 + "예약정보 삭제 실패~");
			
		} catch(Exception e){ e.printStackTrace(); System.out.println(TAG2 + "Exception!!!");
		} finally{ dbClose(); }
		
		return state;	//DB처리 성공유무 반환.
	}//anBookingCancel() 예약취소 처리

	//예약완료(결재) 처리
	//1. tbl_ing_booking.결재요청 예약의 booking_kind(예약 구분코드)를 { 3 }로 변경한다
	public int anPayment( int booking_code ) {
		String TAG2 = TAG + "anPayment(): ";
		String query = "";
		int state = -1;
		
		try {
			connection = getConn();
			System.out.println(TAG2 + "booking_code= " + booking_code );
			
			query = "update tbl_ing_booking set "		//업데이트: 예약테이블.
					+ " booking_kind		= 3 "		//예약 구분(1.찜, 2.진행중, 3.완료)
					+ " where booking_code	= ? ";		//검색조건: 예약코드.
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, booking_code);
			
			state = prepareStatement.executeUpdate();	//DB업데이트 실행.
			
			if (state > 0)	System.out.println(TAG2 + "결재(예약완료:3)으로 변경 성공!");				
			else			System.out.println(TAG2 + "결재(예약완료:3)으로 변경 실패~");
			
		} catch(Exception e){ e.printStackTrace(); System.out.println(TAG2 + "Exception!!!");
		} finally{ dbClose(); }
		
		return state;	//DB처리 성공유무 반환.
	}//anPayment() 예약완료(결재)
	
}
