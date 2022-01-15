package com.community;

import com.community.domain.Board;
import com.community.domain.User;
import com.community.domain.enums.BoardType;
import com.community.repository.BoardRepository;
import com.community.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)    // JUnit내장 runner사용 대신 어노테이션에 저장된 클래스 호출하기. 테스트의 독립실행
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "fbcks97@naver.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before     // 실제 TEST가 실행되기 전에 각 repository를 통해 테스트 해줄 값들을 미리 저장시켜준다.
    public void init(){
        User user = userRepository.save(User.builder()
                .name("ryoochan")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브")
                .content("내용")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build());

    }

    @Test       // 실제 테스트가 이루어진다. 위의 값들과 동일한 값으로 검사해서, 통과하면 문제가 없는 것이다.
    public void 생성테스트(){
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("ryoochan"));
        assertThat(user.getPassword(), is("test"));;
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("서브"));
        assertThat(board.getContent(), is("내용"));
        assertThat(board.getBoardType(), is(BoardType.free));
        // assertThat 자주 쓰이니까 잘 기억하기~
    }

}
