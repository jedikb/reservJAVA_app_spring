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
import command_booking.BookingInsertCommand;
import command_business.ABusinessSelectCommand;
import command_business.ASearchBusinessCommand;
import command_member.AMemberDeleteCommand;
import command_member.AMemberJoinCommand;
import command_member.AMemberLoginCommand;
import command_member.AMemberUpdateCommand;
import command_member.AMemberUpdateNoimgCommand;
import command_member.AMyReviewCommand;
import command_product.AProductSelectCommand;
import command_product.ATimeListCommand;


@Controller
public class AController {

	ACommand command;
	@RequestMapping("/anMyReview")
	//�옉�꽦�븳 由щ럭 由ъ뒪�듃 議고쉶
	public String MyReview(HttpServletRequest req, Model model) {
		System.out.println("anMyReview()");
		
		String member_code = req.getParameter("member_code");
		
		model.addAttribute("member_code", member_code);
		
		command = new AMyReviewCommand();
		command.execute(model);
		
		return "anMyReview";
	}
	
	
	
	//留ㅼ옣 寃��깋
	@RequestMapping(value = "/anSearchBusiness", method = {RequestMethod.GET, RequestMethod.POST})
	public String SearchBusiness(HttpServletRequest req, Model model) {
		System.out.println("anSearchBusiness()");
		
		String searchText = (String) req.getParameter("searchText");
		
		model.addAttribute("searchText", searchText);
		
		command = new ASearchBusinessCommand();
		command.execute(model);
		
		return "anSearchBusiness";
	}
	
	
	//�씠誘몄� �뾽濡쒕뱶 �뵒�젆�넗由� �깮�꽦
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
			f.mkdir();
		}	
	}
	
	//濡쒓렇�씤
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
	
	//�쉶�썝媛��엯
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
	
	//예약 정보 데이터 등록
	@RequestMapping(value="/anBookingInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBookingInsert(HttpServletRequest req, Model model){
		System.out.println("anBookingInsert()");
		
//		int member_code = Integer.parseInt(req.getParameter("member_code"));
//		int business_code = Integer.parseInt(req.getParameter("business_code"));
//		int product_code = Integer.parseInt(req.getParameter("product_code"));
//		int product_price = Integer.parseInt(req.getParameter("product_price"));
//		int price_deposit = Integer.parseInt(req.getParameter("price_deposit"));
//		int booking_num = Integer.parseInt(req.getParameter("booking_num"));
		
		String member_code = req.getParameter("member_code");
		String business_code = req.getParameter("business_code");
		String product_code = req.getParameter("product_code");
		String product_price = req.getParameter("product_price");
		String price_deposit = req.getParameter("price_deposit");
		String booking_num = req.getParameter("booking_num");
		String booking_date_reservation = req.getParameter("booking_date_reservation");
		
		System.out.println(member_code);
		System.out.println(business_code);
		System.out.println(product_code);
		System.out.println(booking_date_reservation);
		
		
		model.addAttribute("member_code", member_code);
		model.addAttribute("business_code", business_code);
		model.addAttribute("product_code", product_code);
		model.addAttribute("product_price", product_price);
		model.addAttribute("price_deposit", price_deposit);
		model.addAttribute("booking_num", booking_num);
		model.addAttribute("booking_date_reservation", booking_date_reservation);
		
		
		command = new BookingInsertCommand();
		command.execute(model);
		
		return "anBookingInsert";
	}
	
	//�쉶�썝 �젙蹂� �뾽�뜲�씠�듃(�씠誘몄� 蹂�寃�)
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
					
		// �씠誘몄�媛� �꽌濡� 媛숈쑝硫� �궘�젣�븯吏� �븡怨� �떎瑜대㈃ 湲곗〈�씠誘몄� �궘�젣
		if(!member_image.equals(pDbImgPath)){			
			
			String pFileName = req.getParameter("pDbImgPath").split("/")[req.getParameter("pDbImgPath").split("/").length -1];
			String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/images/member/" + pFileName);
			
			File delfile = new File(delDbImgPath);
			//System.out.println("�궘�젣�븷 �뙆�씪 : " + delfile.getAbsolutePath());
			
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
			
			// �뵒�젆�넗由� 議댁옱�븯吏� �븡�쑝硫� �깮�꽦
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/images/member/");
				
				//System.out.println("蹂듭궗�븷 �뙆�씪 : " + realImgPath + fileName );
				//System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// �씠誘몄��뙆�씪 ���옣
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
	
	//�쉶�썝 �젙蹂� �뾽�뜲�씠�듃(�씠誘몄� 蹂�寃� �뾾�쓬)
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
	
	//�쉶�썝 �깉�눜	
	@RequestMapping(value="/anMemberDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public void anMemberDelete(HttpServletRequest req, Model model){
		System.out.println("anMemberDelete()");		
		
		model.addAttribute("member_id", req.getParameter("member_id"));		
				
		System.out.println((String)req.getParameter("id"));
		System.out.println((String)req.getParameter("delDbImgPath"));
		
		String pFileName = req.getParameter("delDbImgPath").split("/")[req.getParameter("delDbImgPath").split("/").length -1];
		String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/images/member/" + pFileName);		
		
		// �씠誘몄� �뙆�씪吏��슦湲�
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

	//�뾽泥� 由ъ뒪�듃
	@RequestMapping(value="/anBusinessSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBusinessSelect(HttpServletRequest req, Model model){
		System.out.println("anBusinessSelect()");
		
		String business_member_code = (String) req.getParameter("business_member_code");
		
		command = new ABusinessSelectCommand();
		command.execute(model);
		
		return "anBusinessSelect";
	}
	
	//�긽�뭹 由ъ뒪�듃
	@RequestMapping(value="/anProductSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anProductSelect(HttpServletRequest req, Model model){
		System.out.println("anProductSelect()");
		
		
		int business_code = Integer.parseInt(req.getParameter("business_code"));
		model.addAttribute("business_code", business_code);
		
		command = new AProductSelectCommand();
		command.execute(model);
		
		return "anProductSelect";
	}
	
	@RequestMapping(value="/anTimeList", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anTimeList(HttpServletRequest req, Model model){
		System.out.println("anTimeList");
		
		// �떎�젣 �뀒�뒪�듃 �빐蹂댁� �븡�븘�꽌 留욌뒗吏� 紐⑤Ⅴ�땲 �솗�씤�빐蹂댁꽭�슂
		int business_code = Integer.parseInt(req.getParameter("business_code"));
		model.addAttribute("business_code", business_code);
		
		command = new ATimeListCommand();
		command.execute(model);
		
		return "anTimeList";
	}
	

	//�삁�빟 由ъ뒪�듃(�씪�떒 �삁�빟�옄猷뚮쭔,, 硫ㅻ쾭 �뿰�룞�� 紐⑤쫫;;)
	@RequestMapping(value="/anBookingSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBookingSelect(HttpServletRequest req, Model model){
		System.out.println("anBookingSelect()");
		
		//�븘留� member_code�� business_code, product_code�쓽 3議곌굔�쑝濡� 寃��깋�빐�빞 �븷 寃� 媛숈쓬.
		int member_code = Integer.parseInt(req.getParameter("member_code"));
		int business_code = Integer.parseInt(req.getParameter("business_code"));
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		
		command = new ABookingSelectCommand();
		command.execute(model);
		
		return "anBookingSelect";
	}
	
	//臾몄쓽 由ъ뒪�듃(�븘�씠�뵒�뿉 �뵲瑜� 寃��깋)
	@RequestMapping(value="/anBoardSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anBoardSelect(HttpServletRequest req, Model model){
		System.out.println("anBoardSelect()");
		
		command = new ABoardSelectCommand();
		command.execute(model);
		
		return "anBoardSelect";
	}
	
	
	
	
	// �씠 諛묒� 怨좊�쇳빐遊먯빞 �븷�벏...
	
	//移댄뀒怨좊━ 由ъ뒪�듃(�뼐�뒗 �뿬湲곗뿉 business �뿰�룞�빐�빞 �븿)
	
	
	
	
	//諛⑸Ц 由ъ뒪�듃(硫ㅻ쾭)
	
	//由щ럭 由ъ뒪�듃(硫ㅻ쾭)
	
	//由щ럭 由ъ뒪�듃(�뾽泥�)
	
}
