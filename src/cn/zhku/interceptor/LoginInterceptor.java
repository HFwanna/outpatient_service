package cn.zhku.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获取session，session是个map
		Map<String, Object> session = ActionContext.getContext().getSession();
		//获取登录表示
		Object object = session.get("user");
		//判断登录表示是否存在
		if (object==null) {
			return "toLogin";
		}
		else {
			//如果存在，放行
			return invocation.invoke();
		}
	}
	
}