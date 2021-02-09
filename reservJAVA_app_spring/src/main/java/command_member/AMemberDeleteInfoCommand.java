package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAOlbs;

public class AMemberDeleteInfoCommand implements ACommand {
    private static final String TAG = "AMemberDeleteInfoCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";
		System.out.println(TAG2);
		
		MemberDAOlbs adao = new MemberDAOlbs();
		int state = adao.anMemberDeleteInfo();
		System.out.println(TAG2 + "state= " + state);
		
		model.addAttribute("anMemberDeleteInfo", String.valueOf(state)); 

	}//execute()

}//class AMemberDeleteInfoCommand
