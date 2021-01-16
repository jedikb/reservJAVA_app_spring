package com.hanul.reservJAVA_app.command;

import org.springframework.ui.Model;

import com.hanul.reservJAVA_app.dao.ANDAO;

public class AUpdateCommand implements ACommand{

	@Override
	public void execute(Model model) {
		
		String member_name = (String) model.asMap().get("member_name");
		String member_nick = (String) model.asMap().get("member_nick");
		String member_tel = (String) model.asMap().get("member_tel");
		
		ANDAO rdao = new ANDAO();
		rdao.memberUpdate(member_name, member_nick, member_tel);
		
	}

}
