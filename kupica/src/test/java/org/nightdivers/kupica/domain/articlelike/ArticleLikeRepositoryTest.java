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
import org.springframework.data.domain.PageRequest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@RepositoryTest
class ArticleLikeRepositoryTest {

    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;
    private final AnonymousUserRepository anonymousUserRepository;
    private final ArticleRepository articleRepository;

    Member givenMember1;
    Member givenMember2;
    Member noArticleMember;

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
        initTestData();
    }

    @Nested
    class 전체_좋아요를_조회_시 {

        @Nested
        class 회원_id_와_일치하는_좋아요가_있는_경우 {

            @Test
            void 해당되는_좋아요_목록을_반환한다() {
                List<ArticleLike> actual = articleLikeRepository.findAllByMemberIdAndErasedFlagIsFalse(
                        givenMember1.getId());

                assertThat(actual).contains(givenArticleLike1, givenArticleLike2);
            }
        }

        @Nested
        class 회원_id_와_일치하는_좋아요가_없는_경우 {

            @Test
            void 빈_리스트를_반환한다() {
                List<ArticleLike> actual = articleLikeRepository.findAllByMemberIdAndErasedFlagIsFalse(
                        noArticleMember.getId());

                assertThat(actual).isEmpty();
            }
        }

        @Nested
        class 사진글_id_와_일치하는_좋아요가_있는_경우 {

            @Test
            void 해당되는_좋아요_목록을_반환한다() {
                List<ArticleLike> actual = articleLikeRepository.findAllByArticleIdAndErasedFlagIsFalse(
                        givenArticle1.getId());

                assertThat(actual).contains(givenArticleLike1, givenArticleLike3, givenArticleLike5);
            }
        }

        @Nested
        class 사진글_id_와_일치하는_좋아요가_없는_경우 {

            @Test
            void 빈_리스트를_반환한다() {
                List<ArticleLike> actual = articleLikeRepository.findAllByArticleIdAndErasedFlagIsFalse(
                        givenArticle3.getId());

                assertThat(actual).isEmpty();
            }
        }

        @Nested
        class 좋아요_수가_많은_순서로_조회할_경우 {

            @BeforeEach
            void context() {
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle1)));
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 1)
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle2)));
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 2)
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle3)));
            }

            @Test
            void 해당되는_좋아요_목록을_반환한다() {
                List<ArticleLike> actualArticleLikes = articleLikeRepository.findArticleIdOrderByCountDesc(
                        PageRequest.of(0, 10)).getContent();

                assertAll(
                        () -> assertThat(actualArticleLikes.getFirst().getArticle().getId()).isEqualTo(
                                givenArticle1.getId()),
                        () -> assertThat(actualArticleLikes.get(1).getArticle().getId()).isEqualTo(
                                givenArticle2.getId()),
                        () -> assertThat(actualArticleLikes.get(2).getArticle().getId()).isEqualTo(
                                givenArticle3.getId())
                );
            }
        }

        @Nested
        class 좋아요_수가_적은_순서로_조회할_경우 {

            @BeforeEach
            void context() {
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size())
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle1)));
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 1)
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle2)));
                TEST_ANONYMOUS_IP_LIST.subList(0, TEST_ANONYMOUS_IP_LIST.size() - 2)
                        .forEach(ip -> articleLikeRepository.save(
                                createAnonymousArticleLike(ip, givenArticle3)));
            }

            @Test
            void 해당되는_좋아요_목록을_반환한다() {
                List<ArticleLike> actualArticleLikes = articleLikeRepository.findArticleIdOrderByCountAsc(
                        PageRequest.of(0, 10)).getContent();

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
        }
    }

    @Nested
    class 좋아요_수를_조회할_시 {

            @Nested
            class 사진글_id_와_일치하는_좋아요_수를_조회할_경우 {

                @Test
                void 해당되는_좋아요_수를_반환한다() {
                    Long actual = articleLikeRepository.countByArticleIdAndErasedFlagIsFalse(
                            givenArticle1.getId());

                    assertThat(actual).isEqualTo(3);
                }
            }

            @Nested
            class 유효하지_않은_사진글_id_와_일치하는_좋아요_수를_조회할_경우 {

                @Test
                void 좋아요_수_0_을_반환한다() {
                    Long actual = articleLikeRepository.countByArticleIdAndErasedFlagIsFalse(
                            TEST_INVALID_ARTICLE_ID);

                    assertThat(actual).isZero();
                }
            }
    }

    private void initTestData() {
        givenMember1 = memberRepository.save(createTestMember1());
        givenMember2 = memberRepository.save(createTestMember2());
        noArticleMember = memberRepository.save(createTestMember3());

        givenAnonymousUser1 = anonymousUserRepository.save(createTestAnonymousUser1());
        givenAnonymousUser2 = anonymousUserRepository.save(createTestAnonymousUser2());

        givenArticle1 = articleRepository.save(createTestMemberArticle1(givenMember1));
        givenArticle2 = articleRepository.save(createTestMemberArticle2(givenMember1));
        givenArticle3 = articleRepository.save(createTestMemberArticle3(givenMember2));

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
}
