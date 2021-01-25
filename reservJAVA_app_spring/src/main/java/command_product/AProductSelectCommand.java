package command_product;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.ProductDAO;
import reservJAVA_app.dto.ProductDTO;

public class AProductSelectCommand implements ACommand {

	@Override
	public void execute(Model model) {
		ProductDAO proDAO = new ProductDAO();
		ArrayList<ProductDTO> proDTO = proDAO.businessSelect();
		
		model.addAttribute("anProductSelect", proDTO);		
	}

}
