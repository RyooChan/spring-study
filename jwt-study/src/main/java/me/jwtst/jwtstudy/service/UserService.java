package me.jwtst.jwtstudy.service;

import java.util.Collections;
import java.util.Optional;

import javassist.bytecode.DuplicateMemberException;
import me.jwtst.jwtstudy.dto.UserDto;
import me.jwtst.jwtstudy.entity.Authority;
import me.jwtst.jwtstudy.entity.User;
import me.jwtst.jwtstudy.repository.UserRepository;
import me.jwtst.jwtstudy.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    @Transactional
    public UserDto signup(UserDto userDto) {
        // 이미 있는 유저라면 회원가입 불가능 -> username을 통한 검색
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            try {
                throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
            } catch (DuplicateMemberException e) {
                e.printStackTrace();
            }
        }
        // 권한정보 생성
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")     // ROLE_USER권한만 갖는다.
                .build();
        // 유저정보 생성(위에서 만든 권한정보 포함)
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));
    }

    // username을 받아서 이를 통해 권한정보를 받아온다.
    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    // 현재 security context에 저장된 username정보만 가져온다.
    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}
