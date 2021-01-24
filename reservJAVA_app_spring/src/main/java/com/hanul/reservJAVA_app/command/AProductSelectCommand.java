package com.hanul.reservJAVA_app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.reservJAVA_app.dao.ProductDAO;
import com.hanul.reservJAVA_app.dto.ProductDTO;

public class AProductSelectCommand implements ACommand {

	@Override
	public void execute(Model model) {
		ProductDAO proDAO = new ProductDAO();
		ArrayList<ProductDTO> proDTO = proDAO.businessSelect();
		
		model.addAttribute("anProductSelect", proDTO);		
	}

}
