package com.yuhao.web.token;

import javax.servlet.http.HttpServletRequest;

public class TokenProcessor {
    static TokenProcessor tokenProcessor = new TokenProcessor();
    public static TokenProcessor getInstance() {
        if(tokenProcessor==null){
              return new TokenProcessor();
        }
        return tokenProcessor;
    }

    public String generateToken(HttpServletRequest request){
       return "12345678";
    }
}
