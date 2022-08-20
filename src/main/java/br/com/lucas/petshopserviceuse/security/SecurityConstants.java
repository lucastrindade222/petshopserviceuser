package br.com.lucas.petshopserviceuse.security;

public class SecurityConstants {

    public static final String SECRET = "fac6dd3590b19f71d916bdaacb211750d288f6006d4b30806310682143a502eabb68a4225cdcd8a25f3bee2e193d6642d9b847ec80d5ca986955b6d56420bb15";
    public static final String TOKEN_PREXI = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 86400000L;

    public static final String[] PUBLIC_MATCHERS_POST = {
    "/cliet"
    };

}
