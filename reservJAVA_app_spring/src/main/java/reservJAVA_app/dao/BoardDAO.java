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

import reservJAVA_app.dto.BoardDTO;

public class BoardDAO {
	DataSource dataSource;
	
	public BoardDAO() { 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam"); 
		} catch (NamingException e) {
			e.getMessage(); 
		} 
	}
	
	//예약 리스트 조회
	public ArrayList<BoardDTO> boardSelect() {
		
		ArrayList<BoardDTO> boardDTOs = new ArrayList<BoardDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from tbl_board" 
							+ " order by board_code desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int board_code = resultSet.getInt("board_code");
				int board_status = resultSet.getInt("board_status");
				int board_group_code = resultSet.getInt("board_group_code");
				int board_member_code = resultSet.getInt("board_member_code");
				String board_subject = resultSet.getString("board_subject");
				String board_content = resultSet.getString("board_content");
				Date board_write_date = resultSet.getDate("board_write_date");
				Date board_update_date = resultSet.getDate("board_update_date");
				int board_readcount = resultSet.getInt("board_readcount");
				String board_file = resultSet.getString("board_file");
				
				
				BoardDTO boardDTO = new BoardDTO(board_code, board_status, board_group_code,
						board_member_code, board_subject, board_content, board_write_date, board_update_date, board_readcount, board_file);
				boardDTOs.add(boardDTO);			
			}	
			
			System.out.println("bDTOs크기" + boardDTOs.size());
			
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
		return boardDTOs;
	}

}
