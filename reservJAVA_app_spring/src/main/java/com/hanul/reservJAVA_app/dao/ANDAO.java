package com.hanul.reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.reservJAVA_app.dto.MemberDTO;

public class ANDAO {

	DataSource dataSource;
	
	public ANDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}

	//로그인
	public MemberDTO anLogin(String member_id_in, String member_pw_in) {
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
	
	//내 정보 수정(우선은 조회만)
	public int memberUpdate(String member_id, String member_pw, String member_name, String member_nick, String member_tel, String member_email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
		
		try {
			//아이디는 수정 하면 안됨
			connection = dataSource.getConnection();
			String query = "update tbl_member set "
					+ ", member_pw= '" + member_pw + "'"
					+ ", member_name= '" + member_name + "'"
					+ ", member_nick= '" + member_nick + "'"
					+ ", member_tel= '" + member_tel + "'"
					+ ", member_email= '" + member_email + "'"
					+ " where id =" + member_id;
			
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

	 
	 
	
}
