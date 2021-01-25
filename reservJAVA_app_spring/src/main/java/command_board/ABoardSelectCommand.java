package command_board;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BoardDAO;
import reservJAVA_app.dto.BoardDTO;

public class ABoardSelectCommand implements ACommand{

	@Override
	public void execute(Model model) {
		BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardDTO> boardDTO = boardDAO.boardSelect();
		
		model.addAttribute("anBookingSelect", boardDTO);	
	}

}
