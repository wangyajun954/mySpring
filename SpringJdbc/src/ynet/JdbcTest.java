package ynet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ynet.entity.User;
import ynet.jdbc01.JdbcSpringDataSource;
import ynet.jdbc02.JdbcUseInSpring;
import ynet.jdbc02.JdbcUseInSpringTemplate;

/**
 * 
 * @author dd
 *@ImportResource("classpath:config\\springjdbc01.xml")
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcSpringDataSource.class)
@ActiveProfiles("dev")
//��������Ի����µ� ����jdbc���õ�����Դ���� @ActiveProfiles("qa")

public class JdbcTest {

		@Autowired
	private JdbcUseInSpring jdbUser;
	
	@Autowired
	private JdbcUseInSpringTemplate jdTem;
	
	@Test
	public  void jdbcTestFunc(){
		User user= new User("1", "У��rrr", "1", "20");
		jdbUser.addUser(user);	//jdbUser.updateUser(user);
		//user=jdbUser.findOneUser(user.getId());
		System.out.println(user.getUserName());
	}
	
	@Test
	public  void jdbcTestTemplate(){
		User user= new User("11", "AA��", "1", "20");
		//jdTem.addUser(user);
		//jdTem.addUserBA(user); ������
		//jdTem.updateUser(user);
		//System.out.println(jdTem.findOneUser("9").getUserName());
		jdTem.exchangeUser(user);
		//jdTem.selectUser();
		//System.out.println(user.getUserName());
		
	}
	
	
}
