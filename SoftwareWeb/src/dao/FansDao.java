package src.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.util.DBconn;
import src.entity.User;
/**
 * 
 *
 */
public class FansDao {
	
	//根据用户id查找粉丝信息
	public List<User> getFansByUid(final int uid, int pageSize, int pageNo){
		List<User> fanslist = new ArrayList<User>();
		String strSQL = "select * from User where u_id =any " +
				"(select f_uid from friends where f_gid= " +
				"(select u_id from User where u_id=?)) or " +
				"u_id =any (select f_gid from friends where f_state=2 and " +
				"(f_uid= any (select u_id from User where u_id=?))) limit ?, ?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[]{uid, uid,pageSize*(pageNo-1), pageSize});
		try {
		
			while(rs.next()) {
				User fans = new User();
				fans.setU_addr(rs.getString("u_addr"));
				fans.setU_nick(rs.getString("u_nick"));
				fans.setU_img(rs.getString("u_img"));
				fans.setU_id(rs.getInt("u_id"));
				fanslist.add(fans);
			}
		
			
			return fanslist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			DBconn.closeConn();
		}
		
	}

}
