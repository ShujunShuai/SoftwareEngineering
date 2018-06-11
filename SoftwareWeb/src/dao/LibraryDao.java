package src.dao;

import java.sql.ResultSet;

import src.util.DBconn;
import src.entity.Library;

/**
 * 用户资料修改
 * 
 * 
 */
public class LibraryDao {
	// 获取用户信息
	public Library getLibraryById(final int li_id) {
		String strSQL = "select * from Library where li_id = ?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { li_id });
		Library library = new Library();
		try {
			while (rs.next()) {
				library.setLi_id(rs.getInt(1));
				library.setLi_password(rs.getString(4));
				library.setLi_name(rs.getString(5));
				library.setLi_img(rs.getString(6));
				library.setLi_date(rs.getString(7));
				library.setLi_addr(rs.getString(9));
				library.setLi_url(rs.getString(10));
			}
			return library;
		} catch (Exception e) {
			return null;
		} finally {
			DBconn.closeConn();
		}
	}

	// 保存用户修改完的资料
	public boolean updateUser(final  Library library, final int li_id) {
		String li_date = library.getLi_date();
		String li_name = library.getLi_name();
		String li_addr = library.getLi_addr();
		String li_url  = library.getLi_url();
	  
		String updateSql = "update Library set li_name=?, li_date=?, li_addr=?, li_url=? where li_id = ?";
		DBconn DBconn = new DBconn();
		int flag = DBconn.execOther(updateSql, new Object[] {
				li_name, li_date, li_addr, li_url, li_id });
		return flag > 0 ? true : false;
	}

	// 保存用户修改的密码
	public boolean updatePassword(final Library library, final int li_id) {

		String li_password = library.getLi_password();
		String updateSql = "update User set li_password=? where li_id = ?";
		DBconn DBconn = new DBconn();
		int flag = DBconn.execOther(updateSql,
				new Object[] { li_password, li_id });
		return flag > 0 ? true : false;
	}

	// 保存用户头像
	public void updateImg(final Library library) {
		String filetodb = library.getLi_img();
		String strSQL = "update User set li_img=? where li_id=?";
		DBconn DBconn = new DBconn();
		DBconn.execOther(strSQL, new Object[] { filetodb, library.getLi_id() });
		DBconn.closeConn();
	}

	// 用户修改密码时先检查原密码是否正确
	public boolean checkPassword(final String password, final int li_id) {
		String strSQL = "select count(*) from Library where li_password = ? and li_id=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn
				.execQuery(strSQL, new Object[] { password, li_id });
		try {
			rs.next();
			return rs.getInt(1) > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBconn.closeConn();
		}
	}
	// 帮助用户找回丢失的密码
	public Library findPassword(final String li_mail) {
		Library library = null;
		try {
			String strSQL = "select * from Library where li_mail=? ";
			DBconn DBconn = new DBconn();
			ResultSet rs = DBconn.execQuery(strSQL,
					new Object[] { li_mail});
			while (rs.next()) {
				library = new Library();
				library.setLi_id(rs.getInt(1));
				library.setLi_mail(rs.getString(10));
			}
			return library;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
