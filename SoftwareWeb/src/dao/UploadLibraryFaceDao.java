package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.util.DBconn;
import src.entity.Library;

/**
 * 首页加载头像

 * 
 */
public class UploadLibraryFaceDao {
	// 得到已注册图书馆的头像
	public List<Library> uploadFace() {
		int num = getRandom();
		List<Library> list = new ArrayList<Library>();
		String sql = "select * from Library limit ?,24";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(sql, new Object[] { num });
		try {
			while (rs.next()) {
				Library library = new Library();
				library.setLi_id(rs.getInt("li_id"));
				library.setLi_name(rs.getString("li_nick"));
				library.setLi_img(rs.getString("li_img"));
				list.add(library);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		return list;
	}

	// 登入验证֤
	public Library check(final String name, final String password) {
		String strSQL = "SELECT * from Library where li_name=? and li_password=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { name,
				password });
		Library library = new Library();
		try {
			while (rs.next()) {
				library.setLi_id(rs.getInt("u_id"));
				library.setLi_name(rs.getString("li_name"));
			}
			return library;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			DBconn.closeConn();
		}
	}
	
	//登入验证 ֤
	public boolean check(Library library) {
		String strSQL = "SELECT * from Library where li_name=? and li_password=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { library.getLi_name(),
				library.getLi_password() });
		boolean flag=false;
		try {
			while (rs.next()) {
				flag=true;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return flag;
		} finally {
			DBconn.closeConn();
		}
	}

	// 生成随机数
	private static int getRandom() {
		Random random = new Random();
		int result = 0;
		for (int i = 0; i < 10; i++) {
			result = (Math.abs(random.nextInt()) % 5);
		}
		return result;
	}

}
