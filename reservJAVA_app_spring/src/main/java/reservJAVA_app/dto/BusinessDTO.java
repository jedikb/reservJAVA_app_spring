package reservJAVA_app.dto;

public class BusinessDTO {
	private int business_code;
	private String business_name;
	private int business_member_code, business_category_parent_code, business_category_code;
	private  String business_addr, business_tel, business_image, business_info, business_hashtag;
	private int business_star_avg;
	
	public BusinessDTO() {}
	
	public BusinessDTO(int business_code, String business_name, int business_member_code, int business_category_parent_code,
			int business_category_code, String business_addr, String business_tel, String business_image,
			String business_info, int business_star_avg, String business_hashtag) {
		super();
		this.business_code = business_code;
		this.business_name = business_name;
		this.business_member_code = business_member_code;
		this.business_category_parent_code = business_category_parent_code;
		this.business_category_code = business_category_code;
		this.business_addr = business_addr;
		this.business_tel = business_tel;
		this.business_image = business_image;
		this.business_info = business_info;
		this.business_star_avg = business_star_avg;
		this.business_hashtag = business_hashtag;
	}

	public int getBusiness_code() {
		return business_code;
	}

	public void setBusiness_code(int business_code) {
		this.business_code = business_code;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public int getBusiness_member_code() {
		return business_member_code;
	}

	public void setBusiness_member_code(int business_member_code) {
		this.business_member_code = business_member_code;
	}

	public int business_category_parent_code() {
		return business_category_parent_code;
	}

	public void business_category_parent_code(int business_category_parent_code) {
		this.business_category_parent_code = business_category_parent_code;
	}

	public int business_category_code() {
		return business_category_code;
	}

	public void business_category_code(int business_category_code) {
		this.business_category_code = business_category_code;
	}

	public String getBusiness_addr() {
		return business_addr;
	}

	public void setBusiness_addr(String business_addr) {
		this.business_addr = business_addr;
	}

	public String getBusiness_tel() {
		return business_tel;
	}

	public void setBusiness_tel(String business_tel) {
		this.business_tel = business_tel;
	}

	public String getBusiness_image() {
		return business_image;
	}

	public void setBusiness_image(String business_image) {
		this.business_image = business_image;
	}

	public String getBusiness_info() {
		return business_info;
	}

	public void setBusiness_info(String business_info) {
		this.business_info = business_info;
	}

	public int getBusiness_star_avg() {
		return business_star_avg;
	}

	public void setBusiness_star_avg(int business_star_avg) {
		this.business_star_avg = business_star_avg;
	}
	
}
