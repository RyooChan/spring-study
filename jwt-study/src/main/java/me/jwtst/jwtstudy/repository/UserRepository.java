package me.jwtst.jwtstudy.repository;

import me.jwtst.jwtstudy.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    // Username을 기준으로 정보를 가져올 때에 권한정보를 같이 가져와준다.
    // Eager조회로 쿼리 수행 시 바로 가져온다.
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
