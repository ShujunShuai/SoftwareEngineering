package src.dao;

import java.sql.ResultSet;
import java.util.Date;
import src.util.DBconn;
import src.entity.Book;
import src.entity.BookInLibrary;

/**
 * 用户资料修改
 
 * 
 */
public class BookDao {
	// 获取图书信息
	public Book getBookById(final int bo_id,int pageSize, int pageNo) {
		String strSQL = "select * from Book where bo_id = ? limit ?,?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { bo_id,pageSize*(pageNo-1), pageSize });
		Book book = new Book();
		try {
			while (rs.next()) {
				book.setbo_id(rs.getInt(1));
				book.setbo_name(rs.getString(10));
				book.setbo_date(rs.getDate(5));
				book.setbo_press(rs.getString(6));
				book.setbo_introduction(rs.getString(7));
				book.setbo_author(rs.getString(10));
			}
			return book;
		} catch (Exception e) {
			return null;
		} finally {
			DBconn.closeConn();
		}
	}

	// 保存图书修改完的资料
	public boolean updateUser(final Book book, final int bo_id) {
		Date bo_date = book.getbo_date();
		String bo_name = book.getbo_name();
		String bo_press = book.getbo_press();
		String bo_img = book.getbo_img();
		String bo_introduction = book.getbo_introduction();
		String bo_author = book.getbo_author();
		String updateSql = "update Book set bo_name=?,bo_press=?,bo_img=?,bo_date=?,bo_introduction=?,bo_author=? where bo_id = ?";
		DBconn DBconn = new DBconn();
		int flag = DBconn.execOther(updateSql, new Object[] { bo_name,bo_press,bo_img,bo_date,
				bo_introduction,bo_author,bo_id });
		return flag > 0 ? true : false;
	}

	

	// 保存图书封面
	public void updateImg(final Book book) {
		String filetodb = book.getbo_img();
		String strSQL = "update Book set bo_img=? where bo_id=?";
		DBconn DBconn = new DBconn();
		DBconn.execOther(strSQL, new Object[] { filetodb, book.getbo_id() });
		DBconn.closeConn();
	}

	public boolean addUser(Book book) {
		DBconn DBconn = new DBconn();
		Integer bo_id = book.getbo_id();
		Date bo_date = book.getbo_date();
		String bo_name = book.getbo_name();
		String bo_press = book.getbo_press();
		String bo_img = book.getbo_img();
		String bo_introduction = book.getbo_introduction();
		String bo_author = book.getbo_author();
		String sql = "insert into Book (bo_id, bo_name,bo_press,bo_img,bo_date,\r\n" + 
				"				bo_introduction,bo_author) values( ?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		result = DBconn.execOther(sql, new Object[] { bo_id,
				 bo_name,bo_press,bo_img,bo_date,bo_introduction,bo_author });
		DBconn.closeConn();
		return result > 0 ? true : false;
	}

}
