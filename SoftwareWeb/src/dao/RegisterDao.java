package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBconn;
import entity.User;

/**
 *登陆
 * 
 * 
 */
public class RegisterDao {

	// ����û��ǳ��Ƿ����
	public boolean checkNick(String nick) {
		DBconn DBconn = new DBconn();
		String sql = "select u_nick from User where u_nick =?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { nick });
		boolean flag = true;
		try {
			while (rs.next()) {
				rs.getString("u_nick");
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

	// ��������Ƿ����
	public boolean checkMail(String mail) {
		DBconn DBconn = new DBconn();
		String sql = "select u_mail from User where u_mail =?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { mail });
		boolean flag = true;
		try {
			while (rs.next()) {
				rs.getString("u_mail");
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

	// ��֤�ɹ���������û�
	public boolean addUser(User User) {
		DBconn DBconn = new DBconn();
		String sql = "insert into User (u_account, u_mail, u_password, u_nick, u_sex, u_addr, u_img) values(?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		result = DBconn.execOther(sql, new Object[] { User.getU_mail(),
				User.getU_mail(), User.getU_password(),
				User.getU_nick(), User.getU_sex(),
				User.getU_addr(), User.getU_img() });
		DBconn.closeConn();
		return result > 0 ? true : false;
	}

	// ͨ���û��ǳƻ�ȡ�û���Ϣ
	public User getInfoByAccount(String account) {
		DBconn DBconn = new DBconn();
		String sql = "select * from User where u_account=?";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { account });
		User User = new User();
		try {
			while (rs.next()) {
				User.setU_id(rs.getInt("u_id"));
				User.setU_nick(rs.getString("u_nick"));
				User.setU_img(rs.getString("u_img"));
				User.setU_sex(rs.getString("u_sex"));
				User.setU_name(rs.getString("u_name"));
				User.setU_addr(rs.getString("u_addr"));
				User.setU_mail(rs.getString("u_mail"));
				User.setU_qq(rs.getString("u_qq"));
				User.setU_msn(rs.getString("u_msn"));
				User.setU_sign(rs.getString("u_sign"));
				User.setU_url(rs.getString("u_url"));
				User.setU_account(rs.getString("u_account"));
			}
			return User;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
