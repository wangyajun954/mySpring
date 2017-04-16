package ynet.entity;

/**
 * ”√ªß±Ì
 * @author dd
 *
 */
public class User {

	private String id;
	private String userName;
	private String gender;
	private String age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public User(String id, String userName, String gender, String age) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
	}
	public User() {
		super();
	}
	
	
	
	
}
