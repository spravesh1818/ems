package com.ems.ems.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_EMPLOYEE,ROLE_FINANCE,ROLE_HR;

  public String getAuthority() {
    return name();
  }

}
