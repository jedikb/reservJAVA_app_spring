package reservJAVA_app.dto;

public class ReviewDTO {
	private int booking_code, booking_kind, booking_member_code, booking_business_code, booking_product_code;
	//Date는 자료를 넘기는 중에 오류가 나기 때문에 스트링으로 형식 변환을 하여 넘겨준다.
	private String booking_date_reservation;
	private int booking_appraisal_star;
	private String booking_appraisal;
	// 리스트 뿌리기 위해 부킹 테이블 외에 연동해서 불러올 변수
	//얘는 안드로이드에서 불러오면 될 듯
	private String business_name, business_addr;
	private int business_category_code;
	
	public ReviewDTO(int booking_code, int booking_kind, int booking_member_code, int booking_business_code, int booking_product_code, String booking_date_reservation, int booking_appraisal_star, String booking_appraisal, String business_name, int business_category_code, String business_addr) {
		this.booking_code = booking_code;
	    this.booking_kind = booking_kind;
	    this.booking_member_code = booking_member_code;
	    this.booking_business_code = booking_business_code;
	    this.booking_product_code = booking_product_code;
	    this.booking_date_reservation = booking_date_reservation;
	    this.booking_appraisal_star = booking_appraisal_star;
	    this.booking_appraisal = booking_appraisal;
	    this.business_name = business_name;
	    this.business_category_code = business_category_code;
	    this.business_addr = business_addr;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getBusiness_addr() {
		return business_addr;
	}

	public void setBusiness_addr(String business_addr) {
		this.business_addr = business_addr;
	}

	public int getBusiness_category_code() {
		return business_category_code;
	}

	public void setBusiness_category_code(int business_category_code) {
		this.business_category_code = business_category_code;
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

	public String getBooking_date_reservation() {
		return booking_date_reservation;
	}

	public void setBooking_date_reservation(String booking_date_reservation) {
		this.booking_date_reservation = booking_date_reservation;
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
