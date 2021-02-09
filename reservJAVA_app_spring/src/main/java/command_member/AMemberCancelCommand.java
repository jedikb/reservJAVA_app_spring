package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAOlbs;

public class AMemberCancelCommand implements ACommand {
    private static final String TAG = "AMemberCancelCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";
		System.out.println(TAG2 + "member_id= " + model.asMap().get("member_id") );
		int member_id = Integer.parseInt((String)model.asMap().get("member_id"));
		//int member_id = (int) model.asMap().get("member_id");
		//String member_name = (String)model.asMap().get("member_name");
		System.out.println(TAG2 + "member_id= " + member_id);
		
		MemberDAOlbs adao = new MemberDAOlbs();
		int state = adao.anMemberCancel(member_id);
		System.out.println(TAG2 + "state= " + state);
		
		model.addAttribute("anMemberCancel", String.valueOf(state)); 
	}

}
