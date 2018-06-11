package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import src.util.DBconn;
import src.entity.Artical;
import src.entity.Comment;
/**
 * 
 * 
 *	
 */
public class CommentDao {

	//�������
	public void postComment(Artical artical, final int uid, String comment){
		DBconn dbConn=new DBconn();
		Date date=new Date(System.currentTimeMillis());
		String insertSql="insert into comment (u_id, b_id, c_content, c_time) values (?,?,?,?)";
		dbConn.execOther(insertSql, new Object[]{uid, artical.getB_id(), comment, date});
		dbConn.closeConn();
	}

	public int accountComment(final int bid){
		DBconn dbConn=new DBconn();
		String sql="select count(*) from comment where b_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{bid});
		int num=0;
		try {
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
		
			return 0;
		}finally{
			dbConn.closeConn();
		}
	}
	
	public ArrayList<Comment> getContentById(final int bid){
		ArrayList<Comment> listComments=new ArrayList<Comment>();
		DBconn dbConn=new DBconn();
		String sql="select * from comment where b_id=?";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{bid});
		try {
			while (rs.next()) {
				Comment comment=new Comment();
				comment.setC_content(rs.getString("c_content"));
				comment.setU_id(rs.getInt("u_id"));
				listComments.add(comment);
			}
			return listComments;
		} catch (SQLException e) {
			return null;
		}finally{
			dbConn.closeConn();
		}
}
}