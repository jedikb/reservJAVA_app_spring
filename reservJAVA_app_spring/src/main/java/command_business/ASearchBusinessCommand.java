package command_business;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BusinessDAO;
import reservJAVA_app.dto.BusinessDTO;

public class ASearchBusinessCommand implements ACommand{

	@Override
	public void execute(Model model) {
		BusinessDAO busiDAO = new BusinessDAO();
		ArrayList<BusinessDTO> busiDTOs = busiDAO.searchBusiness(model);
		
		model.addAttribute("anSearchBusiness", busiDTOs);
		//model.addAttribute("anSearchBusiness", busiDTOs.size()==0? null : busiDTOs);
		//System.out.println("busiDTOs.size() : " + busiDTOs.size());
		ArrayList<BusinessDTO> business_category_code1= (ArrayList<BusinessDTO>) model.getAttribute("business_category_code1");
		System.out.println("ASearchBusinessCommand : business_category_code1" + business_category_code1);
		
	}

}
