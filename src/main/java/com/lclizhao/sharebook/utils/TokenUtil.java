package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/11/30.
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.security.PermitAll;
import java.security.Key;
import java.util.Date;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-30（创建日期）
 * @Description:
 * Token的工具类
 */
@PermitAll
public class TokenUtil {
    public static String getJWTString(String tel,Date expires,Key key){
        if (tel == null) {
            throw new NullPointerException("null username is illegal");
        }

        if (expires == null) {
            throw new NullPointerException("null expires is illegal");
        }
        if (key == null) {
            throw new NullPointerException("null key is illegal");
        }
        SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;
        String jwtString = Jwts.builder()
                .setIssuer("Jersey-Security-Basic")
                .setSubject(tel)
                .setAudience("user")
                .setExpiration(expires)
                .setIssuedAt(new Date())
                .setId("1")
                .signWith(signatureAlgorithm,key)
                .compact();
        return jwtString;
    }
    public static boolean isValid(String token, Key key) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getName(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getSubject();
        }
        return null;
    }
    public static String[] getRoles(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getAudience().split(",");
        }
        return new String[]{};
    }
    public static int getVersion(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return Integer.parseInt(claimsJws.getBody().getId());
        }
        return -1;
    }

}
