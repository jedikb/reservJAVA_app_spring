package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAOlbs;

public class AMemberCancelCommand implements ACommand {

	@Override
	public void execute(Model model) {
		int member_id = (Integer) model.asMap().get("member_id");
		
		MemberDAOlbs adao = new MemberDAOlbs();
		int state = adao.anMemberCancel(member_id);
		
		model.addAttribute("anMemberCancel", String.valueOf(state)); 
	}

}
