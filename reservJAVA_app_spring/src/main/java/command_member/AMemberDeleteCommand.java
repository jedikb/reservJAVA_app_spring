package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAO;

public class AMemberDeleteCommand implements ACommand {

	@Override
	public void execute(Model model) {
		String member_id = (String) model.asMap().get("member_id");
		
		MemberDAO adao = new MemberDAO();
		adao.anMemberDelete(member_id);
	}

}
