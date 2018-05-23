package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.DBconn;
import entity.User;

/**
 * 首页加载头像

 * 
 */
public class UploadFaceDao {
	// �õ���ע���û���ͷ��
	public List<User> uploadFace() {
		int num = getRandom();
		List<User> list = new ArrayList<User>();
		String sql = "select * from User limit ?,24";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(sql, new Object[] { num });
		try {
			while (rs.next()) {
				User user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setU_nick(rs.getString("u_nick"));
				user.setU_img(rs.getString("u_img"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		return list;
	}

	// ������֤
	public User check(final String account, final String password) {
		String strSQL = "SELECT * from User where u_account=? and u_password=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { account,
				password });
		User user = new User();
		try {
			while (rs.next()) {
				user.setU_id(rs.getInt("u_id"));
				user.setU_account(rs.getString("u_account"));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			DBconn.closeConn();
		}
	}
	
	// ������֤
	public boolean check(User User) {
		String strSQL = "SELECT * from User where u_account=? and u_password=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { User.getU_account(),
				User.getU_password() });
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

	// ���������
	private static int getRandom() {
		Random random = new Random();
		int result = 0;
		for (int i = 0; i < 10; i++) {
			result = (Math.abs(random.nextInt()) % 5);
		}
		return result;
	}

}
