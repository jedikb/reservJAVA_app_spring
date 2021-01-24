package com.hanul.reservJAVA_app.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

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
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String pDbImgPath = (String) req.getParameter("pDbImgPath");
		
		System.out.println(member_id);
		System.out.println(member_tel);
		System.out.println(member_email);
		System.out.println(member_image);
		System.out.println(pDbImgPath);
		System.out.println(dbImgPath);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_nick", member_nick);
		model.addAttribute("member_tel", member_tel);
		model.addAttribute("member_email", member_email);
		model.addAttribute("member_image", member_image);
					
		// 이미지가 서로 같으면 삭제하지 않고 다르면 기존이미지 삭제
		if(!member_image.equals(pDbImgPath)){			
			
			String pFileName = req.getParameter("pDbImgPath").split("/")[req.getParameter("pDbImgPath").split("/").length -1];
			String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/images/member/" + pFileName);
			
			File delfile = new File(delDbImgPath);
			//System.out.println("삭제할 파일 : " + delfile.getAbsolutePath());
			
	        if(delfile.exists()) {
	        	boolean deleteFile = false;
	            while(deleteFile != true){
	            	deleteFile = delfile.delete();
	            }	            
	            
	        }//if(delfile.exists())
		
		}//if(!member_image.equals(pDbImgPath))  
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		file = multi.getFile("image");
			
		if(file != null) {
			String fileName = req.getParameter("member_image").split("/")[req.getParameter("member_image").split("/").length -1];
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/images/member/");
				
				//System.out.println("복사할 파일 : " + realImgPath + fileName );
				//System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));						
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/images/member/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
		
		
		
		
		command = new AUpdateCommand();
		command.execute(model);
		
		return "memberUpdate";
	}
	
	//업로드 디렉토리 생성
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	
	
}
