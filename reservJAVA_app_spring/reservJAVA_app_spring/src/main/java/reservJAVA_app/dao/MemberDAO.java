package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import reservJAVA_app.dto.MemberDTO;

public class MemberDAO {

	DataSource dataSource;
	
	public MemberDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}

	//로그인
	public MemberDTO anMemberLogin(String member_id_in, String member_pw_in) {
		MemberDTO adto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select b.* ,  to_char(b.member_date , 'yyyy-MM-dd ')  "					
							+ " from tbl_member b" 
							+ " where b.member_id = '" + member_id_in + "' and b.member_pw = '" + member_pw_in + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			//System.out.println(member_id_in +", "+ member_pw_in);
			while (resultSet.next()) {
				int member_code = resultSet.getInt("member_code");
				String member_id = resultSet.getString("member_id");
				int member_kind = resultSet.getInt("member_kind");
				String member_name = resultSet.getString("member_name");
				String member_nick = resultSet.getString("member_nick");
				String member_tel = resultSet.getString("member_tel"); 
				String member_email = resultSet.getString("member_email"); 
				String member_addr = resultSet.getString("member_addr"); 
				String member_image = resultSet.getString("member_image"); 
				String member_date = resultSet.getString("member_date"); 
				System.out.println(member_date);
				adto = new MemberDTO(member_code, member_id, member_kind, member_name, member_nick, member_tel, member_email, member_addr, member_image, member_date);							
			}	
			//System.out.println("MemberDTO id : " + adto.getMember_id());
			//System.out.println("MemberDTO name : " + adto.getMember_name());
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
		return adto;
	}
	
	//회원 가입
	public int memberJoin(String member_id, String member_pw, String member_name, String member_nick,
			String member_tel, String member_email, String member_image, String member_date) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		//가입일자가 자동으로 입력되게 하면 좋겠지만,,, 힘들 수 있으니 
		//가입일자 입력은 나중에 작업합시당~~
		//Date 타입으로 하면 스프링에서 안드로이드로 가면서 문제가 생기는데 반대는 어떤지 모름
		try {
			connection = dataSource.getConnection();
			String query = "insert into tbl_member(member_id, member_pw, member_name, member_nick, member_tel, member_email, member_image) " + 
			               "values('" + member_id + "', '" + member_pw + "', '" + member_name + "', '" + member_nick + "', '" + member_tel + "', '" + 
			               member_email + "', '" + member_image + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			try {				
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

		return state;
	}
	
	//멤버 인서트(그냥 회원가입이면 되는거 아닌지)
	public int memberInsert(String member_id, String member_pw, String member_name, String member_nick,
			String member_tel, String member_email, String member_image, String member_date) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
				
		int state = -1;
		
		try {			
			connection = dataSource.getConnection();
			String query = "insert into tbl_member(member_id, member_pw, member_name, member_nick, member_tel, member_email, member_image) " + 
		               "values('" + member_id + "', '" + member_pw + "', '" + member_name + "', '" + member_nick + "', '" + member_tel + "', '" + 
		               member_email + "', '" + member_image + "' )";

			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}

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
			} 

		}
		return state;
	}
	
	
	//내 정보 수정(이미지 변경)
	public int memberUpdate(String member_id, String member_pw, String member_nick, String member_tel, String member_email, String member_image) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
		
		try {
			//아이디는 수정 하면 안됨
			connection = dataSource.getConnection();
			String query = "";
			
			//비번 변경 여부
			if(member_pw.equals("")) {
				query = "update tbl_member set "
					+ " member_nick= '" + member_nick + "'"
					+ ", member_tel= '" + member_tel + "'"
					+ ", member_email= '" + member_email + "'"
					+ ", member_image= '" + member_image + "'"
					+ " where member_id ='" + member_id + "'";
			} else {
				query = "update tbl_member set "
					+ " member_pw= '" + member_pw + "'"
					+ ", member_nick= '" + member_nick + "'"
					+ ", member_tel= '" + member_tel + "'"
					+ ", member_email= '" + member_email + "'"
					+ ", member_image= '" + member_image + "'"
					+ " where member_id ='" + member_id + "'";
			}
			
			preparedStatement = connection.prepareStatement(query);
			state = preparedStatement.executeUpdate();
			
			if(state > 0) {
				System.out.println("수정1 성공");
			} else {
				System.out.println("수정1 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return state;
	}

	//내 정보 수정(이미지 변경하지 않음)
	public int memberUpdateNoimg(String member_id, String member_pw, String member_nick, String member_tel,
			String member_email) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
		
		try {
			//아이디는 수정 하면 안됨
			connection = dataSource.getConnection();
			String query = "";
			
			//비번 변경 여부
			if(member_pw.equals("")) {
				query = "update tbl_member set "
					+ " member_nick= '" + member_nick + "'"
					+ ", member_tel= '" + member_tel + "'"
					+ ", member_email= '" + member_email + "'"
					+ " where member_id ='" + member_id + "'";
			} else {
				query = "update tbl_member set "
					+ " member_pw= '" + member_pw + "'"
					+ ", member_nick= '" + member_nick + "'"
					+ ", member_tel= '" + member_tel + "'"
					+ ", member_email= '" + member_email + "'"
					+ " where member_id ='" + member_id + "'";
			}
			
			preparedStatement = connection.prepareStatement(query);
			state = preparedStatement.executeUpdate();
			
			if(state > 0) {
				System.out.println("수정1 성공");
			} else {
				System.out.println("수정1 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return state;
	}

	//회원 탈퇴
	public int anMemberDelete(String member_id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;

		try {
			connection = dataSource.getConnection();
			String query = "delete from tbl_member where member_id=" + member_id;
			
			System.out.println(member_id);

			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();

			if (state > 0) {
				System.out.println("삭제성공");				
			} else {
				System.out.println("삭제실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			}
		}
		return state;
	}

	//리뷰 작성
	public int memberReview( int bOOKING_KIND, int bOOKING_MEMBER_CODE, int bOOKING_BUSINESS_CODE,
			int bOOKING_PRODUCT_CODE, int bOOKING_PRICE, int bOOKING_PRICE_DEPOSIT, int bOOKING_NUM,
			String bOOKING_DATE, String bOOKING_DATE_RESERVATION, String bOOKING_ETC, int bOOKING_APPRAISAL_STAR,
			String bOOKING_APPRAISAL) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;	//어째서 -100으로 하는지 물어볼것.
		
	
		try {
			connection = dataSource.getConnection();
			String query = "insert into TBL_ING_BOOKING(  BOOKING_KIND , BOOKING_MEMBER_CODE ,"
					+ "BOOKING_BUSINESS_CODE, BOOKING_PRODUCT_CODE ,"
					+ "BOOKING_PRICE , BOOKING_PRICE_DEPOSIT ,"
					+ "BOOKING_NUM,BOOKING_DATE,BOOKING_DATE_RESERVATION,"
					+ "BOOKING_ETC,BOOKING_APPRAISAL_STAR ,BOOKING_APPRAISAL) "
					
					+  "values('" + bOOKING_KIND + "', '" + bOOKING_MEMBER_CODE + "', '" + bOOKING_BUSINESS_CODE + 
			               "', '" + bOOKING_PRODUCT_CODE + "', '" + 
			               bOOKING_PRICE + "', '" + bOOKING_PRICE_DEPOSIT + "', '" + bOOKING_NUM + 
			               "', '" + bOOKING_DATE + "', '" + bOOKING_DATE_RESERVATION + "', '" + bOOKING_ETC + "', "
			               		+ "'" + bOOKING_APPRAISAL_STAR + "', '" + bOOKING_APPRAISAL + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			try {				
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

		return state;
	}
		
	}

		
	 
	

