package com.hanul.reservJAVA_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.reservJAVA_app.dto.MemberDTO;
import com.hanul.reservJAVA_app.dto.ReservDTO;

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
	public MemberDTO anLogin(String member_id, String member_pw) {
		MemberDTO adto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from member" 
							+ " where id = '" + member_id + "' and passwd = '" + member_pw + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String member_code = resultSet.getString("member_code");
				String member_id2 = resultSet.getString("member_id");
				String member_name = resultSet.getString("member_name");
				String member_nick = resultSet.getString("member_nick");
				String member_tel = resultSet.getString("member_tel"); 

				adto = new MemberDTO(member_code, member_id2, member_name, member_nick, member_tel);							
			}	
			
			System.out.println("MemberDTO id : " + adto.getMember_id());
			
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
	public MemberDTO memberUpdate(String member_name, String member_nick, String member_tel) {
		MemberDTO dto = new MemberDTO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// 임시 아이디
			String member_id = "id101";
			connection = dataSource.getConnection();
			String query = "select member_name, member_nick, member_tel "
					+ " from tbl_member where id =" + member_id;
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
		return dto;
	}

	 
	 
	
}
