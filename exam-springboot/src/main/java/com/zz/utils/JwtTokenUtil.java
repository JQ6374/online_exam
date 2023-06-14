package com.zz.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    // 定义JWT Token的有效期为5小时
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // 从配置文件中读取JWT密钥
    @Value("${jwt.secret}")
    private String secret;

    // 从Token中获取用户名
    public String parserToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // 从Token中获取过期日期
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 从Token中获取指定的Claim
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 解析Token，获取所有的Claims
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // 检查Token是否已过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 生成Token，参数为UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // 将用户名作为Claim存储在Token中
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // 生成Token，参数为String
    public String generateToken(String uId) {
        Map<String, Object> claims = new HashMap<>();
        // 将用户名作为Claim存储在Token中
        return doGenerateToken(claims, uId);
    }

    // 使用给定的Claims和用户名生成Token
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                // 使用密钥对Token进行签名
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 验证Token是否有效
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = parserToken(token);
        // 检查Token是否已过期，并且用户名与Token中的用户名是否匹配
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}