package com.nju.hostelworld.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dongyibo on 2017/1/20.
 */
public class LoginInterceptor extends MethodFilterInterceptor {

//    @Override
//    public String intercept(ActionInvocation actionInvocation) throws Exception {
//        String loginFlag = (String) actionInvocation.getInvocationContext().getSession().get("loginFlag");
//        if (loginFlag!=null && loginFlag.equals("loginFlag")){
//            return actionInvocation.invoke();
//        }
//        return "403";
//    }

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        String loginFlag = (String) actionInvocation.getInvocationContext().getSession().get("loginFlag");
        if (loginFlag != null && loginFlag.equals("loginFlag")) {
            return actionInvocation.invoke();
        }
        return "403";
    }

//    @Override
//    public void setExcludeMethods(String excludeMethods) {
//        super.setExcludeMethods(excludeMethods);
//    }
}
