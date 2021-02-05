package command_member;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.MemberDAO;

public class AMemberReviewCommand implements ACommand {

	@Override
	public void execute(Model model) {
		int BOOKING_KIND = (int) model.asMap().get("BOOKING_KIND");
		int BOOKING_MEMBER_CODE = (int) model.asMap().get("BOOKING_MEMBER_CODE");
		int BOOKING_BUSINESS_CODE = (int) model.asMap().get("BOOKING_BUSINESS_CODE");
		int BOOKING_PRODUCT_CODE = (int) model.asMap().get("BOOKING_PRODUCT_CODE");
		int BOOKING_PRICE = (int) model.asMap().get("BOOKING_PRICE");
		int BOOKING_PRICE_DEPOSIT = (int) model.asMap().get("BOOKING_PRICE_DEPOSIT");
		int BOOKING_NUM = (int) model.asMap().get("BOOKING_NUM");
		String BOOKING_DATE = (String) model.asMap().get("BOOKING_DATE");
		String BOOKING_DATE_RESERVATION = (String) model.asMap().get("BOOKING_DATE_RESERVATION");
		String BOOKING_ETC = (String) model.asMap().get("BOOKING_ETC");
		int BOOKING_APPRAISAL_STAR = (int) model.asMap().get("BOOKING_APPRAISAL_STAR");
		String BOOKING_APPRAISAL = (String) model.asMap().get("BOOKING_APPRAISAL");

		MemberDAO adao = new MemberDAO();
		adao.memberReview(	BOOKING_KIND ,			
						BOOKING_MEMBER_CODE ,	
						BOOKING_BUSINESS_CODE	,
                        BOOKING_PRODUCT_CODE ,	
                        BOOKING_PRICE 			,
                        BOOKING_PRICE_DEPOSIT 	,
                        BOOKING_NUM,
                        BOOKING_DATE, 			
                        BOOKING_DATE_RESERVATION, 
                        BOOKING_ETC 			,	
                        BOOKING_APPRAISAL_STAR 	,
                        BOOKING_APPRAISAL);
	}
}
                        
                        
                        