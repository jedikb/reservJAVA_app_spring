package command_booking;

import java.util.ArrayList;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BookingDAO;
import reservJAVA_app.dto.BookingDTO;

public class ABookingSelectCommand implements ACommand{

	@Override
	public void execute(Model model) {
		BookingDAO bDAO = new BookingDAO();
		ArrayList<BookingDTO> bDTO = bDAO.bookingSelect();
		
		model.addAttribute("anBookingSelect", bDTO);		
	}

}
