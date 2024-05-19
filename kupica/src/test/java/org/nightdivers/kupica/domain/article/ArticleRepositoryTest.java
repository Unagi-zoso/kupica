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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.MemberFactory;
import org.springframework.data.domain.PageRequest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@RepositoryTest
class ArticleRepositoryTest {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;

    Member givenMember1;

    AnonymousUser givenAnonymousUser1;

    int givenTestDataSetSize;

    Article givenMemberArticle1;
    Article givenMemberArticle2;
    Article givenMemberArticle3;

    Article givenAnonymousArticle1;
    Article givenAnonymousArticle2;
    Article givenAnonymousArticle3;

    @BeforeEach
    void setUp() {
        initTestData();
    }

    @Nested
    class 사진글_id_과_일치하는_사진글을_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_사진글을_반환한다() {
                Article actual = articleRepository.findByIdAndErasedFlagIsFalse(givenMemberArticle1.getId())
                        .orElseThrow(NoSuchElementException::new);

                assertThat(actual).isEqualTo(givenMemberArticle1);
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void NoSuchElementException_예외를_발생시킨다() {
                assertAll(
                        () -> assertThatThrownBy(() -> articleRepository.findByIdAndErasedFlagIsFalse(TEST_INVALID_ARTICLE_ID)
                                .orElseThrow(NoSuchElementException::new))
                                .isInstanceOf(NoSuchElementException.class)
                );
            }
        }
    }

    @Nested
    class 최근_등록된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 최근_등록된_순서대로_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findAllByErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize)).getContent();

                assertThat(actualArticles.subList(0, givenTestDataSetSize)).containsExactly(
                        givenAnonymousArticle3,
                        givenAnonymousArticle2, givenAnonymousArticle1,
                        givenMemberArticle3, givenMemberArticle2, givenMemberArticle1
                );
            }
        }
    }

    @Nested
    class 등록된지_오래된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 등록된지_오래된_순서대로_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findAllByErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, 100)).getContent();

                assertThat(actualArticles.subList(
                        actualArticles.size() - givenTestDataSetSize,
                        actualArticles.size()
                )).containsExactly(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                                   givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
                );
            }
        }
    }

    @Nested
    class 회원_id_와_일치하며_최근_등록된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원의_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenMemberArticle1.getMember().getId()
                ).getContent();

                assertThat(actualArticles).containsExactly(
                        givenMemberArticle3, givenMemberArticle2, givenMemberArticle1
                );
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_사진글_목록을_반환한다() {
                assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        TEST_INVALID_MEMBER_ID
                )).isEmpty();
            }
        }
    }

    @Nested
    class 회원_id_와_일치하며_등록된지_오래된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원의_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenMemberArticle1.getMember().getId()
                ).getContent();

                assertThat(actualArticles).containsExactly(
                        givenMemberArticle1, givenMemberArticle2, givenMemberArticle3
                );
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_사진글_목록을_반환한다() {
                assertThat(articleRepository.findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        TEST_INVALID_MEMBER_ID
                )).isEmpty();
            }
        }
    }

    @Nested
    class 익명_이용자_nickname_과_일치하며_최근_등록된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_익명_이용자의_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenAnonymousUser1.getNickname()
                ).getContent();

                assertThat(actualArticles).containsExactly(
                        givenAnonymousArticle3, givenAnonymousArticle2, givenAnonymousArticle1
                );
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_사진글_목록을_반환한다() {
                assertThat(articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
                        PageRequest.of(0, givenTestDataSetSize),
                        TEST_INVALID_ANONYMOUS_USER_NICKNAME
                )).isEmpty();
            }
        }
    }

    @Nested
    class 익명_이용자_nickname_과_일치하며_등록된지_오래된_순서대로_사진글_목록을_조회할_경우 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_익명_이용자의_사진글_목록을_반환한다() {
                List<Article> actualArticles = articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        givenAnonymousUser1.getNickname()
                ).getContent();

                assertThat(actualArticles).containsExactly(
                        givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
                );
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_사진글_목록을_반환한다() {
                assertThat(articleRepository.findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
                        PageRequest.of(0, givenTestDataSetSize),
                        TEST_INVALID_ANONYMOUS_USER_NICKNAME
                )).isEmpty();
            }
        }
    }

    private void initTestData() {
        givenMember1 = memberRepository.save(MemberFactory.createTestMember1());

        givenMemberArticle1 = createTestMemberArticle1(givenMember1);
        givenMemberArticle2 = createTestMemberArticle2(givenMember1);
        givenMemberArticle3 = createTestMemberArticle3(givenMember1);

        givenAnonymousUser1 = anonymousUserRepository.save(
                createTestAnonymousUser1());

        givenAnonymousArticle1 = createTestAnonymousArticle1(givenAnonymousUser1);
        givenAnonymousArticle2 = createTestAnonymousArticle2(givenAnonymousUser1);
        givenAnonymousArticle3 = createTestAnonymousArticle3(givenAnonymousUser1);

        givenTestDataSetSize = articleRepository.saveAll(
                List.of(givenMemberArticle1, givenMemberArticle2, givenMemberArticle3,
                        givenAnonymousArticle1, givenAnonymousArticle2, givenAnonymousArticle3
                )).size();
    }
}
