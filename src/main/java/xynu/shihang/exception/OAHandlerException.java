package xynu.shihang.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class OAHandlerException  implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception ex) {
		//��ӡ���쳣��Ϣ�Ա����Ա�鿴
		ex.printStackTrace();
		
		String message = null;
		ModelAndView view = new ModelAndView();
		if (ex instanceof OAException) {
			message = ex.getMessage();
		}else{
			message= "δ֪����";
		}
		view.addObject("message", message);
		view.setViewName("forward:/error.jsp");
		return view;
	}

}
