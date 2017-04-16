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
 * ����ʽ����ʾ�� 
 * @author dd
 *
 */
@Component
public class JdbcUseInSpring {
	
	private String SQL_INSERT_USER="INSERT INTO USER(USERNAME,GENDER,AGE)" +
			" VALUES(?,?,?)";//��������
	private String SQL_UPDATE_USER="UPDATE USER SET " +
			"USERNAME=?,GENDER=?,AGE=? WHERE ID=?";//�޸Ķ���
	private String SQL_SELECT_USER="SELECT * FROM USER WHERE ID=?";//��ѯ����
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * ����
	 * @param user
	 */
	public void addUser(User user){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();//�������
			pstmt=conn.prepareStatement(SQL_INSERT_USER);//�������
			pstmt.setString(1, user.getUserName());//�󶨲���
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getAge());
			
			pstmt.execute();
			System.out.println("��ӳɹ��ˣ�����");
		} catch (SQLException e) {
			// TODO  �����쳣
			e.printStackTrace();
		}finally{//������Դ
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
	 * �޸�
	 * @param user
	 */
	public void updateUser(User user){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();//�������
			pstmt=conn.prepareStatement(SQL_UPDATE_USER);//�������
			pstmt.setString(1, user.getUserName());//�󶨲���
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getAge());
			pstmt.setString(4, user.getId());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO  �����쳣
			e.printStackTrace();
		}finally{//������Դ
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
	 * �õ�һ������
	 * @param user
	 */
	public User findOneUser(String id){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();//�������
			pstmt=conn.prepareStatement(SQL_SELECT_USER);//�������
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			User user=null;
			if(rs.next()){
				user = new User(id, rs.getString("username"),
						rs.getString("gender"), rs.getString("age"));
			}
			return user;
		} catch (SQLException e) {
			// TODO  �����쳣
			e.printStackTrace();
		}finally{//������Դ
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
