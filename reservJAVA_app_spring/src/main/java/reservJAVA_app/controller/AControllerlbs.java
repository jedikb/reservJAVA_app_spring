package reservJAVA_app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.ACommand;

import command_member.AMemberCancelCommand;
import command_member.AMemberDeleteInfoCommand;
import reservJAVA_app.dao.MemberDAOlbs;

@Controller
public class AControllerlbs {
    private static final String TAG = "AControllerlbs.";

	ACommand command;
	MemberDAOlbs dao;
	
	//회원 탈퇴
	@RequestMapping(value="/anMemberCancel", method = {RequestMethod.GET, RequestMethod.POST})
	public String anMemberCancel(HttpServletRequest req, Model model){
	    String TAG2 = TAG + "anMemberCancel(): ";
		
		//model.addAttribute("member_code", Integer.parseInt( (String)req.getParameter("member_code") ));
		model.addAttribute("member_code", (String)req.getParameter("member_code") );
		//model.addAttribute("member_code", dao.anMemberCancel( req.getParameter("member_code") ));
		System.out.println(TAG2 + "member_code= " + (String)req.getParameter("member_code"));
		
		command = new AMemberCancelCommand();
		command.execute(model);

		return "anMemberCancel";
	}//anMemberCancel()

	//회원-정보삭제 처리
	@RequestMapping(value="/anMemberDeleteInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String anMemberDeleteInfo(HttpServletRequest req, Model model){
	    String TAG2 = TAG + "anMemberDeleteInfo(): ";
		System.out.println(TAG2);
		
		command = new AMemberDeleteInfoCommand();
		command.execute(model);

		return "anMemberDeleteInfo";
	}//anMemberDeleteInfo()
	
}//class AControllerlbs
