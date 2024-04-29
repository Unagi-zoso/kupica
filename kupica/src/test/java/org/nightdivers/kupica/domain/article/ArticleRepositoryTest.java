package org.nightdivers.kupica.domain.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.domain.articlelike.ArticleLike.createAnonymousArticleLike;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_IP_LIST;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_INVALID_ARTICLE_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.articlelike.ArticleLikeRepository;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.nightdivers.kupica.support.factory.MemberFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class ArticleRepositoryTest {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final EntityManager entityManager;

    Member givenMember1;

    AnonymousUser givenAnonymousUser1;

    // 실제론 테스트 시 test.resource.sql.data-h2.sql 에서 만들어 둔 테스트 셋이 더 존재합니다.
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

        givenMemberArticle1 = ArticleFactory.createTestMemberArticle1();
        givenMemberArticle2 = ArticleFactory.createTestMemberArticle2();
        givenMemberArticle3 = ArticleFactory.createTestMemberArticle3();

        givenAnonymousUser1 = anonymousUserRepository.save(AnonymousUserFactory.createTestAnonymousUser1());

        givenAnonymousArticle1 = ArticleFactory.createTestAnonymousArticle1();
        givenAnonymousArticle2 = ArticleFactory.createTestAnonymousArticle2();
        givenAnonymousArticle3 = ArticleFactory.createTestAnonymousArticle3();

        articleRepository.saveAll(List.of(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3));
    }

    /* 게시글 단일 조회 */
    @DisplayName("게시글 단일 조회 - [성공]")
    @Test
    void givenArticle_whenFindById_thenSuccess() {
        // given

        // when
        Article actualMemberArticle = articleRepository.findById(givenMemberArticle1.getId()).orElseThrow(NoSuchElementException::new);
        Article actualAnonymousArticle = articleRepository.findById(givenAnonymousArticle1.getId()).orElseThrow(NoSuchElementException::new);

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
                () -> assertThatThrownBy(() -> articleRepository.findById(TEST_INVALID_ARTICLE_ID).orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class),
                () -> assertThatThrownBy(() -> articleRepository.findById(TEST_INVALID_ARTICLE_ID).orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class)
        );
    }


    /* 게시글 목록 조회 */
    @DisplayName("게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAll_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAll();

        // then
        assertThat(actualArticles).contains(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3);
    }

    @DisplayName("최근 등록된 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAllByOrderByCreatedDatetimeDesc_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAllByOrderByCreatedDatetimeDesc();

        // then
        assertThat(actualArticles.subList(0, givenTestDataSetSize)).containsExactly(givenAnonymousArticle3, givenAnonymousArticle2, givenAnonymousArticle1,
                givenMemberArticle3, givenMemberArticle2,  givenMemberArticle1);
    }

    @DisplayName("등록된지 오래된 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticles_whenFindAllByOrderByCreatedDatetimeAsc_thenSuccess() {
        // given

        // when
        List<Article> actualArticles = articleRepository.findAllByOrderByCreatedDatetimeAsc();

        // then
        assertThat(actualArticles.subList(actualArticles.size() - givenTestDataSetSize, actualArticles.size())).containsExactly(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3);
    }

    @DisplayName("member id 와 일치하며 최근 등록된 순서대로 member 게시글 목록 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeDesc_thenSuccess() {
        // given
        Article givenMemberArticle1ByMember1 = ArticleFactory.createCustomMemberArticle("memberArticle2ByMember1", givenMember1);
        Article givenMemberArticle2ByMember1 = ArticleFactory.createCustomMemberArticle("memberArticle3ByMember1", givenMember1);
        articleRepository.saveAll(List.of(givenMemberArticle1ByMember1, givenMemberArticle2ByMember1));

        // when
        List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeDesc(givenMemberArticle1ByMember1.getMember().getId());

        // then
        assertThat(actualArticles).containsExactly(givenMemberArticle2ByMember1, givenMemberArticle1ByMember1);
    }

    @DisplayName("member id 와 일치하며 최근 등록된 순서대로 member 게시글 목록 조회 - [실패 : 존재하지 않는 회원]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeDesc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeDesc(TEST_INVALID_MEMBER_ID)).isEmpty();
    }

    @DisplayName("member id 와 일치하며 등록된지 오래된 순서대로 member 게시글 목록 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeAsc_thenSuccess() {
        // given
        Article givenMemberArticle1ByMember1 = ArticleFactory.createCustomMemberArticle("memberArticle2ByMember1", givenMember1);
        Article givenMemberArticle2ByMember1 = ArticleFactory.createCustomMemberArticle("memberArticle3ByMember1", givenMember1);
        articleRepository.saveAll(List.of(givenMemberArticle1ByMember1, givenMemberArticle2ByMember1));

        // when
        List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeAsc(givenMemberArticle1ByMember1.getMember().getId());

        // then
        assertThat(actualArticles).containsExactly(givenMemberArticle1ByMember1, givenMemberArticle2ByMember1);
    }

    @DisplayName("member id 와 일치하며 등록된지 오래된 순서대로 member 게시글 목록 조회 - [실패 : 존재하지 않는 회원]")
    @Test
    void givenMemberId_whenFindAllByMemberIdOrderByCreatedDatetimeAsc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeAsc(TEST_INVALID_MEMBER_ID)).isEmpty();
    }

    @DisplayName("anonymousUser nickname 과 일치하며 최근 등록된 순서대로 anonymous 게시글 목록 조회 - [성공]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeDesc_thenSuccess() {
        // given
        Article givenAnonymousArticle1ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle("anonymousArticle2ByAnonymousUser1", givenAnonymousUser1);
        Article givenAnonymousArticle2ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle("anonymousArticle3ByAnonymousUser1", givenAnonymousUser1);
        articleRepository.saveAll(List.of(givenAnonymousArticle1ByAnonymousUser1, givenAnonymousArticle2ByAnonymousUser1));

        // when
        List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeDesc(givenAnonymousUser1.getNickname());

        // then
        assertThat(actualArticles).containsExactly(givenAnonymousArticle2ByAnonymousUser1, givenAnonymousArticle1ByAnonymousUser1);
    }

    @DisplayName("anonymousUser nickname 과 일치하며 최근 등록된 순서대로 anonymous 게시글 목록 조회 - [실패 : 존재하지 않는 익명 사용자]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeDesc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeDesc("invalidAnonymousUser")).isEmpty();
    }

    @DisplayName("anonymousUser nickname 과 일치하며 등록된지 오래된 순서대로 anonymous 게시글 목록 조회 - [성공]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeAsc_thenSuccess() {
        // given
        Article givenAnonymousArticle1ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle("anonymousArticle2ByAnonymousUser1", givenAnonymousUser1);
        Article givenAnonymousArticle2ByAnonymousUser1 = ArticleFactory.createCustomAnonymousArticle("anonymousArticle3ByAnonymousUser1", givenAnonymousUser1);
        articleRepository.saveAll(List.of(givenAnonymousArticle1ByAnonymousUser1, givenAnonymousArticle2ByAnonymousUser1));

        // when
        List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeAsc(givenAnonymousUser1.getNickname());

        // then
        assertThat(actualArticles).containsExactly(givenAnonymousArticle1ByAnonymousUser1, givenAnonymousArticle2ByAnonymousUser1);
    }

    @DisplayName("anonymousUser nickname 과 일치하며 등록된지 오래된 순서대로 anonymous 게시글 목록 조회 - [실패 : 존재하지 않는 익명 사용자]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNicknameOrderByCreatedDatetimeAsc_thenEmpty() {
        // given

        // when & then
        assertThat(articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeAsc(TEST_INVALID_ANONYMOUS_USER_NICKNAME)).isEmpty();
    }

    @DisplayName("좋아요 수가 많은 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticlesAndArticleLikes_whenFindAllByOrderByLikeCountDesc_thenSuccess() {
        // given
        articleLikeRepository.deleteAll();

        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle1)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size()-1)
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle2)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size()-2)
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle3)));

        // when
        List<Article> actualArticles = articleRepository.findArticlesOrderByLikesCountDesc(PageRequest.of(0, 3)).getContent();

        // then
        assertThat(actualArticles).containsExactly(givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3);
    }

    @DisplayName("좋아요 수가 적은 순서대로 게시글 목록 조회 - [성공]")
    @Test
    void givenArticlesAndArticleLikes_whenFindAllByOrderByLikeCountAsc_thenSuccess() {
        // given
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle1)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size()-1)
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle2)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size()-2)
                .forEach(ip -> articleLikeRepository.save(createAnonymousArticleLike(ip, givenAnonymousArticle3)));

        // when
        List<Article> actualArticles = articleRepository.findArticlesOrderByLikesCountAsc(PageRequest.of(0, 50)).getContent();

        // then
        assertAll(
                () -> assertThat(actualArticles.getLast()).isEqualTo(givenAnonymousArticle1),
                () -> assertThat(actualArticles.get(actualArticles.size()-2)).isEqualTo(givenAnonymousArticle2),
                () -> assertThat(actualArticles.get(actualArticles.size()-3)).isEqualTo(givenAnonymousArticle3)
        );
    }


    /* 게시글 등록 */
    @DisplayName("게시글 등록 - [성공]")
    @Test
    void givenArticle_whenSave_thenSuccess() {
        // given
        Article expectedMemberArticle = ArticleFactory.createTestMemberArticle4();
        Article expectedAnonymousArticle = ArticleFactory.createTestAnonymousArticle4();

        // when
        Article actualMemberArticle = articleRepository.save(expectedMemberArticle);
        Article actualAnonymousArticle = articleRepository.save(expectedAnonymousArticle);

        // then
        assertAll(
                () -> assertThat(actualMemberArticle).isEqualTo(expectedMemberArticle),
                () -> assertThat(actualAnonymousArticle).isEqualTo(expectedAnonymousArticle)
        );
    }

    @DisplayName("게시글 등록 - [실패 : 게시글 제목이 없는 경우]")
    @Test
    void givenArticle_whenSave_thenFail() {
        // given
        Article expectedMemberArticle = ArticleFactory.createCustomMemberArticle(null, null);
        Article expectedAnonymousArticle = ArticleFactory.createCustomAnonymousArticle(null, null);

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> articleRepository.save(expectedMemberArticle))
                        .isInstanceOf(DataIntegrityViolationException.class),
                () -> assertThatThrownBy(() -> articleRepository.save(expectedAnonymousArticle))
                        .isInstanceOf(DataIntegrityViolationException.class)
        );
    }


    /* 게시글 수정 */
    @DisplayName("게시글 본문 수정 - [성공]")
    @Test
    void givenArticle_whenChangeCaption_thenSuccess() {
        // given
        Article memberArticle = articleRepository.findById(givenMemberArticle1.getId()).orElseThrow(NoSuchElementException::new);
        LocalDateTime prevMemberArticleUpdatedDatetime = memberArticle.getUpdatedDatetime();
        Article anonymousArticle = articleRepository.findById(givenAnonymousArticle1.getId()).orElseThrow(NoSuchElementException::new);
        LocalDateTime prevAnonymousArticleUpdatedDatetime = anonymousArticle.getUpdatedDatetime();

        // when
        memberArticle.changeCaption("newCaption");
        anonymousArticle.changeCaption("newCaption");

        entityManager.flush();

        Article actualMemberArticle = articleRepository.findById(memberArticle.getId()).orElseThrow(NoSuchElementException::new);
        Article actualAnonymousArticle = articleRepository.findById(anonymousArticle.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertAll(
                () -> assertThat(actualMemberArticle.getCaption()).isEqualTo("newCaption"),
                () -> assertThat(actualMemberArticle.getUpdatedDatetime()).isAfter(prevMemberArticleUpdatedDatetime),
                () -> assertThat(actualAnonymousArticle.getCaption()).isEqualTo("newCaption"),
                () -> assertThat(actualAnonymousArticle.getUpdatedDatetime()).isAfter(prevAnonymousArticleUpdatedDatetime)
        );
    }

    @DisplayName("게시글 본문 수정 - [실패 : 게시글 제목이 없는 경우]")
    @Test
    void givenArticle_whenChangeCaption_thenThrowPropertyValueException() {
        // given
        Article memberArticle = articleRepository.findById(givenMemberArticle1.getId()).orElseThrow(NoSuchElementException::new);
        Article anonymousArticle = articleRepository.findById(givenAnonymousArticle1.getId()).orElseThrow(NoSuchElementException::new);

        // when
        memberArticle.changeCaption(null);
        anonymousArticle.changeCaption(null);

        // then
        assertThatThrownBy(entityManager::flush)
                        .isInstanceOf(PropertyValueException.class);
    }


    /* 게시글 삭제 */
    @DisplayName("게시글 삭제 - [성공]")
    @Test
    void givenArticle_whenDelete_thenSuccess() {
        // given

        // when
        articleRepository.delete(givenMemberArticle1);
        articleRepository.delete(givenAnonymousArticle1);

        // then
        assertThat(articleRepository.findAll()).doesNotContain(givenMemberArticle1, givenAnonymousArticle1);
    }

    @DisplayName("id 가 일치하는 게시글 삭제 - [성공]")
    @Test
    void givenArticleId_whenDeleteById_thenSuccess() {
        // given

        // when
        articleRepository.deleteById(givenMemberArticle1.getId());
        articleRepository.deleteById(givenAnonymousArticle1.getId());

        // then
        assertThat(articleRepository.findAll()).doesNotContain(givenMemberArticle1, givenAnonymousArticle1);
    }
}
