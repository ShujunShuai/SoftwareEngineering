package src.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import src.util.DBconn;
import src.entity.User;
/**
 *  获取感兴趣的人的用户信息
 
 */
public class InterestDao {
	
	//�����û�id��ȡ����Ȥ�˵���Ϣ
	public List<User> getInterestByUid(final int uid){
		List<User> interestlist = new ArrayList<User>();
		String strSQL = "SELECT * FROM User where u_id!=? and u_id not in" +
				" (select f_gid from friends where f_state=1 and f_uid=?) and u_id not in " +
				"(select f_gid from friends where f_state=2 and f_uid=?) and u_id not in " +
				"(select f_uid from friends where f_state=2 and f_gid=?)";
		DBconn dbConn = new DBconn();
		ResultSet rs = dbConn.execQuery(strSQL, new Object[]{uid,uid, uid, uid});
		try {
			while(rs.next()) {
				User interest = new User();
				interest.setU_addr(rs.getString("u_addr"));
				interest.setU_nick(rs.getString("u_nick"));
				interest.setU_img(rs.getString("u_img"));
				interest.setU_date(rs.getString("u_date"));
				interestlist.add(interest);
			}
		   	return interestlist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			dbConn.closeConn();
		}
	}
}
