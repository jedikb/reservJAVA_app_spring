package reservJAVA_app.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import command_business.ASearchBusinessCommand;
import command_member.AMemberDeleteCommand;
import command_member.AMemberJoinCommand;
import command_member.AMemberLoginCommand;
import command_member.AMemberReviewCommand;
import command_member.AMemberUpdateCommand;
import command_member.AMemberUpdateNoimgCommand;
import command_product.AProductSelectCommand;


@Controller
public class AController {

	ACommand command;
	
	//매장 검색
	@RequestMapping("/anSearchBusiness")
	public String SearchBusiness(HttpServletRequest req, Model model) {
		System.out.println("anSearchBusiness()");
		
		String searchText = (String) req.getParameter("searchText");
		
		model.addAttribute("searchText", searchText);
		
		command = new ASearchBusinessCommand();
		command.execute(model);
		
		return "anSearchBusiness";
	}
	
	
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
		
		return "anMemberLogin";
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
		
		return "anMemberJoin";
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
		
		String business_member_code = (String) req.getParameter("business_member_code");
		
		command = new ABusinessSelectCommand();
		command.execute(model);
		
		return "anBusinessSelect";
	}
	
	//상품 리스트
	@RequestMapping(value="/anProductSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anProductSelect(HttpServletRequest req, Model model){
		System.out.println("anProductSelect()");
		
		// 실제 테스트 해보지 않아서 맞는지 모르니 확인해보세요
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		int product_business_code = Integer.parseInt(req.getParameter("product_business_code"));
		
		command = new AProductSelectCommand();
		command.execute(model);
		
		return "anProductSelect";
	}

	//예약 리스트(일단 예약자료만,, 멤버 연동은 모름;;)
	@RequestMapping(value="/anBookingSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBookingSelect(HttpServletRequest req, Model model){
		System.out.println("anBookingSelect()");
		
		//아마 member_code와 business_code, product_code의 3조건으로 검색해야 할 것 같음.
		int member_code = Integer.parseInt(req.getParameter("member_code"));
		int business_code = Integer.parseInt(req.getParameter("business_code"));
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		
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
	@RequestMapping(value="/anMemberReview", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anMemberReview(HttpServletRequest req, Model model){
		System.out.println("anMemberReview()");
//
//		int 	BOOKING_KIND = Integer.parseInt(req.getParameter("BOOKING_KIND"));
//		int 	BOOKING_MEMBER_CODE = Integer.parseInt(req.getParameter("BOOKING_MEMBER_CODE"));
//		int		 BOOKING_BUSINESS_CODE = Integer.parseInt(req.getParameter("BOOKING_BUSINESS_CODE"));
//		int 	BOOKING_PRODUCT_CODE = Integer.parseInt(req.getParameter("BOOKING_PRODUCT_CODE"));
//		int 	BOOKING_PRICE = Integer.parseInt(req.getParameter("BOOKING_PRICE"));
//		int		 BOOKING_PRICE_DEPOSIT = Integer.parseInt(req.getParameter("BOOKING_PRICE_DEPOSIT"));
//		int			BOOKING_NUM = Integer.parseInt(req.getParameter("BOOKING_NUM"));
//		String 	BOOKING_DATE = (String) req.getParameter("BOOKING_DATE");
//		String 	BOOKING_DATE_RESERVATION = (String) req.getParameter("BOOKING_DATE_RESERVATION");
//		String 	BOOKING_ETC = (String) req.getParameter("BOOKING_ETC");
//		int 		BOOKING_APPRAISAL_STAR = Integer.parseInt(req.getParameter("BOOKING_APPRAISAL_STAR"));
//		String	 BOOKING_APPRAISAL = (String) req.getParameter("BOOKING_APPRAISAL");
//		
//		int 	BOOKING_CODE			  =1;		값을 받아와서 넣을수 없다
		int 	BOOKING_KIND              =	2;
		int 	BOOKING_MEMBER_CODE       =	101;
		int		BOOKING_BUSINESS_CODE     =	101;
		int 	BOOKING_PRODUCT_CODE      =	101;
		int 	BOOKING_PRICE             =	6000;
		int		BOOKING_PRICE_DEPOSIT     =	0;
		int		BOOKING_NUM               =	1;
		String  BOOKING_DATE              =	"21/01/12";
		String  BOOKING_DATE_RESERVATION  =	"21/01/13";
		String  BOOKING_ETC               =	"고객요청사항001";
		int   	BOOKING_APPRAISAL_STAR    =	5;
		String  BOOKING_APPRAISAL         =	"평가내용001";
		
		
		
		System.out.println(BOOKING_KIND);
		System.out.println(BOOKING_MEMBER_CODE);
		System.out.println(BOOKING_BUSINESS_CODE);
		System.out.println(BOOKING_PRODUCT_CODE);
		System.out.println(BOOKING_PRICE);
		System.out.println(BOOKING_PRICE_DEPOSIT);
		System.out.println(BOOKING_NUM);
		System.out.println(BOOKING_DATE);
		System.out.println(BOOKING_DATE_RESERVATION);
		System.out.println(BOOKING_ETC);
		System.out.println(BOOKING_APPRAISAL_STAR);
		System.out.println(BOOKING_APPRAISAL);

		
		model.addAttribute("BOOKING_KIND", BOOKING_KIND);
		model.addAttribute("BOOKING_MEMBER_CODE", BOOKING_MEMBER_CODE);
		model.addAttribute("BOOKING_BUSINESS_CODE", BOOKING_BUSINESS_CODE);
		model.addAttribute("BOOKING_PRODUCT_CODE", BOOKING_PRODUCT_CODE);
		model.addAttribute("BOOKING_PRICE", BOOKING_PRICE);
		model.addAttribute("BOOKING_PRICE_DEPOSIT", BOOKING_PRICE_DEPOSIT);
		model.addAttribute("BOOKING_NUM", BOOKING_NUM);
		model.addAttribute("BOOKING_DATE", BOOKING_DATE);
		model.addAttribute("BOOKING_DATE_RESERVATION", BOOKING_DATE_RESERVATION);
		model.addAttribute("BOOKING_ETC", BOOKING_ETC);
		model.addAttribute("BOOKING_APPRAISAL_STAR", BOOKING_APPRAISAL_STAR);
		model.addAttribute("BOOKING_APPRAISAL", BOOKING_APPRAISAL);

		command = new AMemberReviewCommand();
		command.execute(model);
		
		return "anMemberReview";
		
		
	}
	
	//리뷰 리스트(업체)
	
}
