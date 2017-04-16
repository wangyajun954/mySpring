package action;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//@Controller声明为一个控制器   
//该注解基于 @component 注解  作用是辅助实现组件扫描
@Controller
@RequestMapping({"/","/spittles"})//定义类级别的请求处理，
//login方法可以映射到对"/"和"/spittles"的get请求。
//方法上的@RequestMapping(value="/",method=GET)，value可以省略
public class UserController {

	@RequestMapping(value="/",method=GET)//处理对“/”的get请求
	public String login(){
		
		return "welcome";//视图名是 welcome
	}
	
	
	
}
