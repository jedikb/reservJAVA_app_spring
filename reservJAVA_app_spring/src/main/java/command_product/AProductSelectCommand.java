package command_product;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.ProductDAO;
import reservJAVA_app.dto.ProductDTO;

public class AProductSelectCommand implements ACommand {

	@Override
	public void execute(Model model) {
		
		String sbusiness_code =  model.getAttribute("business_code")+"";
		int business_code = Integer.parseInt( sbusiness_code);
		ProductDAO proDAO = new ProductDAO();
		ArrayList<ProductDTO> proDTOs = proDAO.productSelect(business_code);
		
		model.addAttribute("anProductSelect", proDTOs);		
	}

}
