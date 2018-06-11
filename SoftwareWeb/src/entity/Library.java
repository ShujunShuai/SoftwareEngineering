package src.entity;



/**
 * 用户实体类，包装了用户的所有信息
 * @author Vincent
 *
 */
public class Library{
	private Integer Li_id;
	private String Li_password;
	private String Li_name;
	private String Li_img;
	private String Li_date;
	private String Li_addr;
	private String Li_mail;
	private String Li_url;


	
	public Library() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Library(Integer LiId, String LiPassword,
			 String LiImg, String uSex, String LiName, String LiDate,
			String LiAddr, String LiUrl,String LiMail) {
		super();
		Li_id = LiId;
		Li_password = LiPassword;
		Li_img = LiImg;
		Li_name = LiName;
		Li_date = LiDate;
		Li_addr = LiAddr;
		Li_url = LiUrl;
		Li_mail =LiMail;

	}
	public Integer getLi_id() {
		return Li_id;
	}
	public void setLi_id(Integer LiId) {
		Li_id = LiId;
	}
	
	public String getLi_password() {
		return Li_password;
	}
	public void setLi_password(String LiPassword) {
		Li_password = LiPassword;
	}
	public String getLi_mail() {
		return Li_mail;
	}
	public void setLi_mail(String LiMail) {
		Li_mail = LiMail;
	}
	public String getLi_img() {
		return Li_img;
	}
	public void setLi_img(String LiImg) {
		Li_img = LiImg;
	}
	
	public String getLi_name() {
		return Li_name;
	}
	public void setLi_name(String LiName) {
		Li_name = LiName;
	}
	public String getLi_date() {
		return Li_date;
	}
	public void setLi_date(String LiDate) {
		Li_date = LiDate;
	}
	public String getLi_addr() {
		return Li_addr;
	}
	public void setLi_addr(String LiAddr) {
		Li_addr = LiAddr;
	}
	public String getLi_url() {
		return Li_url;
	}
	public void setLi_url(String LiUrl) {
		Li_url = LiUrl;
	}

	
	
}

