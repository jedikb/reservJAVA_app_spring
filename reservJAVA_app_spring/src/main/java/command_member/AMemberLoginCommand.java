package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAO;
import reservJAVA_app.dto.MemberDTO;

public class AMemberLoginCommand implements ACommand{

	@Override
	public void execute(Model model) {		
		String member_id_in = (String)model.asMap().get("member_id");
		String member_pw_in = (String)model.asMap().get("member_pw");	
		
		MemberDAO adao = new MemberDAO();
		MemberDTO adto = adao.anLogin(member_id_in, member_pw_in);
		
		model.addAttribute("anLogin", adto); 
		
	}
	
}
