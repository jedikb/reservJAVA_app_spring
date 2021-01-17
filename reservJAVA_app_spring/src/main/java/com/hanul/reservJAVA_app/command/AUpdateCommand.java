package com.hanul.reservJAVA_app.command;

import org.springframework.ui.Model;

import com.hanul.reservJAVA_app.dao.ANDAO;

public class AUpdateCommand implements ACommand{

	@Override
	public void execute(Model model) {
		
		String member_id = (String) model.asMap().get("member_id");
		String member_pw = (String) model.asMap().get("member_pw");
		String member_name = (String) model.asMap().get("member_name");
		String member_nick = (String) model.asMap().get("member_nick");
		String member_tel = (String) model.asMap().get("member_tel");
		String member_email = (String) model.asMap().get("member_email");
		
		ANDAO adao = new ANDAO();
		adao.memberUpdate(member_id, member_pw, member_name, member_nick, member_tel, member_email);
		
	}

}
