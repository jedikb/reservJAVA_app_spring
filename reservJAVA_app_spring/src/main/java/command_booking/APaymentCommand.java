package command_booking;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BookingDAOlbs;

public class APaymentCommand implements ACommand {
    private static final String TAG = "APaymentCommand.";

	@Override
	public void execute(Model model) {
	    String TAG2 = TAG + "execute(): ";

		int booking_code = Integer.parseInt((String)model.asMap().get("booking_code"));
		System.out.println(TAG2 + "booking_code= " + booking_code + " 번 예약을 결재 요청하였습니다.");
		
		BookingDAOlbs adao = new BookingDAOlbs();
		int state = adao.anPayment(booking_code);

		System.out.println(TAG2 + "state= " + state);
		model.addAttribute("anPayment", String.valueOf(state)); 
	}//execute()

}//class APaymentCommand
