package com.suman.blogz.utils;

public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "5";
    public static final String SORT_BY = "postId";
    public static final String SORT_DIR = "asc";
    public static final Integer MAX_POST_IMG_SIZE_BYTES = 10 * 1024 * 1024;
    public static final Integer MIN_POST_IMG_SIZE_BYTES = 1024;
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String URL_ALL_POSTS = "/blogz/posts/show-posts/all";
    public static final Integer ACCESS_TOKEN_EXPIRATION = 3*60*1000;     // 3 minutes in millis
    public static final Integer REFRESH_TOKEN_EXPIRATION = 6*60*1000;  // 6 minutes in millis
}
