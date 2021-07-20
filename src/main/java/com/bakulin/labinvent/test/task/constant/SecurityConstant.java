package com.bakulin.labinvent.test.task.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token ";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management portal";
    public static final  String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not has permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTION";
    public static final String EMAIL_SENT = "An email with new password sent to: ";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    public static final String[] PUBLIC_URLS = {"/user/login","/user/register", "/user/reset-password/**", "/user/image/**","/error"};



}
