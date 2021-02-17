package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAOlbs;

public class AMemberCancelCommand implements ACommand {
    private static final String TAG = "AMemberCancelCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";

		int member_code = Integer.parseInt((String)model.asMap().get("member_code"));
		//int member_code = (int) model.asMap().get("member_code");
		//String member_name = (String)model.asMap().get("member_name");
		System.out.println(TAG2 + "member_code= " + member_code + " 번 회원님이 탈퇴 요청하였습니다.");
		
		MemberDAOlbs adao = new MemberDAOlbs();
		int state = adao.anMemberCancel(member_code);

		System.out.println(TAG2 + "state= " + state);
		model.addAttribute("anMemberCancel", String.valueOf(state)); 
	}//execute()

}//class AMemberCancelCommand
