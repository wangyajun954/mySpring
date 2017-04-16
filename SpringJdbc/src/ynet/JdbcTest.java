package ynet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ynet.entity.User;
import ynet.jdbc01.JdbcSpringDataSource;
import ynet.jdbc02.JdbcUseInSpring;
import ynet.jdbc02.JdbcUseInSpringTemplate;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcSpringDataSource.class)
@ActiveProfiles("qa")//��������Ի����µ� ����jdbc���õ�����Դ����
public class JdbcTest {

		@Autowired
	private JdbcUseInSpring jdbUser;
	
	@Autowired
	private JdbcUseInSpringTemplate jdTem;
	
	@Test
	public  void jdbcTestFunc(){
		User user= new User("1", "У��С��", "1", "20");
		//jdbUser.addUser(user);	//jdbUser.updateUser(user);
		user=jdbUser.findOneUser(user.getId());
		System.out.println(user.getUserName());
	}
	
	@Test
	public  void jdbcTestTemplate(){
		User user= new User("1", "У��", "1", "43");
		//jdTem.addUser(user);
		//jdTem.addUserBA(user); ������
		jdTem.updateUser(user);
		//System.out.println(jdTem.findOneUser("9").getUserName());
		
		System.out.println(user.getUserName());
		
	}
	
	
}
