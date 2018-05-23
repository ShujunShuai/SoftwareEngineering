package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBconn;
/**
 * 
 */
public class CancleFollowingDao {
	//取消关注，uid是当前用户，fid是被关注对象
	public boolean cancelFollowing(final int uid,final int fid){
		DBconn DBconn = new DBconn();
		String sql1 = "delete from friends where f_uid=? and f_gid=?";
		String sql2 = "update friends set f_state=1,f_uid=?,f_gid=? where f_uid=? and f_gid=?";
		String sql3 = "update friends set f_state=1 where f_uid=? and f_gid=?";
		int affected = 0;		
		if (isAttention(uid, fid)==false) {
				affected = DBconn.execOther(sql3, new Object[] {fid, uid});
			} else{
				if(isAttention2(uid, fid)==false){
				affected = DBconn.execOther(sql1, new Object[]{uid,fid});
				}
				else{
					affected = DBconn.execOther(sql2, new Object[]{fid,uid,uid,fid});	
				}
		}
		return affected > 0 ? true : false;
	}
	

	//判断fid是否已经加uid关注    并且为好友
	private static boolean isAttention(final int uid, final int fid) {
		DBconn DBconn = new DBconn();
		String sql = "select * from friends where f_uid=? and f_gid=? and f_state=2";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { fid,uid,});
		boolean flag = false;
		try {
			if (rs.next()) {
				flag = false;
			} else
				flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		return flag;
	}
	
/////------------判断uid是否已经关注fid--------/////////////
	private static boolean isAttention2(final int uid, final int fid) {
		DBconn DBconn = new DBconn();
		String sql = "select * from friends where f_uid=? and f_gid=? and f_state=1";
		ResultSet rs = DBconn.execQuery(sql, new Object[] { uid, fid});
		boolean flag = false;
		try {
			if (rs.next()) {
				flag = false;//已关注
			} else
				flag = true;//未关注
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		return flag;
	}

}

