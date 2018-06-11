package src.dao;

import java.sql.ResultSet;

import src.util.DBconn;
import src.entity.User;

/**
 * 用户资料修改
 * 
 * @author Vincent
 * 
 */
public class UserDao {
	// 获取用户信息
	public User getUserById(final int u_id) {
		String strSQL = "select * from User where u_id = ?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { u_id });
		User User = new User();
		try {
			while (rs.next()) {
				User.setU_id(rs.getInt(1));
				User.setU_nick(rs.getString(4));
				User.setU_img(rs.getString(5));
				User.setU_sex(rs.getString(6));
				User.setU_name(rs.getString(7));
				User.setU_date(rs.getString(8));
				User.setU_addr(rs.getString(9));
				User.setU_mail(rs.getString(10));
				User.setU_qq(rs.getString(11));
				User.setU_msn(rs.getString(12));
				User.setU_sign(rs.getString(13));
				User.setU_question(rs.getString(15));
			}
			return User;
		} catch (Exception e) {
			return null;
		} finally {
			DBconn.closeConn();
		}
	}

	// 保存用户修改完的资料
	public boolean updateUser(final User User, final int u_id) {
		String u_date = User.getU_date();
		String u_mail = User.getU_mail();
		String u_nick = User.getU_nick();
		String u_name = User.getU_name();
		String u_sex = User.getU_sex();
		String u_addr = User.getU_addr();
		String u_qq = User.getU_qq();
		String u_msn = User.getU_msn();
		String u_sign = User.getU_sign();
		String updateSql = "update User set u_nick=?,u_sex=?,u_name=?,u_date=?,u_addr=?,u_qq=?,u_msn=?,u_sign=?where u_id = ?";
		DBconn DBconn = new DBconn();
		int flag = DBconn.execOther(updateSql, new Object[] { u_nick, u_sex,
				u_name, u_date, u_addr, u_qq, u_msn, u_sign, u_id });
		return flag > 0 ? true : false;
	}

	// 保存用户修改的密码
	public boolean updatePassword(final User User, final int u_id) {

		String u_password = User.getU_password();
		String updateSql = "update User set u_password=? where u_id = ?";
		DBconn DBconn = new DBconn();
		int flag = DBconn.execOther(updateSql,
				new Object[] { u_password, u_id });
		return flag > 0 ? true : false;
	}

	// 保存用户头像
	public void updateImg(final User user) {
		String filetodb = user.getU_img();
		String strSQL = "update User set u_img=? where u_id=?";
		DBconn DBconn = new DBconn();
		DBconn.execOther(strSQL, new Object[] { filetodb, user.getU_id() });
		DBconn.closeConn();
	}

	// 用户修改密码时先检查原密码是否正确
	public boolean checkPassword(final String password, final int u_id) {
		String strSQL = "select count(*) from User where u_password = ? and u_id=?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn
				.execQuery(strSQL, new Object[] { password, u_id });
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
	public User findPassword(final String u_mail, final String u_question) {
		User User = null;
		try {
			String strSQL = "select * from User where u_mail=? and u_question=?";
			DBconn DBconn = new DBconn();
			ResultSet rs = DBconn.execQuery(strSQL,
					new Object[] { u_mail, u_question });
			while (rs.next()) {
				User = new User();
				User.setU_id(rs.getInt(1));
				User.setU_account(rs.getString(2));
				User.setU_password(rs.getString(3));
				User.setU_mail(rs.getString(10));
			}
			return User;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
