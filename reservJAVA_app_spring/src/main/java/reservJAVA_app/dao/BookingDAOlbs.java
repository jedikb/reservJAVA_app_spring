package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookingDAOlbs {
    private static final String TAG = "BookingDAOlbs.";

    private Connection connection = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet resultSet = null;
	private DataSource dataSource;

	//생성자 메소드()
	public BookingDAOlbs() { 
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
	
	//예약완료(결재) 처리
	//1. tbl_ing_booking.결재요청 예약의 booking_kind(예약 구분코드)를 { 3 }로 변경한다
	public int anPayment( int booking_code ) {
	    String TAG2 = TAG + "anPayment(): ";
		String query = "";
		int state = -1;

		try {
			connection = getConn();
			System.out.println(TAG2 + "booking_code= " + booking_code );
			
			query = "update tbl_ing_booking set "		//업데이트 예약테이블.
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
