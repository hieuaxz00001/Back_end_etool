package com.rdsic.back_end_e_tool.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTService {
    //Tạo token
    public String generateToken(String username) throws Exception{
        return SecurityConstants.TOKEN_PREFIX + JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

    }

    //Giải mã token
    public String decode(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build().verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getSubject();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }
}