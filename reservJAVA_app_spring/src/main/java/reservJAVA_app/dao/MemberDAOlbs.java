package reservJAVA_app.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAOlbs {
    private static final String TAG = "MemberDAOlbs.";

    private Connection connection = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet resultSet = null;
	private DataSource dataSource;

	//생성자 메소드()
	public MemberDAOlbs() { 
	    String TAG2 = TAG + "MemberDAOlbs() 생성자 메소드: ";
	    try {	Context context = new InitialContext();
				//DB접속정보(dataSource)를 가져온다(위치:tomcat/context.xml->name="bteam")
				dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (Exception e) { e.printStackTrace(); System.out.println(TAG2 + "Exception!!!"); }
	}//MemberDAOlbs() DB접속정보 가져옴.

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
	
	//회원-탈퇴 처리
	//1. tbl_member.탈퇴요청 회원의 member_kind(구분코드)를 { 9 }로 변경하고,
	//2. member_date_delete(개인정보 삭제 예정일)를 생성,저장한다.
	public int anMemberCancel( int member_code ) {
	    String TAG2 = TAG + "anMemberCancel(): ";
		String query = "";
		int state = -1;

		try {
			connection = getConn();
			System.out.println(TAG2 + "member_code= " + member_code );
			
			query = "update tbl_member set "			//업데이트 회원테이블.
					+ "  member_kind		= 9 "		//회원 구분(1.관리자, 2.일반회원,3.사업자회원,9.회원탈퇴).
					+ ", member_date_delete	= sysdate "	//개인정보 삭제 예정일(코드수정필요).
					+ " where member_code	= ? ";		//검색조건: 회원코드.
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, member_code);

			state = prepareStatement.executeUpdate();	//DB업데이트 실행.

			if (state > 0)	System.out.println(TAG2 + "탈퇴회원(9)으로 변경 성공!");				
			else			System.out.println(TAG2 + "탈퇴회원(9)으로 변경 실패~");

		} catch(Exception e){ e.printStackTrace(); System.out.println(TAG2 + "Exception!!!");
		} finally{ dbClose(); }

		return state;	//DB처리 성공유무 반환.
	}//anMemberCancel() 회원탈퇴

	//회원-개인정보삭제 처리
	//1. 서버에 있는 member_image 파일 삭제. 
	//2. tbl_member.member_date_delete(개인정보 삭제 예정일)가 경과한 탈퇴회원들의 개인정보를 삭제한다.
	public int anMemberDeleteInfo() {
	    String TAG2 = TAG + "anMemberDeleteInfo(): ";
		
		String query = "";
		int state = -1;
		String member_image;
		
		//1. 서버에 있는 member_image 파일 삭제 처리(예정)
		//ArrayList<String> list = new ArrayList<>();		
		try {
			connection = getConn();
			System.out.println(TAG2 + "member_date_delete(개인정보 삭제 예정일)이 경과한 회원님들의 사진파일을 삭제합니다.");

			query = "select "
					+ " member_image "
					+ " from tbl_member "
					+ " where member_kind = 9 "				//검색조건1: 탈퇴회원 이고,
					+ " and member_date_delete > sysdate ";	//검색조건2: 개인정보 삭제 예정일이 지난 경우.
			prepareStatement = connection.prepareStatement(query);

			resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {
				member_image = "/resources/images/member/" + resultSet.getString("member_image");
				//list.add(member_image);
				System.out.println(TAG2 + "1. " + member_image);
				
				// 이미지 파일지우기
				File delfile = new File(member_image);
				System.out.println(TAG2 + "2. " + delfile.getAbsolutePath());
				
				if(delfile.exists()) {
					System.out.println(TAG2 + "3. Del:member_image " + delfile.exists() + " --> ");
					boolean deleteFile = false;
					while(deleteFile != true){
						deleteFile = delfile.delete();
					}//while()     
					System.out.println(TAG2 + "삭제완료: " + deleteFile);
				}//if(delfile.exists())
			}//while(resultSet.next())
			
		} catch (Exception e) { e.printStackTrace(); System.out.println(TAG2 + "member_image 파일삭제 Exception!!!");
		} finally { dbClose(); }
		
		//2. tbl_member.member_date_delete(개인정보 삭제 예정일)가 경과한 탈퇴회원들의 개인정보를 삭제한다.
		try { System.out.println("member_date_delete(개인정보 삭제 예정일)이 경과한 회원님들의 개인정보를 삭제합니다.");

			query = "update tbl_member set "
					+ " member_pw = '' "
					+ " member_nick = '' "
					+ " member_tel = '' "
					+ " member_email = '' "
					+ " member_addr = '' "
					+ " member_image = '' "
					+ " where member_kind = 9 "				//검색조건1: 탈퇴회원 이고,
					+ " and member_date_delete > sysdate ";	//검색조건2: 개인정보 삭제 예정일이 지난 경우.

			prepareStatement = connection.prepareStatement(query);
			//prepareStatement.setInt(1, member_code);
			state = prepareStatement.executeUpdate();
			
			if (state > 0)	System.out.println(TAG2 + "개인정보 삭제 성공!");				
			else			System.out.println(TAG2 + "개인정보 삭제 실패~");

		} catch (Exception e) { e.printStackTrace(); System.out.println(TAG2 + "Exception!!!");
		} finally { dbClose(); }

		return state;	//DB처리 성공유(처리건수)무 반환.
	}//anMemberDeleteInfo() 회원-개인정보삭제, 회원-이미지파일 삭제

//	//로그인
//	public MemberDTO anMemberLogin(String member_id_in, String member_pw_in) {
//		MemberDTO adto = null;
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//		ResultSet resultSet = null;		
//		
//		try {
//			connection = dataSource.getConnection();
//			
//			String query = "select b.* ,  to_char(b.member_date , 'yyyy-MM-dd ')  "					
//							+ " from tbl_member b" 
//							+ " where b.member_id = '" + member_id_in + "' and b.member_pw = '" + member_pw_in + "' ";
//			prepareStatement = connection.prepareStatement(query);
//			resultSet = prepareStatement.executeQuery();
//			//System.out.println(member_id_in +", "+ member_pw_in);
//			while (resultSet.next()) {
//				int member_code = resultSet.getInt("member_code");
//				String member_id = resultSet.getString("member_id");
//				int member_kind = resultSet.getInt("member_kind");
//				String member_name = resultSet.getString("member_name");
//				String member_nick = resultSet.getString("member_nick");
//				String member_tel = resultSet.getString("member_tel"); 
//				String member_email = resultSet.getString("member_email"); 
//				String member_addr = resultSet.getString("member_addr"); 
//				String member_image = resultSet.getString("member_image"); 
//				String member_date = resultSet.getString("member_date"); 
//				System.out.println(member_date);
//				adto = new MemberDTO(member_code, member_id, member_kind, member_name, member_nick, member_tel, member_email, member_addr, member_image, member_date);							
//			}	
//			//System.out.println("MemberDTO id : " + adto.getMember_id());
//			//System.out.println("MemberDTO name : " + adto.getMember_name());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {			
//				
//				if (resultSet != null) {
//					resultSet.close();
//				}
//				if (prepareStatement != null) {
//					prepareStatement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}	
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//
//			}
//		}
//		return adto;
//	}
//	
//	//회원 가입
//	public int memberJoin(String member_id, String member_pw, String member_name, String member_nick,
//			String member_tel, String member_email, String member_image, String member_date) {
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//		int state = -100;
//		
//		//가입일자가 자동으로 입력되게 하면 좋겠지만,,, 힘들 수 있으니 
//		//가입일자 입력은 나중에 작업합시당~~
//		//Date 타입으로 하면 스프링에서 안드로이드로 가면서 문제가 생기는데 반대는 어떤지 모름
//		try {
//			connection = dataSource.getConnection();
//			String query = "insert into tbl_member(member_id, member_pw, member_name, member_nick, member_tel, member_email, member_image) " + 
//			               "values('" + member_id + "', '" + member_pw + "', '" + member_name + "', '" + member_nick + "', '" + member_tel + "', '" + 
//			               member_email + "', '" + member_image + "' )";
//			prepareStatement = connection.prepareStatement(query);
//			state = prepareStatement.executeUpdate();
//			
//			if (state > 0) {
//				System.out.println(state + "삽입성공");				
//			} else {
//				System.out.println(state + "삽입실패");
//			}
//			
//		} catch (Exception e) {			
//			System.out.println(e.getMessage());
//		} finally {
//			try {				
//				if (prepareStatement != null) {
//					prepareStatement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}	
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//
//			}
//		}
//
//		return state;
//	}
//	
//	//멤버 인서트(그냥 회원가입이면 되는거 아닌지)
//	public int memberInsert(String member_id, String member_pw, String member_name, String member_nick,
//			String member_tel, String member_email, String member_image, String member_date) {
//		
//		Connection connection = null;
//		PreparedStatement prepareStatement = null;
//		ResultSet resultSet = null;
//				
//		int state = -1;
//		
//		try {			
//			connection = dataSource.getConnection();
//			String query = "insert into tbl_member(member_id, member_pw, member_name, member_nick, member_tel, member_email, member_image) " + 
//		               "values('" + member_id + "', '" + member_pw + "', '" + member_name + "', '" + member_nick + "', '" + member_tel + "', '" + 
//		               member_email + "', '" + member_image + "' )";
//
//			prepareStatement = connection.prepareStatement(query);
//			state = prepareStatement.executeUpdate();
//			
//			if (state > 0) {
//				System.out.println(state + "삽입성공");				
//			} else {
//				System.out.println(state + "삽입실패");
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				if (resultSet != null) {
//					resultSet.close();
//				}
//				if (prepareStatement != null) {
//					prepareStatement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//
//		}
//		return state;
//	}
//	
//	
//	//내 정보 수정(이미지 변경)
//	public int memberUpdate(String member_id, String member_pw, String member_nick, String member_tel, String member_email, String member_image) {
//		
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		int state = -1;
//		
//		try {
//			//아이디는 수정 하면 안됨
//			connection = dataSource.getConnection();
//			String query = "";
//			
//			//비번 변경 여부
//			if(member_pw.equals("")) {
//				query = "update tbl_member set "
//					+ " member_nick= '" + member_nick + "'"
//					+ ", member_tel= '" + member_tel + "'"
//					+ ", member_email= '" + member_email + "'"
//					+ ", member_image= '" + member_image + "'"
//					+ " where member_id ='" + member_id + "'";
//			} else {
//				query = "update tbl_member set "
//					+ " member_pw= '" + member_pw + "'"
//					+ ", member_nick= '" + member_nick + "'"
//					+ ", member_tel= '" + member_tel + "'"
//					+ ", member_email= '" + member_email + "'"
//					+ ", member_image= '" + member_image + "'"
//					+ " where member_id ='" + member_id + "'";
//			}
//			
//			preparedStatement = connection.prepareStatement(query);
//			state = preparedStatement.executeUpdate();
//			
//			if(state > 0) {
//				System.out.println("수정1 성공");
//			} else {
//				System.out.println("수정1 실패");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				if (resultSet != null) {
//					resultSet.close();
//				}
//				if (preparedStatement != null) {
//					preparedStatement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return state;
//	}
//
//	//내 정보 수정(이미지 변경하지 않음)
//	public int memberUpdateNoimg(String member_id, String member_pw, String member_nick, String member_tel,
//			String member_email) {
//
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		int state = -1;
//		
//		try {
//			//아이디는 수정 하면 안됨
//			connection = dataSource.getConnection();
//			String query = "";
//			
//			//비번 변경 여부
//			if(member_pw.equals("")) {
//				query = "update tbl_member set "
//					+ " member_nick= '" + member_nick + "'"
//					+ ", member_tel= '" + member_tel + "'"
//					+ ", member_email= '" + member_email + "'"
//					+ " where member_id ='" + member_id + "'";
//			} else {
//				query = "update tbl_member set "
//					+ " member_pw= '" + member_pw + "'"
//					+ ", member_nick= '" + member_nick + "'"
//					+ ", member_tel= '" + member_tel + "'"
//					+ ", member_email= '" + member_email + "'"
//					+ " where member_id ='" + member_id + "'";
//			}
//			
//			preparedStatement = connection.prepareStatement(query);
//			state = preparedStatement.executeUpdate();
//			
//			if(state > 0) {
//				System.out.println("수정1 성공");
//			} else {
//				System.out.println("수정1 실패");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				if (resultSet != null) {
//					resultSet.close();
//				}
//				if (preparedStatement != null) {
//					preparedStatement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return state;
//	}

}
