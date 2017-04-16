package action;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * ǰ�˿�������UserInitializer��  DispatcherServlet
 * ֮ǰ��������web.xml ���档
 * @author dd
 *
 */
public class UserInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	/**
	 * ��DispatcherServlet ӳ�䵽"/"
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
