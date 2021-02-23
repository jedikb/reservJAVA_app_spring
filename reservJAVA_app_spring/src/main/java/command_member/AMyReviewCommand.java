package command_member;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAO;
import reservJAVA_app.dto.ReviewDTO;

public class AMyReviewCommand implements ACommand {

	@Override
	public void execute(Model model) {
		
		MemberDAO adao = new MemberDAO();
		ArrayList<ReviewDTO> reviewDTOs = adao.anMyReview(model);
		
		model.addAttribute("anMyReview", reviewDTOs);
	}

}
