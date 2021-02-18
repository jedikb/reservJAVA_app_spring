package command_booking;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BookingDAO_lbs;
import reservJAVA_app.dto.BookingDTO;

public class ABookingViewCommand implements ACommand {
    private static final String TAG = "ABookingViewCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";

		int booking_code = Integer.parseInt((String)model.asMap().get("booking_code"));
		System.out.println(TAG2 + "booking_code= " + booking_code + " 번 예약확인(상세보기)을 요청하였습니다.");
		
		BookingDAO_lbs adao = new BookingDAO_lbs();
		BookingDTO adto = adao.anBookingView(booking_code);
		model.addAttribute("anBookingView", adto); 
	}//execute()

}//class ABookingViewCommand
