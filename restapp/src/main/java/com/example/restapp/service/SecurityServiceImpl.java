package com.example.restapp.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService{

    public static final String secretKey = "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";

    @Override   // 최초로 클라이언트에서 요청이 들어오면 토큰 발행
    public String createToken(String subject, long ttlMillis) { // subject로 식별자 값을 주고, ttlMillis로 만료 기간을 보내 준다.
        if (ttlMillis == 0) {
            throw new RuntimeException("토큰 만료기간은 0 이상 이어야 합니다.");
        }

        // HS256 방식으로 암호화 방식 설정
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;       // 암호화 알고리즘 HS256방식 사용
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);      // 암호화 값 사용하기
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder() // builder패턴으로 토큰 생성
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        long nowMillis = System.currentTimeMillis();
        builder.setExpiration(new Date(nowMillis + ttlMillis)); // 만료기간 세팅
        return builder.compact();   // String token발생
    }// 토큰을 받고 나면 이걸 사용해서 요청하기

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))  // secretKey를 가지고 역으로 parsing한다.
                .parseClaimsJws(token).getBody();       // 이 token의 클레임을 받아와 본다. 이게 제대로 가져와지면 변조되지 않은 토큰임을 알 수 있다.

        return claims.getSubject();
    }

    public String get(String token, String key) {
        String value = Jwts.parser()
                .setSigningKey((DatatypeConverter.parseBase64Binary(secretKey)))
                .parseClaimsJwt(token)
                .getBody()
                .get(key, String.class);

        return value;
    }
}
