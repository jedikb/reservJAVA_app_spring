package com.hanul.reservJAVA_app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.reservJAVA_app.dao.BusinessDAO;
import com.hanul.reservJAVA_app.dto.BusinessDTO;

public class ABusinessSelectCommand implements ACommand {

	@Override
	public void execute(Model model) {
		BusinessDAO busiDAO = new BusinessDAO();
		ArrayList<BusinessDTO> busiDTO = busiDAO.businessSelect();
		
		model.addAttribute("anBusinessSelect", busiDTO);
	}

}
