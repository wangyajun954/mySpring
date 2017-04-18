package ynet.jdbc01;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sun.xml.internal.bind.v2.runtime.Name;

import ynet.jdbc02.JdbcUseInSpringTemplate;

/**
 * ͨ�� pfofile ����������ʱ��Ӧ��������ʹ�õ�����Դ
 * ����������Ƕ��ʽ����Դ
 * QA���� �����ӳ�����Դ
 * ����������jndi ����Դ
 * @author dd    @ImportResource("classpath:config\\springjdbc.xml")
 *��java ������
 *
 */
@Configuration
@ComponentScan(basePackages={"ynet.jdbc02"})
@EnableTransactionManagement
public class JdbcSpringDataSource {

	/**
	 * ������������Դ
	 * jndi   webSphere ��Jboss��tomcat ����ͨ��jndi ��ȡ����Դ��
	 * @return
	 */
	@Profile("pro")
	@Bean
	public DataSource dataSource(){
		JndiObjectFactoryBean jofb=new JndiObjectFactoryBean();
		jofb.setJndiName("JDBC/CWQITEST");
		jofb.setResourceRef(true);
		jofb.setProxyInterface(DataSource.class);
		
		return (DataSource)jofb.getObject();
	}
	
	
	/**
	 * QA����Դ
	 * ���ӳ����á� �� mysql ������ջ��
	 * @return
	 */
	@Profile("qa")
	@Bean
	public DataSource dataSourceQA(){
		
		BasicDataSource basic = new BasicDataSource();
		basic.setDriverClassName("com.mysql.jdbc.Driver");
		basic.setUrl("jdbc:mysql://localhost:3306/test");
		basic.setUsername("root");
		basic.setPassword("root");
		basic.setInitialSize(5);//���ӳش�������������
		basic.setMaxActive(10);//����û��ʱ�������������������
		
		return basic;
	}
	
	/**
	 * ������������Դ
	 * Ƕ��ʽ ���ݿ�����---> H2 ���ݿ⡣
	 * ���ƣ�1.���ô�java��д�����Բ���ƽ̨���ơ�
	 * 		 2.ֻ��һ��jar�ļ������ʺ���ΪǶ��ʽ���ݿ⡣
	 * 	     3.h2 �ṩ��ʮ�ַ����web����̨���ڲ����͹������ݿ����ݡ�
	 * @return
	 */
	@Profile("devQr")
	@Bean
	public DataSource embeddedDataSource(){
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test_data.sql").build();
	}
	
	/**
	 * ����jdbc ����������Դ��  �˽⼴�ɣ����ܲ��ã���δ����µ����ӡ�
	 * @return
	 * 
	 * 1��DriverManagerDataSource û�гػ�����ÿ�δ����µ�����
	 * 2��SimpleDriverDataSource ��1��������ʽ���ƣ�ֱ����ѽjdbc������
	 *    ����ض������µ���������⣬eg:�û���������OSGI���� 
	 * 3��SingleConnectionDataSource
	 * 
	 */
	
	@Profile("dev")
	@Bean
	public DataSource jdbcDataSource(){
		
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("root");
		
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		
		return new JdbcTemplate(dataSource);
	}
	
	/**
	 * ʹ�ð���������
	 * @param dataSource
	 * @return
	 */
	@Bean
	public NamedParameterJdbcTemplate nameJdbcTemplate(){
		return new NamedParameterJdbcTemplate(jdbcDataSource());
	}
	
	@Bean
	public DataSourceTransactionManager txmanage(DataSource dataSource){
		DataSourceTransactionManager  txmanage= new DataSourceTransactionManager();
		txmanage.setDataSource(dataSource);
		return txmanage;
	}
	
//	@Bean
//	public JdbcUseInSpringTemplate jdbcTemplate(JdbcTemplate jdbcTemplate){
//		return new JdbcUseInSpringTemplate(jdbcTemplate);
//	}
	
}
