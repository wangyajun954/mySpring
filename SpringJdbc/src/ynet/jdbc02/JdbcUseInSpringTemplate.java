package ynet.jdbc02;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ynet.entity.User;


//@Repository  @Component

@Repository
public class JdbcUseInSpringTemplate {

	private JdbcOperations jdbcOperations;
	
	@Inject
	public JdbcUseInSpringTemplate (JdbcOperations jdbcOperations){
		
		this.jdbcOperations=jdbcOperations;
	}
	
	private String SQL_INSERT_USER="INSERT INTO USER(USERNAME,GENDER,AGE)" +
			" VALUES(?,?,?)";//新增对象
	
	private String SQL_INSERT_USER_BA="INSERT INTO USER(USERNAME,GENDER,AGE)" +
			" VALUES(:username,:gender,:age)";//新增对象
	private String SQL_UPDATE_USER="UPDATE USER SET " +
			"USERNAME=?,GENDER=?,AGE=? WHERE ID=?";//修改对象
	private String SQL_SELECT_USER="SELECT * FROM USER WHERE ID=?";//查询对象
	
	
	
	/**
	 * 新增  使用命名参数绑定模板添加数据
	 * @param user
	 */
	public void addUserBA(User user){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", user.getUserName());
		paramMap.put("gender", user.getGender());
		paramMap.put("age", user.getAge());
		jdbcOperations.update(SQL_INSERT_USER_BA,paramMap);
		System.out.println("模板测试绑定参数式添加成功！！");
		
	}
	
	
	/**
	 * 新增
	 * @param user
	 */
	public void addUser(User user){
		
		jdbcOperations.update(SQL_INSERT_USER,
				user.getUserName(),user.getGender(),user.getAge());
		System.out.println("模板测试添加成功！！");
	}
	
	
	
	
	
	
	/**
	 * 修改
	 * @param user
	 */
	public void updateUser(User user){
		jdbcOperations.update(SQL_UPDATE_USER,user.getUserName(),
				user.getGender(),user.getAge(),user.getId());
		System.out.println("模板测试修改成功！！");
	}
	
	
	/**
	 * 得到一个对象，在java 8 里可以使用 Lambda 表达式 或者 方法引用皆可。
	 * @param user
	 */
	public User findOneUser(String id){
		
		return jdbcOperations.queryForObject(SQL_SELECT_USER, 
				new UserRowMapper()/*将查询结果映射到对象，
				从resultset（）里提取数据，并构建域对象*/,id);
		
	}
	
	private static final class UserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int row) throws SQLException {
			
			return new User(rs.getString("id"), rs.getString("username"),
					rs.getString("gender"),rs.getString("age"));
		}
		
		
	}

	
	
	
	
	
	
	
	
	
}
