package com.bakulin.labinvent.test.task.model.enumeration;

import static com.bakulin.labinvent.test.task.constant.AuthorityConstant.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);


    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }

}
