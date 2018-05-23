package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBconn;
import entity.Artical;
import entity.Collect;
import entity.User;

public class SearchDao {
	//�����ҵ���ҳ
	public  List<Artical> searchHome(final  String b_content ,final int u_id,  int pageSize, int pageNo){		 
		List<Artical> lstArticals=new ArrayList<Artical>();
		DBconn DBconn=new DBconn();		
		String sql="select * from Artical where b_content like ? and (u_id= any( select f_gid from friends where (f_uid=? and f_state=1) or (f_uid=? and f_state=2))or u_id=?  or u_id= any(select f_uid from friends where f_gid=? and f_state=2)) order by b_time desc limit ?, ?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+b_content+"%",u_id ,u_id,u_id,u_id,pageSize*(pageNo-1), pageSize});
		try {
			while (rs.next()) {
				Artical Artical=new Artical();
				Artical.setB_content(rs.getString("b_content"));
				Artical.setB_time(rs.getDate("b_time"));
				Artical.setB_date(rs.getTime("b_time"));
				Artical.setU_id(rs.getInt("u_id"));
				Artical.setB_degree(rs.getInt("b_degree"));
				Artical.setB_id(rs.getInt("b_id"));
				Artical.setB_img(rs.getString("b_img"));
				lstArticals.add(Artical);
			}
			return lstArticals;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
	//�����ҵ�΢��
	public List<Artical> searchProfile(final  String b_content ,final int u_id,  int pageSize, int pageNo){		 
		List<Artical> lstArticals=new ArrayList<Artical>();
		DBconn DBconn=new DBconn();		
		String sql="select * from Artical where b_content like ? and u_id=? order by b_time desc limit ?, ?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+b_content+"%",u_id,pageSize*(pageNo-1), pageSize});
		try {
			while (rs.next()) {
				Artical Artical=new Artical();
				Artical.setB_content(rs.getString("b_content"));
				Artical.setB_time(rs.getDate("b_time"));
				Artical.setB_date(rs.getTime("b_time"));
				Artical.setU_id(rs.getInt("u_id"));
				Artical.setB_id(rs.getInt("b_id"));
				Artical.setB_img(rs.getString("b_img"));
				lstArticals.add(Artical);
			}
			return lstArticals;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
	//�����ҵĺ���
	public List<User> searchFriend(final  String u_nick,final int u_id){		 
		List<User> lstFriend=new ArrayList<User>();
		DBconn DBconn=new DBconn();		
		String sql="select * from User where u_nick like ? and (u_id=any(select f_gid from friends where f_uid=? and f_state=2) or u_id=any(select f_uid from friends where f_gid=? and f_state=2))";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+u_nick+"%",u_id,u_id});
		try {
			while (rs.next()) {
				User User=new User();
				User.setU_addr(rs.getString("u_addr"));
				User.setU_img(rs.getString("u_img"));
				User.setU_nick(rs.getString("u_nick"));
				 
				lstFriend.add(User);
			}
			return lstFriend;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
	//������ע
	public List<User> searchFollowing(final  String u_nick,final int u_id){		 
		List<User> lstFollowing=new ArrayList<User>();
		DBconn DBconn=new DBconn();		
		String sql="select * from User where u_nick like ? and (u_id=any(select f_gid from friends where f_uid=? and f_state=1))";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+u_nick+"%",u_id});
		try {
			while (rs.next()) {
				User User=new User();
				User.setU_addr(rs.getString("u_addr"));
				User.setU_img(rs.getString("u_img"));
				User.setU_nick(rs.getString("u_nick"));
				 
				lstFollowing.add(User);
			}
			return lstFollowing;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
	//�����ҵ��ղ�
	public List<Collect> searchCollect(final  String co_content ,final int u_id,  int pageSize, int pageNo){		 
		List<Collect> lstCollects=new ArrayList<Collect>();
		DBconn DBconn=new DBconn();		
		String sql="select * from collect where co_content like ? and u_id=? order by co_time desc limit ?, ?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+co_content+"%",u_id,pageSize*(pageNo-1), pageSize});
		try {
			while (rs.next()) {
				Collect collect=new Collect();
				collect.setCo_content(rs.getString("co_content"));
				collect.setCo_time(rs.getDate("co_time"));
				collect.setCo_date(rs.getTime("co_time"));
				collect.setU_id(rs.getInt("u_id"));
				collect.setB_id(rs.getInt("b_id"));
				collect.setCo_img(rs.getString("co_img"));
				lstCollects.add(collect);
			}
			return lstCollects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
	//ͳ��������ҳ��΢������
	public int allSearchArticals(final String b_content,final int u_id ){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from Artical where  b_content like ? and (u_id= any( select f_gid from friends where (f_uid=? and f_state=1) or (f_uid=? and f_state=2))or u_id=?  or u_id= any(select f_uid from friends where f_gid=? and f_state=2))";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+b_content+"%",u_id,u_id,u_id,u_id});
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
			DBconn.closeConn();
		}
	}
	//ͳ�������ҵ�΢��ҳ���΢������
	public int allSearchMyArticals(final String b_content,final int u_id ){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from Artical where  b_content like ? and u_id=?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+b_content+"%",u_id});
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
			DBconn.closeConn();
		}
	}
	//ͳ�������ҵĺ���ҳ�������	
	public int allSearchFriends(final String u_nick,final int u_id ){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from User where u_nick like ? and (u_id=any(select f_gid from friends where f_uid=? and f_state=2) or u_id=any(select f_uid from friends where f_gid=? and f_state=2))";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+u_nick+"%",u_id,u_id});
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
			DBconn.closeConn();
		}
	}
	//ͳ�������ҵ��ղ�ҳ����ղ�����
	public int allSearchCollects(final String co_content,final int u_id ){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from collect where co_content like ? and u_id=?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"+co_content+"%",u_id });
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
			DBconn.closeConn();
		}
	}
}
	 
 