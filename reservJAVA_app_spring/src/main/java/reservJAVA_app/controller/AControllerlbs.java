package reservJAVA_app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.ACommand;

import command_member.AMemberCancelCommand;
import command_member.AMemberDeleteInfoCommand;

@Controller
public class AControllerlbs {

	ACommand command;
	
	//회원 탈퇴	
	@RequestMapping(value="/anMemberCancel", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberCancel(HttpServletRequest req, Model model){
		System.out.println("anMemberCancel() " + (String)req.getParameter("member_code"));		
		
		model.addAttribute("member_code", req.getParameter("member_code"));		
				
		command = new AMemberCancelCommand();
		command.execute(model);	
	}

	//회원-정보삭제 처리	
	@RequestMapping(value="/anMemberDeleteInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberDeleteInfo(HttpServletRequest req, Model model){
		System.out.println("anMemberDeleteInfo()");		
		
		command = new AMemberDeleteInfoCommand();
		command.execute(model);	
	}//anMemberDeleteInfo()
	
}
