package command_booking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ui.Model;

import command.ACommand;
import reservJAVA_app.dao.BookingDAO;
import reservJAVA_app.dto.BookingDTO;

public class BookingInsertCommand implements ACommand {

	@Override
	public void execute(Model model) {
		//객체 다시 담기
		String smember_code = (String) model.getAttribute("member_code");		
		String sbusiness_code = (String) model.getAttribute("business_code");		
		String sproduct_code = (String) model.getAttribute("product_code");		
		String sproduct_price = (String) model.getAttribute("product_price");		
		String sprice_deposit = (String) model.getAttribute("price_deposit");		
		String sbooking_num = (String) model.getAttribute("booking_num");		
		String booking_date_reservation = (String) model.getAttribute("booking_date_reservation");		
		
		int member_code = Integer.parseInt(smember_code);
		int business_code = Integer.parseInt(sbusiness_code);
		int product_code = Integer.parseInt(sproduct_code);
		int product_price = Integer.parseInt(sproduct_price);
		int price_deposit = Integer.parseInt(sprice_deposit);
		int booking_num = Integer.parseInt(sbooking_num);
		
		//SimpleDateFormat df = new SimpleDateFormat("yyyy년MM월dd일 hh:mm");
		//Date booking_date_reservation = df.parse(sbooking_date_reservation);
				
		
		
		BookingDTO dto = new BookingDTO(member_code, business_code, product_code, booking_num, price_deposit, booking_num,
									booking_date_reservation);
				
		
		//dao객체 생성 및 메소드 호출
		BookingDAO dao = new BookingDAO();
		dao.bookingInsert(dto);
		
		
	}

}
