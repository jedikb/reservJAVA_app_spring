package com.hanul.reservJAVA_app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.reservJAVA_app.command.ACommand;
import com.hanul.reservJAVA_app.command.ALoginCommand;
import com.hanul.reservJAVA_app.command.AUpdateCommand;


@Controller
public class AController {

	ACommand command;
	
	//로그인
	@RequestMapping(value="/anLogin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anLogin(HttpServletRequest req, Model model){
		System.out.println("anLogin()");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		
		String member_id = (String) req.getParameter("member_id");
		String member_pw = (String) req.getParameter("member_pw");
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		
		command = new ALoginCommand();
		command.execute(model);
		
		return "anLogin";
	}
	
	
	//회원 정보 업데이트
	@RequestMapping(value="/memberUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberDetail(HttpServletRequest req, Model model) {
		System.out.println("memberUpdate()");
		
		String member_id = req.getParameter("member_id");
		String member_pw = req.getParameter("member_pw");
		String member_nick = req.getParameter("member_nick");
		String member_tel = req.getParameter("member_tel");
		String member_email = req.getParameter("member_email");
		String member_image = req.getParameter("member_image");
		
		System.out.println(member_id);
		System.out.println(member_pw);
		System.out.println(member_nick);
		System.out.println(member_tel);
		System.out.println(member_email);
		System.out.println(member_image);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_nick", member_nick);
		model.addAttribute("member_tel", member_tel);
		model.addAttribute("member_email", member_email);
		model.addAttribute("member_image", member_image);
		
		command = new AUpdateCommand();
		command.execute(model);
		
		return "memberUpdate";
	}
	
	
}
