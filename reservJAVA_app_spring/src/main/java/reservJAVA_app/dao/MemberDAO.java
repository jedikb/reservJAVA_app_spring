package reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.ui.Model;

import reservJAVA_app.dto.MemberDTO;
import reservJAVA_app.dto.ReviewDTO;

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

	//
	
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
	
	// 작성 리뷰 조회
	public ArrayList<ReviewDTO> anMyReview(Model model) {
		
		ArrayList<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		System.out.println(model.getAttribute("member_code"));
		String member_code = (String) model.getAttribute("member_code");
		System.out.println("member_code : " +member_code);
		try {
			connection = dataSource.getConnection();
			
			String query = "select b.*, bs.business_name, bs.business_category_code, bs.business_addr, to_char(b.booking_date_reservation, 'yyyy-MM-dd') "
					+ " from tbl_ing_booking b, tbl_business bs"
					+ " where b.booking_business_code = bs.business_code"
					+ " and b.booking_member_code = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, member_code);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int booking_code = resultSet.getInt("booking_code");
				int booking_kind = resultSet.getInt("booking_kind");
				int booking_member_code = resultSet.getInt("booking_member_code");
				int booking_business_code = resultSet.getInt("booking_business_code");
				int booking_product_code = resultSet.getInt("booking_product_code");
				String booking_date_reservation = resultSet.getString("booking_date_reservation");
				int booking_appraisal_star = resultSet.getInt("booking_appraisal_star");
				String booking_appraisal = resultSet.getString("booking_appraisal");
				String business_name = resultSet.getString("business_name");
				int business_category_code = resultSet.getInt("business_category_code");
				String business_addr = resultSet.getString("business_addr");
				
				ReviewDTO reviewDTO = new ReviewDTO(booking_code, booking_kind, booking_member_code, booking_business_code,
						booking_product_code, booking_date_reservation, booking_appraisal_star, booking_appraisal, business_name, business_category_code, business_addr);
				reviewDTOs.add(reviewDTO);			
			}
			System.out.println("리스트 수 : " + reviewDTOs.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		return reviewDTOs;
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


	 
	
}
