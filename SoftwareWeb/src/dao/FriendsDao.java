package src.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.util.DBconn;
import src.entity.User;

public class FriendsDao {
	
	//���ݵ�ǰ�û�id��ȡ�������Ϣ�������з�ҳ
	public List<User> getFriendsByUid(final int uid, int pageSize, int pageNo){
		List<User> friendlist = new ArrayList<User>();
		String strSQL = "select * from User where u_id =any " +
				"(select f_gid from friends where f_state=2 and (f_uid= any " +
				"(select u_id from User where u_id=?))) or u_id =any " +
				"(select f_uid from friends where f_state=2 and (f_gid= any " +
				"(select u_id from User where u_id=?))) limit ?, ?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[]{uid, uid, pageSize*(pageNo-1), pageSize});
		try {
		
			while(rs.next()) {
				User friends = new User();
				friends.setU_addr(rs.getString("u_addr"));
				friends.setU_nick(rs.getString("u_nick"));
				friends.setU_img(rs.getString("u_img"));
				friends.setU_id(rs.getInt("u_id"));
				friendlist.add(friends);
			}
			return friendlist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			DBconn.closeConn();
		}
	}

	//ͳ�Ƶ�ǰ�û���������
	public int accountFriends(final int id){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from friends where (f_uid=? and f_state=2) or (f_gid=? and f_state=2)";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{id, id});
		int num=0;
		try {
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			DBconn.closeConn();
		}
	}
}
