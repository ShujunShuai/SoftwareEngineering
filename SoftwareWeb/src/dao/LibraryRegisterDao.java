package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.util.DBconn;
import src.entity.Library;

/**
 *登陆
 * 
 * 
 */
public class LibraryRegisterDao {

	// 图书馆名称是否可用
	public boolean checkNick(String name) {
		DBconn DBconn = new DBconn();
		String sql = "select li_name from Library where li_name =?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { name });
		boolean flag = true;
		try {
			while (rs.next()) {
				rs.getString("li_name");
				flag = false;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return flag;
		} finally {
			DBconn.closeConn();
		}
	}

	// 检查邮箱是否可用
	public boolean checkMail(String mail) {
		DBconn DBconn = new DBconn();
		String sql = "select li_mail from Library where li_mail =?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { mail });
		boolean flag = true;
		try {
			while (rs.next()) {
				rs.getString("li_mail");
				flag = false;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return flag;
		} finally {
			DBconn.closeConn();
		}
	}

	// 添加新图书馆
	public boolean addUser(Library library) {
		DBconn DBconn = new DBconn();
		String sql = "insert into Library ( li_mail, li_password, li_name, li_addr, li_img, li_url) values(?, ?, ?, ?, ?, ?)";
		int result = 0;
		result = DBconn.execOther(sql, new Object[] { library.getLi_mail(),
				library.getLi_password(), library.getLi_name(),
				library.getLi_addr(), library.getLi_img(),
				library.getLi_url()});
		DBconn.closeConn();
		return result > 0 ? true : false;
	}

	// ͨ根据图书馆名字获取信息
	public Library getInfoByName(String name) {
		DBconn DBconn = new DBconn();
		String sql = "select * from Library where li_name=?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { name });
		Library library = new Library();
		try {
			while (rs.next()) {
				library.setLi_id(rs.getInt("li_id"));
				library.setLi_img(rs.getString("li_img"));
				library.setLi_addr(rs.getString("li_addr"));
				library.setLi_mail(rs.getString("li_mail"));
				library.setLi_url(rs.getString("li_url"));
				library.setLi_date(rs.getString("li_date"));
			}
			return library;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
