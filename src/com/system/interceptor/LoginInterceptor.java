package com.system.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.system.model.UserModel;

public class LoginInterceptor implements Interceptor {
	private static final long serialVersionUID = 2206603184236267879L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		UserModel user = (UserModel) ctx.getSession().get("user");
		if (user != null) {
			return invocation.invoke();
		}
		return Action.LOGIN;
	}

}
