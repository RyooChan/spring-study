package me.jwtst.jwtstudy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    // DI
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private SecurityUtil() {
    }

    // Authentication객체를 이용한 username 리턴하기
    // 이 Authentication에 username이 저장되는 시점은 JwtFilter의 doFilter메소드에서 Request가 들어올 때 SecurityContext에 Authentication객체를 저장한다.
    public static Optional<String> getCurrentUsername() {
        // authentication을 가져와서
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 정보가 없다면 정보
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        // authentication을 통한 username 받아오기
        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}
