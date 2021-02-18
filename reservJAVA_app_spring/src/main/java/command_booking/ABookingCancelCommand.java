package command_booking;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BookingDAO_lbs;

public class ABookingCancelCommand implements ACommand {
    private static final String TAG = "ABookingCancelCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";

		int booking_code = Integer.parseInt((String)model.asMap().get("booking_code"));
		System.out.println(TAG2 + "booking_code= " + booking_code + " 번 예약을 취소 요청하였습니다.");
		
		BookingDAO_lbs adao = new BookingDAO_lbs();
		int state = adao.anBookingCancel(booking_code);

		System.out.println(TAG2 + "state= " + state);
		model.addAttribute("anBookingCancel", String.valueOf(state)); 
	}//execute()

}//class ABookingCancelCommand
