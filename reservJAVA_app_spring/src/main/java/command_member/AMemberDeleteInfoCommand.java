package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAOlbs;

public class AMemberDeleteInfoCommand implements ACommand {

	@Override
	public void execute(Model model) {
		//int member_id = Integer.parseInt((String)model.asMap().get("member_id"));
		
		MemberDAOlbs adao = new MemberDAOlbs();
		int state = adao.anMemberDeleteInfo();

		model.addAttribute("anMemberDeleteInfo", String.valueOf(state)); 
	}

}
