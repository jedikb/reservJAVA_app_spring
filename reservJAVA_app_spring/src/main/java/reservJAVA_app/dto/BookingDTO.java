package reservJAVA_app.dto;

import java.sql.Date;

public class BookingDTO {
/*
booking_code             not null number         
booking_kind                      number         
booking_member_code               number         
booking_business_code             number         
booking_product_code              number         
booking_price                     number         
booking_price_deposit             number         
booking_num                       number         
booking_date                      date           
booking_date_reservation          varchar2(100)           
booking_etc                       varchar2(1000) 
booking_appraisal_star            number         
booking_appraisal                 varchar2(2000) 
*/
	private int booking_code, booking_kind, booking_member_code, booking_business_code,
		booking_product_code, booking_price, booking_price_deposit, booking_num;
	private String booking_date;
	private String booking_date_reservation, booking_etc;
	private int booking_appraisal_star;
	private String booking_appraisal;
	
	//조회(select) 쿼리를 위한 맴버변수
	private String booking_member_name, booking_business_name, booking_product_name;

	public BookingDTO() {}
	
	public BookingDTO(	int		booking_code, 
						int		booking_kind, 
						int		booking_member_code, 
						int		booking_business_code,
						int		booking_product_code, 
						int		booking_price, 
						int		booking_price_deposit, 
						int		booking_num, 
						Date	booking_date,
						String	booking_date_reservation, 
						String	booking_etc, 
						int		booking_appraisal_star, 
						String	booking_appraisal) {
		super();
		this.booking_code				= booking_code;
		this.booking_kind				= booking_kind;
		this.booking_member_code		= booking_member_code;
		this.booking_business_code		= booking_business_code;
		this.booking_product_code		= booking_product_code;
		this.booking_price				= booking_price;
		this.booking_price_deposit		= booking_price_deposit;
		this.booking_num				= booking_num;
		this.booking_date				= booking_date.toString();
		this.booking_date_reservation	= booking_date_reservation;
		this.booking_etc				= booking_etc;
		this.booking_appraisal_star		= booking_appraisal_star;
		this.booking_appraisal			= booking_appraisal;
	}
	
	
	//insert용 생성자
	public BookingDTO(	int		booking_member_code, 
						int		booking_business_code, 
						int		booking_product_code, 
						int		booking_price,
						int		booking_price_deposit, 
						int		booking_num, 
						String	booking_date_reservation) {
		super();
		this.booking_member_code		= booking_member_code;
		this.booking_business_code		= booking_business_code;
		this.booking_product_code		= booking_product_code;
		this.booking_price				= booking_price;
		this.booking_price_deposit		= booking_price_deposit;
		this.booking_num				= booking_num;
		this.booking_date_reservation	= booking_date_reservation;
	}

	public String getBooking_member_name() {
		return booking_member_name;
	}

	public void setBooking_member_name(String booking_member_name) {
		this.booking_member_name = booking_member_name;
	}

	public String getBooking_business_name() {
		return booking_business_name;
	}

	public void setBooking_business_name(String booking_business_name) {
		this.booking_business_name = booking_business_name;
	}

	public String getBooking_product_name() {
		return booking_product_name;
	}

	public void setBooking_product_name(String booking_product_name) {
		this.booking_product_name = booking_product_name;
	}

	public int getBooking_code() {
		return booking_code;
	}


	public void setBooking_code(int booking_code) {
		this.booking_code = booking_code;
	}


	public int getBooking_kind() {
		return booking_kind;
	}


	public void setBooking_kind(int booking_kind) {
		this.booking_kind = booking_kind;
	}


	public int getBooking_member_code() {
		return booking_member_code;
	}


	public void setBooking_member_code(int booking_member_code) {
		this.booking_member_code = booking_member_code;
	}


	public int getBooking_business_code() {
		return booking_business_code;
	}


	public void setBooking_business_code(int booking_business_code) {
		this.booking_business_code = booking_business_code;
	}


	public int getBooking_product_code() {
		return booking_product_code;
	}


	public void setBooking_product_code(int booking_product_code) {
		this.booking_product_code = booking_product_code;
	}


	public int getBooking_price() {
		return booking_price;
	}


	public void setBooking_price(int booking_price) {
		this.booking_price = booking_price;
	}


	public int getBooking_price_deposit() {
		return booking_price_deposit;
	}


	public void setBooking_price_deposit(int booking_price_deposit) {
		this.booking_price_deposit = booking_price_deposit;
	}


	public int getBooking_num() {
		return booking_num;
	}


	public void setBooking_num(int booking_num) {
		this.booking_num = booking_num;
	}


	public String getBooking_date() {
		return booking_date;
	}


	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}


	public String getBooking_date_reservation() {
		return booking_date_reservation;
	}


	public void setBooking_date_reservation(String booking_date_reservation) {
		this.booking_date_reservation = booking_date_reservation;
	}


	public String getBooking_etc() {
		return booking_etc;
	}


	public void setBooking_etc(String booking_etc) {
		this.booking_etc = booking_etc;
	}


	public int getBooking_appraisal_star() {
		return booking_appraisal_star;
	}


	public void setBooking_appraisal_star(int booking_appraisal_star) {
		this.booking_appraisal_star = booking_appraisal_star;
	}


	public String getBooking_appraisal() {
		return booking_appraisal;
	}


	public void setBooking_appraisal(String booking_appraisal) {
		this.booking_appraisal = booking_appraisal;
	}
	
}
