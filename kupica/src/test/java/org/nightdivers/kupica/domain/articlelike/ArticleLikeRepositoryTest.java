package org.nightdivers.kupica.domain.articlelike;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.domain.articlelike.ArticleLike.createAnonymousArticleLike;
import static org.nightdivers.kupica.domain.articlelike.ArticleLike.createMemberArticleLike;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_IP_LIST;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_INVALID_ARTICLE_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createTestAnonymousUser1;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createTestAnonymousUser2;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createTestAnonymousUser3;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle1;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle2;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createTestMemberArticle3;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember2;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember3;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.article.ArticleRepository;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class ArticleLikeRepositoryTest {

    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;
    private final ArticleRepository articleRepository;

    Member givenMember1;
    Member givenMember2;

    AnonymousUser givenAnonymousUser1;
    AnonymousUser givenAnonymousUser2;

    Article givenArticle1;
    Article givenArticle2;
    Article givenArticle3;

    ArticleLike givenArticleLike1;
    ArticleLike givenArticleLike2;
    ArticleLike givenArticleLike3;
    ArticleLike givenArticleLike4;
    ArticleLike givenArticleLike5;
    ArticleLike givenArticleLike6;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(createTestMember1());
        givenMember2 = memberRepository.save(createTestMember2());

        givenAnonymousUser1 = anonymousUserRepository.save(createTestAnonymousUser1());
        givenAnonymousUser2 = anonymousUserRepository.save(createTestAnonymousUser2());

        givenArticle1 = articleRepository.save(createTestMemberArticle1());
        givenArticle2 = articleRepository.save(createTestMemberArticle2());
        givenArticle3 = articleRepository.save(createTestMemberArticle3());

        givenArticleLike1 = articleLikeRepository.save(
                createMemberArticleLike(givenMember1, givenArticle1));
        givenArticleLike2 = articleLikeRepository.save(
                createMemberArticleLike(givenMember1, givenArticle2));
        givenArticleLike3 = articleLikeRepository.save(
                createMemberArticleLike(givenMember2, givenArticle1));
        givenArticleLike5 = articleLikeRepository.save(
                createAnonymousArticleLike(givenAnonymousUser1.getIpAddress(), givenArticle1));
        givenArticleLike4 = articleLikeRepository.save(
                createAnonymousArticleLike(givenAnonymousUser2.getIpAddress(), givenArticle2));
        givenArticleLike6 = articleLikeRepository.save(
                createAnonymousArticleLike(givenAnonymousUser1.getIpAddress(), givenArticle2));
    }

    /* 좋아요 조회 */
    @DisplayName("member id 와 일치하는 좋아요 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberId_thenReturnsArticleLikeList() {
        // given

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByMemberId(
                givenMember1.getId());

        // then
        assertThat(articleLikeList).hasSize(2);
    }

    @DisplayName("member id 와 일치하는 좋아요 조회 - [실패 : 좋아요 없음]")
    @Test
    void givenValidMemberId_whenFindAllByMemberId_thenReturnsEmptyList() {
        // given
        Long memberId = memberRepository.save(createTestMember3()).getId();

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByMemberId(memberId);

        // then
        assertThat(articleLikeList).isEmpty();
    }

    @DisplayName("member id 와 일치하는 좋아요 조회 - [실패 : member id 없음]")
    @Test
    void givenInvalidMemberId_whenFindAllByMemberId_thenReturnsEmptyList() {
        // given

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByMemberId(
                TEST_INVALID_MEMBER_ID);

        // then
        assertThat(articleLikeList).isEmpty();
    }

    @DisplayName("article id 와 일치하는 좋아요 조회 - [성공]")
    @Test
    void givenArticleId_whenFindAllByArticleId_thenReturnsArticleLikeList() {
        // given

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByArticleId(
                givenArticle1.getId());

        // then
        assertThat(articleLikeList).hasSize(3);
    }

    @DisplayName("article id 와 일치하는 좋아요 조회 - [실패 : 좋아요 없음]")
    @Test
    void givenValidArticleId_whenFindAllByArticleId_thenReturnsEmptyList() {
        // given
        Long articleId = articleRepository.save(createTestMemberArticle3()).getId();

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByArticleId(articleId);

        // then
        assertThat(articleLikeList).isEmpty();
    }

    @DisplayName("article id 와 일치하는 좋아요 조회 - [실패 : article id 없음]")
    @Test
    void givenInvalidArticleId_whenFindAllByArticleId_thenReturnsEmptyList() {
        // given

        // when
        List<ArticleLike> articleLikeList = articleLikeRepository.findAllByArticleId(
                TEST_INVALID_ARTICLE_ID);

        // then
        assertThat(articleLikeList).isEmpty();
    }

    @DisplayName("좋아요 수가 많은 순서로 조회 - [성공]")
    @Test
    void whenFindArticleIdOrderByCountDesc_thenReturnsArticleLikeList() {
        // given
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle1)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 1)
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle2)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 2)
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle3)));

        // when
        List<ArticleLike> actualArticleLikes = articleLikeRepository.findArticleIdOrderByCountDesc(
                        PageRequest.of(0, 10))
                .getContent();
        // then
        assertAll(
                () -> assertThat(actualArticleLikes.getFirst().getArticle().getId()).isEqualTo(
                        givenArticle1.getId()),
                () -> assertThat(actualArticleLikes.get(1).getArticle().getId()).isEqualTo(
                        givenArticle2.getId()),
                () -> assertThat(actualArticleLikes.get(2).getArticle().getId()).isEqualTo(
                        givenArticle3.getId())
        );
    }

    @DisplayName("좋아요 수가 적은 순서로 조회 - [성공]")
    @Test
    void whenFindArticleIdOrderByCountAsc_thenReturnsArticleLikeList() {
        // given
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle1)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 1)
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle2)));
        TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 2)
                .forEach(ip -> articleLikeRepository.save(
                        createAnonymousArticleLike(ip, givenArticle3)));

        // when
        List<ArticleLike> actualArticleLikes = articleLikeRepository.findArticleIdOrderByCountAsc(
                        PageRequest.of(0, 10))
                .getContent();

        // then
        assertAll(
                () -> assertThat(actualArticleLikes.getLast().getArticle().getId()).isEqualTo(
                        givenArticle1.getId()),
                () -> assertThat(actualArticleLikes.get(actualArticleLikes.size() - 2).getArticle()
                                         .getId()).isEqualTo(
                        givenArticle2.getId()),
                () -> assertThat(actualArticleLikes.get(actualArticleLikes.size() - 3).getArticle()
                                         .getId()).isEqualTo(
                        givenArticle3.getId())
        );
    }


    /* 좋아요 생성 */
    @DisplayName("member 좋아요 생성 - [성공]")
    @Test
    void givenMember_whenCreateMemberArticleLike_thenReturnsArticleLike() {
        // given
        Member member = memberRepository.save(createTestMember3());

        // when
        ArticleLike actualArticleLike = articleLikeRepository.save(
                createMemberArticleLike(member, givenArticle1));

        // then
        assertThat(actualArticleLike).isNotNull();
    }

    @DisplayName("anonymous user 좋아요 생성 - [성공]")
    @Test
    void givenAnonymousUser_whenCreateAnonymousArticleLike_thenReturnsArticleLike() {
        // given
        AnonymousUser anonymousUser = anonymousUserRepository.save(createTestAnonymousUser3());

        // when
        ArticleLike actualArticleLike = articleLikeRepository.save(
                createAnonymousArticleLike(anonymousUser.getIpAddress(), givenArticle1));

        // then
        assertThat(actualArticleLike).isNotNull();
    }


    /* 좋아요 삭제 */
    @DisplayName("member 좋아요 삭제 - [성공]")
    @Test
    void givenMemberArticleLike_whenDelete_thenReturnsVoid() {
        // given
        ArticleLike articleLike = articleLikeRepository.save(
                createMemberArticleLike(givenMember1, givenArticle2));

        // when
        articleLikeRepository.delete(articleLike);

        // then
        assertThat(articleLikeRepository.findById(articleLike.getId())).isEmpty();
    }


    /* 좋아요 수 조회 */
    @DisplayName("article id 와 일치하는 좋아요 수 조회 - [성공]")
    @Test
    void givenArticleId_whenCountByArticleId_thenReturnsCount() {
        // given

        // when
        Long count = articleLikeRepository.countByArticleId(givenArticle1.getId());

        // then
        assertThat(count).isEqualTo(3);
    }

    @DisplayName("article id 와 일치하는 좋아요 수 조회 - [실패 : 유효하지 않은 article id]")
    @Test
    void givenInvalidArticleId_whenCountByArticleId_thenReturnsZero() {
        // given

        // when
        Long count = articleLikeRepository.countByArticleId(TEST_INVALID_ARTICLE_ID);

        // then
        assertThat(count).isZero();
    }
}
