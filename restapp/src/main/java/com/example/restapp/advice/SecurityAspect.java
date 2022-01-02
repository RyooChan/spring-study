package com.example.restapp.advice;

import com.example.restapp.annotation.TokenRequired;
import com.example.restapp.service.SecurityServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(tokenRequired)")       // 해당 annotation이 적용된 메소드는 아래 코드가 수행된다.
    public void authWithToken(TokenRequired tokenRequired) {
        ServletRequestAttributes reqAttributes =
                (ServletRequestAttributes)
                        RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = reqAttributes.getRequest();
        // checks for token in request header
        String tokenInHeader = request.getHeader("token");
        if(StringUtils.isEmpty(tokenInHeader)){     // 요청 헤드 확인 -> token이라는 이름으로 왔는지
            throw new IllegalArgumentException("Empty token");
        }
        Claims claims = Jwts.parser().setSigningKey(        // 위의 token의 경우 수행
                        DatatypeConverter.parseBase64Binary(
                                SecurityServiceImpl.secretKey))
                .parseClaimsJws(tokenInHeader).getBody();
        if(claims == null || claims.getSubject() == null) {     // 그렇지 않은 경우 예외 발생
            throw new IllegalArgumentException("Token Error : Claim is null");
        }
        System.out.println("토큰에 포함된 subject로 자체 인증처리 필요..");
    }
}
