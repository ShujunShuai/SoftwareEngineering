package src.entity;

import java.util.Date;

/**
 * 
 * 
 * 
 */
public class BookInLibrary {
	private Integer bo_id;
	private Integer  bo_liId;
	private String  bo_status;


	public Integer getbo_liId() {
		return bo_liId;
	}

	public void setbo_liId(Integer boLibrary) {
		bo_liId=boLibrary;
	}

	public String getbo_status() {
		return bo_status;
	}

	public void setbo_status(String boStatus) {
		bo_status=boStatus;
	}

	
	public BookInLibrary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookInLibrary(Integer boId, Integer boLibrary,String boStatus) {
		super();
		bo_id = boId;
		bo_liId =boLibrary;
		bo_status =boStatus;

		
	}
	
	
	
	public Integer getbo_id() {
		return bo_id;
	}

	public void setbo_id(Integer bId) {
		bo_id = bId;
	}



}
