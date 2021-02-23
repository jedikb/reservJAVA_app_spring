package reservJAVA_app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.ACommand;
import command_booking.ABookingCancelCommand;
import command_booking.ABookingViewCommand;
import command_booking.APaymentCommand;
import command_member.AMemberCancelCommand;
import command_member.AMemberDeleteInfoCommand;
import reservJAVA_app.dao.MemberDAO_lbs;

@Controller
public class AController_lbs {
    private static final String TAG = "AController_lbs.";

	ACommand command;
	MemberDAO_lbs dao;
	
	//예약확인(상세보기)
	@RequestMapping(value="/anBookingView", method = {RequestMethod.GET, RequestMethod.POST})
	public String anBookingView(HttpServletRequest req, Model model){
	    String TAG2 = TAG + "anBookingView(): ";
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 예약확인(상세보기) 처리 시작 -------" );

		model.addAttribute("booking_code", req.getParameter("booking_code") );
		command = new ABookingViewCommand();
		command.execute(model);
		
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 예약확인(상세보기) 처리 끝 -------\n" );
		return "anBookingView";
	}//anMemberCancel()

	//예약-취소(삭제)처리
	@RequestMapping(value="/anBookingCancel", method = {RequestMethod.GET, RequestMethod.POST})
	public String anBookingCancel(HttpServletRequest req, Model model){
		String TAG2 = TAG + "anBookingCancel(): ";
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 예약삭제 처리 시작 -------" );
		
		model.addAttribute("booking_code", req.getParameter("booking_code") );
		command = new ABookingCancelCommand();
		command.execute(model);
		
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 예약삭제 처리 끝 -------\n" );
		return "anBookingCancel";
	}//anBookingCancel()
	
	//예약-결재처리
	@RequestMapping(value="/anPayment", method = {RequestMethod.GET, RequestMethod.POST})
	public String anPayment(HttpServletRequest req, Model model){
		String TAG2 = TAG + "anPayment(): ";
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 결재 처리 시작 -------" );
		
		model.addAttribute("booking_code", req.getParameter("booking_code") );
		command = new APaymentCommand();
		command.execute(model);
		
		System.out.println(TAG2 + (String)req.getParameter("booking_code") + "번(booking_code) 결재 처리 끝 -------\n" );
		return "anPayment";
	}//anPayment()
	
	//회원 탈퇴
	@RequestMapping(value="/anMemberCancel", method = {RequestMethod.GET, RequestMethod.POST})
	public String anMemberCancel(HttpServletRequest req, Model model){
		String TAG2 = TAG + "anMemberCancel(): ";
		System.out.println(TAG2 + (String)req.getParameter("member_code") + "번(member_code) 회원탈퇴 처리 시작 -------" );
		
		model.addAttribute("member_code", req.getParameter("member_code") );		
		command = new AMemberCancelCommand();
		command.execute(model);
		
		System.out.println(TAG2 + (String)req.getParameter("member_code") + "번(member_code) 회원탈퇴 처리 끝 -------\n" );
		return "anMemberCancel";
	}//anMemberCancel()
	
	//회원-개인정보삭제 처리
	@RequestMapping(value="/anMemberDeleteInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String anMemberDeleteInfo(HttpServletRequest req, Model model){
	    String TAG2 = TAG + "anMemberDeleteInfo(): ";
		System.out.println(TAG2 + "개인정보 삭제 처리 시작 -------" );
		
		command = new AMemberDeleteInfoCommand();
		command.execute(model);

		System.out.println(TAG2 + "개인정보 삭제 처리 끝 -------\n" );
		return "anMemberDeleteInfo";
	}//anMemberDeleteInfo()
	
}//class AControllerlbs
