package reservJAVA_app.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import command.ACommand;
import command_board.ABoardSelectCommand;
import command_booking.ABookingSelectCommand;
import command_business.ABusinessSelectCommand;
import command_member.AMemberDeleteCommand;
import command_member.AMemberInsertCommand;
import command_member.AMemberJoinCommand;
import command_member.AMemberLoginCommand;
import command_member.AMemberUpdateCommand;
import command_member.AMemberUpdateNoimgCommand;
import command_product.AProductSelectCommand;


@Controller
public class AController {

	ACommand command;
	
	//이미지 업로드 디렉토리 생성
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
			f.mkdir();
		}	
	}
	
	//로그인
	@RequestMapping(value="/anMemberLogin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anMemberLogin(HttpServletRequest req, Model model){
		System.out.println("anMemberLogin()");
		
		String member_id = (String) req.getParameter("member_id");
		String member_pw = (String) req.getParameter("member_pw");
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		
		command = new AMemberLoginCommand();
		command.execute(model);
		
		return "anLogin";
	}
	
	//회원가입
	@RequestMapping(value="/anMemberJoin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anMemberJoin(HttpServletRequest req, Model model){
		System.out.println("anMemberJoin()");

		String member_id = (String) req.getParameter("member_id");
		String member_pw = (String) req.getParameter("member_pw");
		String member_name = (String) req.getParameter("member_name");
		String member_nick = (String) req.getParameter("member_nick");
		String member_tel = (String) req.getParameter("member_tel");
		String member_email = (String) req.getParameter("member_email");
		String member_image = (String) req.getParameter("member_image");
		//Date member_date = (String) req.getParameter("member_date");
		
		System.out.println(member_id);
		System.out.println(member_pw);
		System.out.println(member_name);

		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_name", member_name);
		model.addAttribute("member_nick", member_nick);
		model.addAttribute("member_tel", member_tel);
		model.addAttribute("member_email", member_email);
		model.addAttribute("member_image", member_image);
		//model.addAttribute("member_date", member_date);
		
		command = new AMemberJoinCommand();
		command.execute(model);
		
		return "anJoin";
	}
	
	//멤버 인서트(이건 뭐에 쓰는거?)
	//잘모르겠지만,, 업데이트에 쓰는 것과 비슷한것 같아 참고해서 넣음
	@RequestMapping(value="/anMemberInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anMemberInsert(HttpServletRequest req, Model model){
		System.out.println("anMemberInsert()");	
		
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
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("image");
		
			
		if(file != null) {
			//여기 파일 네임 확인해야 할듯??
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/images/member/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/images/member/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
				
		command = new AMemberInsertCommand();
		command.execute(model);
		
		return "anMemberInsert";
	}
	
	//회원 정보 업데이트(이미지 변경)
	@RequestMapping(value="/anMemberUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberUpdate(HttpServletRequest req, Model model) {
		System.out.println("anMemberUpdate()");
		
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
				
		command = new AMemberUpdateCommand();
		command.execute(model);
	}
	
	//회원 정보 업데이트(이미지 변경 없음)
	@RequestMapping(value="/anMemberUpdateNoimg", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberUpdateNoimg(HttpServletRequest req, Model model) {
		System.out.println("anMmemberUpdateNoimg()");
		
		String member_id = req.getParameter("member_id");
		String member_pw = req.getParameter("member_pw");
		String member_nick = req.getParameter("member_nick");
		String member_tel = req.getParameter("member_tel");
		String member_email = req.getParameter("member_email");
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_nick", member_nick);
		model.addAttribute("member_tel", member_tel);
		model.addAttribute("member_email", member_email);
				
		command = new AMemberUpdateNoimgCommand();
		command.execute(model);
	}
	
	//회원 탈퇴	
	@RequestMapping(value="/anMemberDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberDelete(HttpServletRequest req, Model model){
		System.out.println("anMemberDelete()");		
		
		model.addAttribute("member_id", req.getParameter("member_id"));		
				
		System.out.println((String)req.getParameter("id"));
		System.out.println((String)req.getParameter("delDbImgPath"));
		
		String pFileName = req.getParameter("delDbImgPath").split("/")[req.getParameter("delDbImgPath").split("/").length -1];
		String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/images/member/" + pFileName);		
		
		// 이미지 파일지우기
		File delfile = new File(delDbImgPath);
		System.out.println(delfile.getAbsolutePath());
		
        if(delfile.exists()) {
            System.out.println("Del:pDelImagePath " + delfile.exists());
            boolean deleteFile = false;
            while(deleteFile != true){
            	deleteFile = delfile.delete();
            }     
        }	
		
		command = new AMemberDeleteCommand();
		command.execute(model);	
	}

	//업체 리스트
	@RequestMapping(value="/anBusinessSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBusinessSelect(HttpServletRequest req, Model model){
		System.out.println("anBusinessSelect()");
		
		command = new ABusinessSelectCommand();
		command.execute(model);
		
		return "anBusinessSelect";
	}
	
	//상품 리스트
	@RequestMapping(value="/anProductSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anProductSelect(HttpServletRequest req, Model model){
		System.out.println("anProductSelect()");
		
		command = new AProductSelectCommand();
		command.execute(model);
		
		return "anProductSelect";
	}

	//예약 리스트(일단 예약자료만,, 멤버 연동은 모름;;)
	@RequestMapping(value="/anBookingSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBookingSelect(HttpServletRequest req, Model model){
		System.out.println("anBookingSelect()");
		
		command = new ABookingSelectCommand();
		command.execute(model);
		
		return "anBookingSelect";
	}
	
	//문의 리스트(아이디에 따른 검색)
	@RequestMapping(value="/anBoardSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBoardSelect(HttpServletRequest req, Model model){
		System.out.println("anBoardSelect()");
		
		command = new ABoardSelectCommand();
		command.execute(model);
		
		return "anBoardSelect";
	}
	
	
	
	
	// 이 밑은 고민해봐야 할듯...
	
	//카테고리 리스트(얘는 여기에 business 연동해야 함)
	
	
	
	
	//방문 리스트(멤버)
	
	//리뷰 리스트(멤버)
	
	//리뷰 리스트(업체)
	
}
