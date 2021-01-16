package com.hanul.reservJAVA_app.command;

import org.springframework.ui.Model;

import com.hanul.reservJAVA_app.dao.ANDAO;
import com.hanul.reservJAVA_app.dto.MemberDTO;

public class ALoginCommand implements ACommand{

	@Override
	public void execute(Model model) {		
		String member_id = (String)model.asMap().get("member_id");
		String member_pw = (String)model.asMap().get("member_pw");	
		
		ANDAO adao = new ANDAO();
		MemberDTO adto = adao.anLogin(member_id, member_pw);
		
		model.addAttribute("anLogin", adto); 
		
	}
	
}
