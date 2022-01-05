package me.jwtst.jwtstudy.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  // 기본적인 web시큐리티를 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {      // 추가 설정을 위한 extends

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"      // h2, favicon요청 무시
                        , "favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()    // httpServletRequest를 사용하는 요청에 대한 접근 제한을 설정함.
                .antMatchers("/api/hello").permitAll()   // 해당 URL에의 접근은 인증 필요 x
                .anyRequest().authenticated();      // 나머지는 모두 인증 받아야한다.
    }
}
