package action;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//@Controller����Ϊһ��������   
//��ע����� @component ע��  �����Ǹ���ʵ�����ɨ��
@Controller
@RequestMapping({"/","/spittles"})//�����༶���������
//login��������ӳ�䵽��"/"��"/spittles"��get����
//�����ϵ�@RequestMapping(value="/",method=GET)��value����ʡ��
public class UserController {

	@RequestMapping(value="/",method=GET)//����ԡ�/����get����
	public String login(){
		
		return "welcome";//��ͼ���� welcome
	}
	
	
	
}
