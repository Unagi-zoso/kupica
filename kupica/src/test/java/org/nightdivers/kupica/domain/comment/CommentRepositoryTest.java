package org.nightdivers.kupica.domain.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_ANONYMOUS_ARTICLE_1_CAPTION;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_INVALID_ARTICLE_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createTestAnonymousUser1;
import static org.nightdivers.kupica.support.factory.ArticleFactory.createCustomAnonymousArticle;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestAnonymousComment1;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestAnonymousReplyComment1;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestAnonymousReplyComment2;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestAnonymousReplyComment3;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestMemberComment1;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestMemberReplyComment1;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestMemberReplyComment2;
import static org.nightdivers.kupica.support.factory.CommentFactory.createTestMemberReplyComment3;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;

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

@RequiredArgsConstructor
@RepositoryTest
class CommentRepositoryTest {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;
    private final ArticleRepository articleRepository;

    Member givenMember1;

    AnonymousUser givenAnonymousUser1;

    Article givenAnonymousArticle1;

    Comment givenMemberComment1;
    Comment givenAnonymousComment1;

    Comment givenMemberReplyComment1;
    Comment givenMemberReplyComment2;
    Comment givenMemberReplyComment3;

    Comment givenAnonymousReplyComment1;
    Comment givenAnonymousReplyComment2;
    Comment givenAnonymousReplyComment3;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(createTestMember1());
        givenAnonymousUser1 = anonymousUserRepository.save(createTestAnonymousUser1());
        givenAnonymousArticle1 = articleRepository.save(
                createCustomAnonymousArticle(
                        TEST_ANONYMOUS_ARTICLE_1_CAPTION,
                        givenAnonymousUser1
                ));
        givenMemberComment1 = commentRepository.save(
                createTestMemberComment1(givenMember1, givenAnonymousArticle1));
        givenAnonymousComment1 = commentRepository.save(
                createTestAnonymousComment1(givenAnonymousUser1, givenAnonymousArticle1));
        givenMemberReplyComment1 = commentRepository.save(
                createTestMemberReplyComment1(givenMemberComment1, givenMember1,
                                              givenAnonymousArticle1
                ));
        givenMemberReplyComment2 = commentRepository.save(
                createTestMemberReplyComment2(givenMemberComment1, givenMember1,
                                              givenAnonymousArticle1
                ));
        givenMemberReplyComment3 = commentRepository.save(
                createTestMemberReplyComment3(givenMemberComment1, givenMember1,
                                              givenAnonymousArticle1
                ));
        givenAnonymousReplyComment1 = commentRepository.save(
                createTestAnonymousReplyComment1(givenAnonymousComment1, givenAnonymousUser1,
                                                 givenAnonymousArticle1
                ));
        givenAnonymousReplyComment2 = commentRepository.save(
                createTestAnonymousReplyComment2(givenAnonymousComment1, givenAnonymousUser1,
                                                 givenAnonymousArticle1
                ));
        givenAnonymousReplyComment3 = commentRepository.save(
                createTestAnonymousReplyComment3(givenAnonymousComment1, givenAnonymousUser1,
                                                 givenAnonymousArticle1
                ));
    }

    /* TARGET : 댓글 조회 테스트 */
    @DisplayName("게시글 id 와 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenArticleId_whenFindAllByArticleId_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId());

        // then
        assertThat(comments).contains(
                givenMemberComment1,
                givenAnonymousComment1,
                givenMemberReplyComment1,
                givenMemberReplyComment2,
                givenMemberReplyComment3,
                givenAnonymousReplyComment1,
                givenAnonymousReplyComment2,
                givenAnonymousReplyComment3
        );
    }

    @DisplayName("member id 와 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenMemberId_whenFindAllByMemberId_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByMemberIdAndErasedFlagIsFalse(givenMember1.getId());

        // then
        assertThat(comments).contains(
                givenMemberComment1,
                givenMemberReplyComment1,
                givenMemberReplyComment2,
                givenMemberReplyComment3
        );
    }

    @DisplayName("member id 와 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 member id]")
    @Test
    void givenInvalidMemberId_whenFindAllByMemberId_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByMemberIdAndErasedFlagIsFalse(TEST_INVALID_MEMBER_ID);

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("member nickname 과 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenMemberNickname_whenFindAllByMemberNickname_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByMemberNicknameAndErasedFlagIsFalse(
                givenMember1.getNickname());

        // then
        assertThat(comments).contains(
                givenMemberComment1,
                givenMemberReplyComment1,
                givenMemberReplyComment2,
                givenMemberReplyComment3
        );
    }

    @DisplayName("member nickname 과 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 member nickname]")
    @Test
    void givenInvalidMemberNickname_whenFindAllByMemberNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByMemberNicknameAndErasedFlagIsFalse(
                TEST_INVALID_MEMBER_NICKNAME);

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("article id 와 일치하며 member nickname 과도 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenArticleIdAndMemberNickname_whenFindAllByArticleIdAndMemberNickname_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(),
                givenMember1.getNickname()
        );

        // then
        assertThat(comments).contains(
                givenMemberComment1,
                givenMemberReplyComment1,
                givenMemberReplyComment2,
                givenMemberReplyComment3
        );
    }

    @DisplayName("article id 와 일치하며 member nickname 과도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 article id]")
    @Test
    void givenInvalidArticleIdAndMemberNickname_whenFindAllByArticleIdAndMemberNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(
                TEST_INVALID_ARTICLE_ID,
                givenMember1.getNickname()
        );

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("article id 와 일치하며 member nickname 과도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 member nickname]")
    @Test
    void givenArticleIdAndInvalidMemberNickname_whenFindAllByArticleIdAndMemberNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(),
                TEST_INVALID_MEMBER_NICKNAME
        );

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("anonymous nickname 과 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenAnonymousUserNickname_whenFindAllByAnonymousUserNickname_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByAnonymousUserNicknameAndErasedFlagIsFalse(
                givenAnonymousUser1.getNickname());

        // then
        assertThat(comments).contains(
                givenAnonymousComment1,
                givenAnonymousReplyComment1,
                givenAnonymousReplyComment2,
                givenAnonymousReplyComment3
        );
    }

    @DisplayName("anonymous nickname 과 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 anonymous nickname]")
    @Test
    void givenInvalidAnonymousUserNickname_whenFindAllByAnonymousUserNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByAnonymousUserNicknameAndErasedFlagIsFalse(
                TEST_INVALID_ANONYMOUS_USER_NICKNAME);

        // then
        assertThat(comments).isEmpty();
    }


    @DisplayName("article id 와 일치하며 member id 와도 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenArticleIdAndMemberId_whenFindAllByArticleIdAndMemberId_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(),
                givenMember1.getId()
        );

        // then
        assertThat(comments).contains(
                givenMemberComment1,
                givenMemberReplyComment1,
                givenMemberReplyComment2,
                givenMemberReplyComment3
        );
    }

    @DisplayName("article id 와 일치하며 member id 와도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 article id]")
    @Test
    void givenInvalidArticleIdAndMemberId_whenFindAllByArticleIdAndMemberId_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(
                TEST_INVALID_ARTICLE_ID,
                givenMember1.getId()
        );

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("article id 와 일치하며 member id 와도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 member id]")
    @Test
    void givenArticleIdAndInvalidMemberId_whenFindAllByArticleIdAndMemberId_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(),
                TEST_INVALID_MEMBER_ID
        );

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("article id 와 일치하며 anonymous nickname 과도 일치하는 댓글 전체 조회 - [성공]")
    @Test
    void givenArticleIdAndAnonymousUserNickname_whenFindAllByArticleIdAndAnonymousUserNickname_thenCommentList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(), givenAnonymousUser1.getNickname());

        // then
        assertThat(comments).contains(
                givenAnonymousComment1,
                givenAnonymousReplyComment1,
                givenAnonymousReplyComment2,
                givenAnonymousReplyComment3
        );
    }

    @DisplayName("article id 와 일치하며 anonymous nickname 과도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 article id]")
    @Test
    void givenInvalidArticleIdAndAnonymousUserNickname_whenFindAllByArticleIdAndAnonymousUserNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(
                TEST_INVALID_ARTICLE_ID,
                givenAnonymousUser1.getNickname()
        );

        // then
        assertThat(comments).isEmpty();
    }

    @DisplayName("article id 와 일치하며 anonymous nickname 과도 일치하는 댓글 전체 조회 - [실패 : 존재하지 않는 anonymous nickname]")
    @Test
    void givenArticleIdAndInvalidAnonymousUserNickname_whenFindAllByArticleIdAndAnonymousUserNickname_thenEmptyList() {
        // given

        // when
        List<Comment> comments = commentRepository.findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(
                givenAnonymousArticle1.getId(), TEST_INVALID_ANONYMOUS_USER_NICKNAME);

        // then
        assertThat(comments).isEmpty();
    }
}
