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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.article.ArticleRepository;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
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
        initTestData();
    }

    @Nested
    class 전체_댓글을_조회할_시 {

        @Nested
        class 사진글_id_와_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndErasedFlagIsFalse(
                        givenAnonymousArticle1.getId());

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
        }

        @Nested
        class 사진글_id_와_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndErasedFlagIsFalse(
                        TEST_INVALID_ARTICLE_ID);

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 회원_id_와_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByMemberIdAndErasedFlagIsFalse(givenMember1.getId());

                assertThat(comments).contains(
                        givenMemberComment1,
                        givenMemberReplyComment1,
                        givenMemberReplyComment2,
                        givenMemberReplyComment3
                );
            }
        }

        @Nested
        class 회원_id_와_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByMemberIdAndErasedFlagIsFalse(TEST_INVALID_MEMBER_ID);

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 회원_nickname_과_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByMemberNicknameAndErasedFlagIsFalse(
                        givenMember1.getNickname());

                assertThat(comments).contains(
                        givenMemberComment1,
                        givenMemberReplyComment1,
                        givenMemberReplyComment2,
                        givenMemberReplyComment3
                );
            }
        }

        @Nested
        class 회원_nickname_과_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByMemberNicknameAndErasedFlagIsFalse(
                        TEST_INVALID_MEMBER_NICKNAME);

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 사진글_id_와_일치하며_회원_id_와도_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(
                        givenAnonymousArticle1.getId(),
                        givenMember1.getId()
                );

                assertThat(comments).contains(
                        givenMemberComment1,
                        givenMemberReplyComment1,
                        givenMemberReplyComment2,
                        givenMemberReplyComment3
                );
            }
        }

        @Nested
        class 사진글_id_와_일치하며_회원_id_와도_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(
                        TEST_INVALID_ARTICLE_ID,
                        givenMember1.getId()
                );

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 사진글_id_와_일치하며_회원_nickname_과도_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(
                        givenAnonymousArticle1.getId(),
                        givenMember1.getNickname()
                );

                assertThat(comments).contains(
                        givenMemberComment1,
                        givenMemberReplyComment1,
                        givenMemberReplyComment2,
                        givenMemberReplyComment3
                );
            }
        }

        @Nested
        class 사진글_id_와_일치하며_회원_nickname_과도_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(
                        givenAnonymousArticle1.getId(),
                        TEST_INVALID_MEMBER_NICKNAME
                );

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 익명_이용자의_nickname_과_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByAnonymousUserNicknameAndErasedFlagIsFalse(
                        givenAnonymousUser1.getNickname());

                assertThat(comments).contains(
                        givenAnonymousComment1,
                        givenAnonymousReplyComment1,
                        givenAnonymousReplyComment2,
                        givenAnonymousReplyComment3
                );
            }
        }

        @Nested
        class 익명_이용자의_nickname_과_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByAnonymousUserNicknameAndErasedFlagIsFalse(
                        TEST_INVALID_ANONYMOUS_USER_NICKNAME);

                assertThat(comments).isEmpty();
            }
        }

        @Nested
        class 사진글_id_와_일치하며_익명_이용자의_nickname_과도_일치하는_댓글이_있는_경우 {

            @Test
            void 해당되는_댓글_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(
                        givenAnonymousArticle1.getId(),
                        givenAnonymousUser1.getNickname()
                );

                assertThat(comments).contains(
                        givenAnonymousComment1,
                        givenAnonymousReplyComment1,
                        givenAnonymousReplyComment2,
                        givenAnonymousReplyComment3
                );
            }
        }

        @Nested
        class 사진글_id_와_일치하며_익명_이용자의_nickname_과도_일치하는_댓글이_없는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Comment> comments = commentRepository.findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(
                        TEST_INVALID_ARTICLE_ID,
                        givenAnonymousUser1.getNickname()
                );

                assertThat(comments).isEmpty();
            }
        }
    }

    private void initTestData() {
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
}
