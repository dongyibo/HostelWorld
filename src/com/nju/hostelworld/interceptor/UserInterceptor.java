package com.nju.hostelworld.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dongyibo on 2017/3/1.
 */
public class UserInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        String loginFlag = (String) actionInvocation.getInvocationContext().getSession().get("loginFlag");
        if (loginFlag == null){
            return "403";
        }
        else if (!loginFlag.equals("vip")){
            return "noAuthority";
        }
        return actionInvocation.invoke();
    }
}
