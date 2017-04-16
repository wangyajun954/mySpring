package action;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * 前端控制器（UserInitializer）  DispatcherServlet
 * 之前是配置在web.xml 里面。
 * @author dd
 *
 */
public class UserInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	/**
	 * 将DispatcherServlet 映射到"/"
	 */
	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] {WebConfig.class} ;
	}

}
