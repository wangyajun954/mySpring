package ynet.jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ynet.entity.User;

/**
 * 样板式代码示例 
 * @author dd
 *
 */
@Component
public class JdbcUseInSpring {
	
	private String SQL_INSERT_USER="INSERT INTO USER(USERNAME,GENDER,AGE)" +
			" VALUES(?,?,?)";//新增对象
	private String SQL_UPDATE_USER="UPDATE USER SET " +
			"USERNAME=?,GENDER=?,AGE=? WHERE ID=?";//修改对象
	private String SQL_SELECT_USER="SELECT * FROM USER WHERE ID=?";//查询对象
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * 新增
	 * @param user
	 */
	public void addUser(User user){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();//获得连接
			pstmt=conn.prepareStatement(SQL_INSERT_USER);//创建语句
			pstmt.setString(1, user.getUserName());//绑定参数
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getAge());
			
			pstmt.execute();
			System.out.println("添加成功了！！！");
		} catch (SQLException e) {
			// TODO  处理异常
			e.printStackTrace();
		}finally{//清理资源
			try {
				if(pstmt != null){
					pstmt.close();
					
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	
	
	
	
	/**
	 * 修改
	 * @param user
	 */
	public void updateUser(User user){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();//获得连接
			pstmt=conn.prepareStatement(SQL_UPDATE_USER);//创建语句
			pstmt.setString(1, user.getUserName());//绑定参数
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getAge());
			pstmt.setString(4, user.getId());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO  处理异常
			e.printStackTrace();
		}finally{//清理资源
			try {
				if(pstmt != null){
					pstmt.close();
					
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	/**
	 * 得到一个对象
	 * @param user
	 */
	public User findOneUser(String id){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();//获得连接
			pstmt=conn.prepareStatement(SQL_SELECT_USER);//创建语句
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			User user=null;
			if(rs.next()){
				user = new User(id, rs.getString("username"),
						rs.getString("gender"), rs.getString("age"));
			}
			return user;
		} catch (SQLException e) {
			// TODO  处理异常
			e.printStackTrace();
		}finally{//清理资源
			try {
				if(rs != null){
					rs.close();
					
				}
				
				if(pstmt != null){
					pstmt.close();
					
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	

}
