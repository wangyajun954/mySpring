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
 * 通过 pfofile 决定在运行时对应环境下所使用的数据源
 * 开发环境：嵌入式数据源
 * QA环境 ：连接池数据源
 * 生产环境：jndi 数据源
 * @author dd    @ImportResource("classpath:config\\springjdbc.xml")
 *在java 里配置
 *
 */
@Configuration
@ComponentScan(basePackages={"ynet.jdbc02"})
@EnableTransactionManagement
public class JdbcSpringDataSource {

	/**
	 * 生产环境数据源
	 * jndi   webSphere ，Jboss，tomcat 允许通过jndi 获取数据源。
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
	 * QA数据源
	 * 连接池配置。 少 mysql 的驱动栈包
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
		basic.setInitialSize(5);//连接池创建的连接数量
		basic.setMaxActive(10);//连接没有时，允许创建的最大连接数
		
		return basic;
	}
	
	/**
	 * 开发环境数据源
	 * 嵌入式 数据库配置---> H2 数据库。
	 * 优势：1.采用纯java编写，所以不受平台限制。
	 * 		 2.只有一个jar文件，很适合作为嵌入式数据库。
	 * 	     3.h2 提供了十分方便的web控制台用于操作和管理数据库内容。
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
	 * 基于jdbc 驱动的数据源。  了解即可，性能不好，多次创建新的连接。
	 * @return
	 * 
	 * 1、DriverManagerDataSource 没有池化管理，每次创建新的连接
	 * 2、SimpleDriverDataSource 与1、工作方式类似，直接是呀jdbc驱动，
	 *    解决特定环境下的类加载问题，eg:该环境包括：OSGI容器 
	 * 3、SingleConnectionDataSource
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
	 * 使用绑定命名参数
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
