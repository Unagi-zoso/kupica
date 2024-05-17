package org.nightdivers.kupica.domain.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_INVALID_ARTICLE_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createTestAnonymousUser1;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestAnonymousArticle1;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestAnonymousArticle2;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestAnonymousArticle3;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle1;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle2;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle3;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.nightdivers.kupica.support.factory.MemberFactory;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class ArticleRepositoryTest {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;

    Member givenMember1;

    AnonymousUser givenAnonymousUser1;

    int givenTestDataSetSize = 6;

    Article givenMemberArticle1;
    Article givenMemberArticle2;
    Article givenMemberArticle3;

    Article givenAnonymousArticle1;
    Article givenAnonymousArticle2;
    Article givenAnonymousArticle3;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(MemberFactory.createTestMember1());

        givenMemberArticle1 = createTestMemberArticle1(givenMember1);
        givenMemberArticle2 = createTestMemberArticle2(givenMember1);
        givenMemberArticle3 = createTestMemberArticle3(givenMember1);

        givenAnonymousUser1 = anonymousUserRepository.save(
                createTestAnonymousUser1());

        givenAnonymousArticle1 = createTestAnonymousArticle1(givenAnonymousUser1);
        givenAnonymousArticle2 = createTestAnonymousArticle2(givenAnonymousUser1);
        givenAnonymousArticle3 = createTestAnonymousArticle3(givenAnonymousUser1);

        articleRepository.saveAll(
                List.of(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                        givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
                ));
    }

    /* TARGET : 게시글 단일 조회 테스트 */
    @DisplayName("게시글 단일 조회 - [성공]")
    @Test
    void givenArticle_whenFindById_thenSuccess() {
        // given

        // when
        Article actualMemberArticle = articleRepository.findByIdAndErasedFlagIsFalse(givenMemberArticle1.getId())
                .orElseThrow(NoSuchElementException::new);
        Article actualAnonymousArticle = articleRepository.findByIdAndErasedFlagIsFalse(givenAnonymousArticle1.getId())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertAll(
                () -> assertThat(actualMemberArticle).isEqualTo(givenMemberArticle1),
                () -> assertThat(actualAnonymousArticle).isEqualTo(givenAnonymousArticle1)
        );
    }

    @DisplayName("게시글 단일 조회 - [실패 : 존재하지 않는 게시글]")
    @Test
    void givenArticle_whenFindById_thenFail() {
        // given

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> articleRepository.findByIdAndErasedFlagIsFalse(TEST_INVALID_ARTICLE_ID)
                        .orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class),
                () -> assertThatThrownBy(() -> articleRepository.findByIdAndErasedFlagIsFalse(TEST_INVALID_ARTICLE_ID)
                        .orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class)
        );
    }


    /* TARGET : 게시글 목록 조회 테스트 */
    @DisplayName("게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAll_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAllByErasedFlagIsFalse();

        // then
        assertThat(actualArticles).contains(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                                            givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
        );
    }

    @DisplayName("최근 등록된 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAllByOrderByCreatedDatetimeDesc_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAllByErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                PageRequest.of(0, givenTestDataSetSize)).getContent();

        // then
        assertThat(actualArticles.subList(0, givenTestDataSetSize)).containsExactly(
                givenAnonymousArticle3,
                givenAnonymousArticle2, givenAnonymousArticle1,
                givenMemberArticle3, givenMemberArticle2, givenMemberArticle1
        );
    }

    @DisplayName("등록된지 오래된 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAllByOrderByCreatedDatetimeAsc_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAllByErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, 100))
                .getContent();

        // then
        assertThat(actualArticles.subList(
                actualArticles.size() - givenTestDataSetSize,
                actualArticles.size()
        )).containsExactly(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                           givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
        );
    }

    @DisplayName("member id 와 일치하며 최근 등록된 순서대로 member 게시글 목록 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeDesc_thenSuccess() {
        // given
        Article givenMemberArticle1ByMember1 = createTestMemberArticle2(givenMember1);
        Article givenMemberArticle2ByMember1 = createTestMemberArticle3(givenMember1);
        articleRepository.saveAll(
                List.of(givenMemberArticle1ByMember1, givenMemberArticle2ByMember1)
        );

        // when
        List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenMemberArticle1ByMember1.getMember().getId()
                )
                .getContent();

        // then
        assertThat(actualArticles).contains(
                givenMemberArticle2ByMember1,
                givenMemberArticle1ByMember1
        );
    }

    @DisplayName("member id 와 일치하며 최근 등록된 순서대로 member 게시글 목록 조회 - [실패 : 존재하지 않는 회원]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeDesc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                PageRequest.of(0, givenTestDataSetSize),
                TEST_INVALID_MEMBER_ID
        ))
                .isEmpty();
    }

    @DisplayName("member id 와 일치하며 등록된지 오래된 순서대로 member 게시글 목록 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeAsc_thenSuccess() {
        // given
        Article givenMemberArticle1ByMember1 = createTestMemberArticle1(givenMember1);
        Article givenMemberArticle2ByMember1 = ArticleFactory.createTestMemberArticle2(givenMember1);
        articleRepository.saveAll(
                List.of(givenMemberArticle1ByMember1, givenMemberArticle2ByMember1));

        // when
        List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenMemberArticle1ByMember1.getMember().getId()
                )
                .getContent();

        // then
        assertThat(actualArticles).contains(
                givenMemberArticle1ByMember1,
                givenMemberArticle2ByMember1
        );
    }

    @DisplayName("member id 와 일치하며 등록된지 오래된 순서대로 member 게시글 목록 조회 - [실패 : 존재하지 않는 회원]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeAsc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                PageRequest.of(0, givenTestDataSetSize),
                TEST_INVALID_MEMBER_ID
        ))
                .isEmpty();
    }

    @DisplayName("anonymousUser nickname 과 일치하며 최근 등록된 순서대로 anonymous 게시글 목록 조회 - [성공]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeDesc_thenSuccess() {
        // given
        Article givenAnonymousArticle1ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle(
                "anonymousArticle2ByAnonymousUser1", givenAnonymousUser1);
        Article givenAnonymousArticle2ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle(
                "anonymousArticle3ByAnonymousUser1", givenAnonymousUser1);
        articleRepository.saveAll(
                List.of(
                        givenAnonymousArticle1ByAnonymousUser1,
                        givenAnonymousArticle2ByAnonymousUser1
                ));

        // when
        List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenAnonymousUser1.getNickname()
                )
                .getContent();

        // then
        assertThat(actualArticles).contains(
                givenAnonymousArticle2ByAnonymousUser1,
                givenAnonymousArticle1ByAnonymousUser1
        );
    }

    @DisplayName("anonymousUser nickname 과 일치하며 최근 등록된 순서대로 anonymous 게시글 목록 조회 - [실패 : 존재하지 않는 익명 사용자]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeDesc_thenEmpty() {
        // given

        // when & then
        assertThat(
                articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        "invalidAnonymousUser"
                ))
                .isEmpty();
    }

    @DisplayName("anonymousUser nickname 과 일치하며 등록된지 오래된 순서대로 anonymous 게시글 목록 조회 - [성공]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeAsc_thenSuccess() {
        // given
        Article givenAnonymousArticle1ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle(
                "anonymousArticle2ByAnonymousUser1", givenAnonymousUser1);
        Article givenAnonymousArticle2ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle(
                "anonymousArticle3ByAnonymousUser1", givenAnonymousUser1);
        articleRepository.saveAll(
                List.of(
                        givenAnonymousArticle1ByAnonymousUser1,
                        givenAnonymousArticle2ByAnonymousUser1
                ));

        // when
        List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenAnonymousUser1.getNickname()
                )
                .getContent();

        // then
        assertThat(actualArticles).contains(
                givenAnonymousArticle1ByAnonymousUser1,
                givenAnonymousArticle2ByAnonymousUser1
        );
    }

    @DisplayName("anonymousUser nickname 과 일치하며 등록된지 오래된 순서대로 anonymous 게시글 목록 조회 - [실패 : 존재하지 않는 익명 사용자]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeAsc_thenEmpty() {
        // given

        // when & then
        assertThat(
                articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        TEST_INVALID_ANONYMOUS_USER_NICKNAME
                ))
                .isEmpty();
    }
}
