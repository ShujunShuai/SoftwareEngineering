package src.entity;


import java.util.Date;
/**
 * 
 * 
 * @author Vincent
 * 
 */
public class Book {
	private Integer bo_id;
	private String bo_name;
	private String bo_img;
	private String bo_press;
	private Date bo_date;
	private String bo_introduction;
	private String bo_author;

	public Date getbo_date() {
		return bo_date;
	}

	public void setbo_date(Date boDate) {
		bo_date = boDate;
	}

	public String getbo_press() {
		return bo_press;
	}

	public void setbo_press(String boPress) {
		bo_press = boPress;
	}

	public String getbo_img() {
		return bo_img;
	}

	public void setbo_img(String bImg) {
		bo_img = bImg;
	}

	public String getbo_introduction() {
		return bo_introduction;
	}

	public void setbo_introduction(String boIntroduction) {
		bo_introduction = boIntroduction;
	}
	


	
	public String getbo_author() {
		return bo_author;
	}

	public void setbo_author(String boAuthor) {
		bo_author = boAuthor;
	}
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer boId, String boName, Date boDate,String boAuthor,String boPress,String boIntroduction) {
		super();
		bo_id = boId;
		bo_name = boName;
		bo_date = boDate;
		bo_author =boAuthor;
		bo_press =boPress;
		bo_introduction =boIntroduction;

		
	}
	
	
	
	public Integer getbo_id() {
		return bo_id;
	}

	public void setbo_id(Integer bId) {
		bo_id = bId;
	}

	public String getbo_name() {
		return bo_name;
	}

	public void setbo_name(String boName) {
		bo_name = boName;
	}


}
