package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBconn;
import entity.User;
/**
 * 
 * @author Vincent
 *	��ȡ��ע�������Ϣ
 */
public class FollowingDao {
	
	//��ǰ�û�����ע�˵���Ϣ
	public List<User> getFollowingByUid(final int uid , int pageSize, int pageNo){
		List<User> followinglist = new ArrayList<User>();
		String strSQL = "select * from User where u_id =any " +
				"(select f_gid from friends where f_uid= " +
				"(select u_id from User where u_id=?)) " +
				"or u_id =any (select f_uid from friends where f_state=2 and " +
				"(f_gid= any (select u_id from User where u_id=?))) limit ?, ?";
		//���ݿ�����
		DBconn dbConn = new DBconn();
		ResultSet rs = dbConn.execQuery(strSQL, new Object[]{uid, uid,pageSize*(pageNo-1), pageSize});
		try {
			//���ؽ����
			while(rs.next()) {
				User following = new User();
				following.setU_id(rs.getInt("u_id"));
				following.setU_addr(rs.getString("u_addr"));
				following.setU_nick(rs.getString("u_nick"));
				following.setU_img(rs.getString("u_img"));
				followinglist.add(following);
			}
			return followinglist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			dbConn.closeConn();
		}
		
	}
	

}
