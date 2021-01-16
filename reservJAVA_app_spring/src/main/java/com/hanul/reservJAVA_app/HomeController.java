package com.hanul.reservJAVA_app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.reservJAVA_app.command.ACommand;
import com.hanul.reservJAVA_app.command.AUpdateCommand;
import com.hanul.reservJAVA_app.dao.ANDAO;
import com.hanul.reservJAVA_app.dto.ReservDTO;

@Controller
public class HomeController {
	
	ACommand command;
	
	@RequestMapping(value="/memberUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberDetail(HttpServletRequest req, Model model) {
		System.out.println("memberDetail()");
		
		String member_name = req.getParameter("member_name");
		String member_nick = req.getParameter("member_nick");
		String member_tel = req.getParameter("member_tel");
		
		System.out.println(member_name);
		System.out.println(member_nick);
		System.out.println(member_tel);
		
		model.addAttribute("member_name", member_name);
		model.addAttribute("member_nick", member_nick);
		model.addAttribute("member_tel", member_tel);
		
		command = new AUpdateCommand();
		command.execute(model);
		
		return "memberUpdate";
	}
	
	
	
	
	
	
	
	/*//웹 접속
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(HttpSession session) { //session.removeAttribute("category"); return
	 * "home"; }
	 */
	
	
	
}
