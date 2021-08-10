package com.bakulin.labinvent.test.task.constant;

public class AuthorityConstant {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read","user:update", "user:create", "user:delete" };
}
