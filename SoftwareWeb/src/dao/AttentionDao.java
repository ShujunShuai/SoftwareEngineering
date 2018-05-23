package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBconn;
import entity.Artical;
import entity.User;

/**
 * @version1.0
 * @author Vincent
 * 和关注页相关的DAO类
 */
public class AttentionDao {
	 
	//添加关注
	public boolean addAttention(final int uid, final int fid) {
		DBconn dbConn = new DBconn();
		String sqlInsert = "insert into friends (f_uid, f_gid, f_state) values (?, ?, ?)";
		String sqlUpdate = "update friends set f_state=2 where f_uid=? and f_gid=?";
		int affected = 0;
		if (isAttention2(uid, fid)==true) {
			if (isAttention(uid, fid)==true) {
				affected = dbConn.execOther(sqlInsert, new Object[] {
						uid, fid, 1});
			} else
				affected = dbConn.execOther(sqlUpdate, new Object[] {
						fid, uid });
		}
		return affected > 0 ? true : false;
	}

	// 判断uid是否可以加fid为关注,只需判断fid是否已经加uid关注
	private static boolean isAttention(final int uid, final int fid) {
		DBconn dbConn = new DBconn();
		String sql = "select * from friends where f_uid=? and f_gid=?";
		ResultSet rs = dbConn.execQuery(sql, new Object[] { fid, uid });
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
			dbConn.closeConn();
		}
		return flag;
	}

	/////------------判断uid是否已经关注fid--------/////////////
	private static boolean isAttention2(final int uid, final int fid) {
		DBconn dbConn = new DBconn();
		String sql = "select * from friends where f_uid=? and f_gid=?";
		ResultSet rs = dbConn.execQuery(sql, new Object[] { uid, fid});
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
			dbConn.closeConn();
		}
		return flag;
	}

	// 统计关注人数
	public long accountAttention(final int id) {
		DBconn dbConn = new DBconn();
		String sql = "select count(*) from friends where (f_uid=?) or (f_gid=? and f_state=2)";
		//select count(*) from friends where (f_uid=(select u_id from userinfo where u_id=? ) and f_state=1) or (f_uid=(select u_id from userinfo where u_id=? ) and f_state=2)
		
		ResultSet rs = dbConn.execQuery(sql, new Object[] { id, id });
		try {
			rs.next();
			long num = Long.parseLong(rs.getString("count(*)"));
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			dbConn.closeConn();
		}
	}

	// 获取当前用户u_id 关注人信息
	public List<User> getAttention(final int id) {
		List<User> lstInfos = new ArrayList<User>();
		String sql = "SELECT * from userinfo where u_id= " +
				"any (select f_gid from friends where (f_uid=(select u_id from userinfo where u_id=?) and " +
				"f_state=1) or (f_uid=(select u_id from userinfo where u_id=?) and f_state=2))";
		DBconn dbConn = new DBconn();
		ResultSet rs = dbConn.execQuery(sql, new Object[] {id, id});
		try {
			while (rs.next()) {
				User userInfo = new User();
				userInfo.setU_id(rs.getInt("u_id"));
				userInfo.setU_img(rs.getString("u_img"));
				userInfo.setU_addr(rs.getString("u_addr"));
				userInfo.setU_nick(rs.getString("u_nick"));
				userInfo.setU_sign(rs.getString("u_sign"));
				userInfo.setU_url(rs.getString("u_url"));
				lstInfos.add(userInfo);
			}
			return lstInfos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			dbConn.closeConn();
		}
	}

	// 统计粉丝数
	public long accountFans(final int id) {
		DBconn dbConn = new DBconn();
		String sql = "select count(*) from friends where (f_uid=? and f_state=2) or f_gid=?";
		ResultSet rs = dbConn.execQuery(sql, new Object[] {id, id});
		try {
			rs.next();
			long num = Long.parseLong(rs.getString("count(*)"));
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			dbConn.closeConn();
		}
	}
	//获取当前用户信息
	public User getUserInfo(final int id){
		DBconn dbConn=new DBconn();
		String sql="select * from userinfo where u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id});
		User userInfo=new User();
		try {
			while (rs.next()) {
				userInfo.setU_id(rs.getInt("u_id"));
				userInfo.setU_addr(rs.getString("u_addr"));
				userInfo.setU_nick(rs.getString("u_nick"));
				userInfo.setU_img(rs.getString("u_img"));
				userInfo.setU_date(rs.getString("u_date"));
				userInfo.setU_sign(rs.getString("u_sign"));
				userInfo.setU_url(rs.getString("u_url"));
				userInfo.setU_sex(rs.getString("u_sex"));
				userInfo.setU_account(rs.getString("u_account"));
				userInfo.setU_password(rs.getString("u_password"));
			}
			return userInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return userInfo;
		}finally{
			dbConn.closeConn();
		}
	}
	
	//获取当前用户信息
	public User getUserInfo(final String account){
		DBconn dbConn=new DBconn();
		String sql="select * from userinfo where u_account=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{account});
		User userInfo=new User();
		try {
			while (rs.next()) {
				userInfo.setU_id(rs.getInt("u_id"));
				userInfo.setU_addr(rs.getString("u_addr"));
				userInfo.setU_nick(rs.getString("u_nick"));
				userInfo.setU_img(rs.getString("u_img"));
				userInfo.setU_date(rs.getString("u_date"));
				userInfo.setU_sign(rs.getString("u_sign"));
				userInfo.setU_url(rs.getString("u_url"));
				userInfo.setU_sex(rs.getString("u_sex"));
			}
			return userInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return userInfo;
		}finally{
			dbConn.closeConn();
		}
	}
	
	//获取用户信息
	public ArrayList<User> getUserInfoList(final int id){
		DBconn dbConn=new DBconn();
		ArrayList<User> userInfos=new ArrayList<User>();
		String sql="select * from userinfo where u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id});
		try {
			while (rs.next()) {
				User userInfo=new User();
				userInfo.setU_id(rs.getInt("u_id"));
				userInfo.setU_nick(rs.getString("u_nick"));
				userInfos.add(userInfo);
			}
			return userInfos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return userInfos;
		}finally{
			dbConn.closeConn();
		}
	}

	//获取当前用户己所关注人所发内容
	public ArrayList<Artical> getArtical(final int id, int pageSize, int pageNo){
		DBconn dbConn=new DBconn();
		ArrayList<Artical> lstArticals=new ArrayList<Artical>();
		String sql="select * from Artical where (u_id= any( select f_gid from friends where " +
				"(f_uid=? and f_state=1) or (f_uid=? and f_state=2))or u_id=?  or u_id= any " +
				"(select f_uid from friends where f_gid=? and f_state=2)) order by b_time desc limit ?, ?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id, id, id,id, pageSize*(pageNo-1), pageSize});
		try {
			while (rs.next()) {
				Artical artical=new Artical();
				artical.setB_content(rs.getString("b_content"));
				artical.setB_time(rs.getDate("b_time"));
				artical.setU_id(rs.getInt("u_id"));
				artical.setB_img(rs.getString("b_img"));
				artical.setB_degree(rs.getInt("b_degree"));
				artical.setB_id(rs.getInt("b_id"));
				artical.setB_date(rs.getTime("b_time"));
				lstArticals.add(artical);
			}
			return lstArticals;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			dbConn.closeConn();
		}
	}
	
	//统计当前用户微博数
	public long accountArticals(final int id){
		DBconn dbConn=new DBconn();
		String sql="select count(*) from Artical where u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id});
		try {
			long num=0;
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			dbConn.closeConn();
		}
	}
	//统计当前用户和其所关注人的微博总数
	public int allArticals(final int id){
		DBconn dbConn=new DBconn();
		String sql="SELECT count(*) FROM Artical where u_id= any (select f_gid from friends where (f_uid=? and f_state=1)" +
				" or (f_uid=? and f_state=2)) or u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id, id, id});
		try {
			int num=0;
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			dbConn.closeConn();
		}
	}
	
	//根据用户ID获得用户头像
	public String getPortrait(final int id){
		DBconn dbConn=new DBconn();
		String sql="select u_img from userinfo where u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id});
		String p=null;
		try {
			while (rs.next()) {
				p=rs.getString("u_img");
			}
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			dbConn.closeConn();
		}
	}
	//根据用户ID获得用户昵称
	public String getNick(final int id){
		DBconn dbConn=new DBconn();
		String sql="select u_nick from userinfo where u_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{id});
		String nick=null;
		try {
			while (rs.next()) {
				nick=rs.getString("u_nick");
			}
			return nick;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			dbConn.closeConn();
		}
	}
	//用户删除微博
	public boolean deleteArtical(final int uid, final int bid){
		DBconn dbConn=new DBconn();
		String sql="delete from Artical where u_id=? and b_id=?";
		int result=dbConn.execOther(sql, new Object[]{uid, bid});
		dbConn.closeConn();
		return result>0?true:false;
	}
/*	//保存用戶Cookie
	public void insertUserSession(UserInfo userInfo, String sessionid){
		DBConn dbConn=new DBConn();
		String sql="update userinfo set u_cookie=? where u_id=?";
		dbConn.execOther(sql, new Object[]{sessionid, userInfo.getU_id()});
		dbConn.closeConn();
	}
	//检查用户是否保存Cookie
	public boolean getAutoLoginState(String username, String sessionid){
		DBConn dbConn=new DBConn();
		String sql="select * from userinfo where u_account=? and u_cookie=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{username, sessionid});
		boolean flag=false;
		try {
			while (rs.next()) {
				flag=true;
			}
			return flag;
		} catch (SQLException e) {
			return flag;
		}finally{
			dbConn.closeConn();
		}
	}*/
}
