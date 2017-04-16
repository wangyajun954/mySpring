package action;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * ����spring mvc ��ע����
 * @author dd
 *@EnableWebMvc ��ʾ����
 *
 *
 */

@Configuration
@EnableWebMvc//����spring mvc
@ComponentScan("springjdbc.web") //�������ɨ�衣
public class WebConfig extends WebMvcConfigurerAdapter{

	/**
	 * ����jsp��ͼ������
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver =
				new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	
	/**
	 * ���þ�̬��Դ�Ĵ���
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
	
	
	
	
	
	
	
}
