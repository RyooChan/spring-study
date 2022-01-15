package com.community;

import com.community.domain.Board;
import com.community.domain.User;
import com.community.domain.enums.BoardType;
import com.community.repository.BoardRepository;
import com.community.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

    // bean을 사용하여 User/Board repository DI받기
    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
        return (args) -> {
            // 아래 IntStream.rangeClosed를 통해 저장할 때 사용되는 user의 정보 미리 만들기
            User user = userRepository.save(User.builder()
                    .name("ryoochan")
                    .password("test")
                    .email("fbcks97@naver.com")
                    .createdDate(LocalDateTime.now())
                    .build()
            );
            // 1~200의 index를 가지는 글 200개를 만들어서 저장시킨다.
            IntStream.rangeClosed(1, 200).forEach(index ->
                    boardRepository.save(Board.builder()
                            .title(index+"번째 글")
                            .subTitle("부제 : " + index + " 번째임")
                            .content("내용없음")
                            .boardType(BoardType.free)
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .user(user).build()
                    )
            );
        };
    }

}
