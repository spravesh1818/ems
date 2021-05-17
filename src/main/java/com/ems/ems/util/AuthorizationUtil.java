package com.ems.ems.util;

import org.springframework.security.core.Authentication;

public class AuthorizationUtil {

    private static AuthorizationUtil authorizationUtil=null;
    private AuthorizationUtil(){};
    public static AuthorizationUtil getInstance(){
        if(authorizationUtil==null)
            authorizationUtil=new AuthorizationUtil();

        return authorizationUtil;
    }

    public boolean checkAdminRole(Authentication authentication){
        return authentication.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN");
    }


    public boolean checkEmployeeRole(Authentication authentication){
        return authentication.getAuthorities().toArray()[0].toString().equals("ROLE_EMPLOYEE");
    }

}
