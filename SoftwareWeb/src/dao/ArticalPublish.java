package src.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import src.util.DBconn;
import src.entity.Artical;


public class ArticalPublish {
	

	public boolean Articalpublish(Artical Artical){
		
		//拆出封装对象的属性，组织SQL语句
		String b_content = Artical.getB_content();
		String b_img = Artical.getB_img();
		Date b_time =  new Date(System.currentTimeMillis());
		int u_id=Artical.getU_id();
		//组织SQL语句
		String strSQL = "insert into Artical (b_content, b_img, b_time, u_id) values(?,?,?,?)";
		//连接数据库
		DBconn DBconn = new DBconn();
		int affectedRows = DBconn.execOther(strSQL, new Object[]{b_content,b_img, b_time, u_id});
		//关闭数据连接
		DBconn.closeConn();
		return affectedRows >0?true:false;
	}
	//发微博的重载方法
	public boolean Articalpublish(Artical Artical, int uid){
		
		//拆出封装对象的属性，组织SQL语句
		String b_content = Artical.getB_content();
		String b_img = Artical.getB_img();
		Date b_time =  new Date(System.currentTimeMillis());
		//组织SQL语句
		String strSQL = "insert into Artical (b_content, b_img, b_time, u_id) values(?,?,?,?)";
		//连接数据库
		DBconn DBconn = new DBconn();
		int affectedRows = DBconn.execOther(strSQL, new Object[]{b_content,b_img, b_time, uid});
		//关闭数据连接
		DBconn.closeConn();
		return affectedRows >0?true:false;
	}
	//转发微博
	public boolean dispatchArtical(Artical Artical, int old, int uid){
		DBconn DBconn=new DBconn();
		String sqlOld="update Artical set b_degree=? where b_content=?";
		Articalpublish(Artical,uid);
		int row=DBconn.execOther(sqlOld, new Object[]{old+1, Artical.getB_content()});
		DBconn.closeConn();
		return row>0?true:false;
	}

	//统计转发次数
	public int accountDispatch(final int bid){
		DBconn DBconn=new DBconn();
		String sql="select b_degree from Artical where b_id=?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{bid});
		int result=0;
		try {
			while (rs.next()) {
				result =rs.getInt("b_degree");
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			DBconn.closeConn();
		}
	}
	//通过微博id获取微博信息
	public Artical getArticalById(final int bid){
		DBconn DBconn=new DBconn();
		String sql="select * from Artical where b_id=?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{bid});
		Artical Artical=new Artical();
		try {
			while (rs.next()) {
				Artical.setB_content(rs.getString("b_content"));
				Artical.setB_img(rs.getString("b_img"));
				Artical.setB_time(rs.getDate("b_time"));
				Artical.setU_id(rs.getInt("u_id"));
				Artical.setB_degree(rs.getInt("b_degree"));
				Artical.setB_id(rs.getInt("b_id"));
				Artical.setB_date(rs.getTime("b_time"));
			}
			return Artical;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally{
			DBconn.closeConn();
		}
	}
}
