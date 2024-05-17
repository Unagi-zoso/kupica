package org.nightdivers.kupica.support.factory;

import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.constant.ArticleConstant;

public class ArticleFactory {

    public static Article createTestMemberArticle1(Member member) {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_1_CAPTION,
                member
        );
    }

    public static Article createTestMemberArticle2(Member member) {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_2_CAPTION,
                member
        );
    }

    public static Article createTestMemberArticle3(Member member) {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_3_CAPTION,
                member
        );
    }

    public static Article createTestMemberArticle4(Member member) {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_4_CAPTION,
                member
        );
    }

    public static Article createCustomMemberArticle(String caption, Member member) {
        return Article.createMemberArticle(
                caption,
                member
        );
    }

    public static Article createTestAnonymousArticle1(AnonymousUser anonymousUser) {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_1_CAPTION,
                anonymousUser
        );
    }

    public static Article createTestAnonymousArticle2(AnonymousUser anonymousUser) {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_2_CAPTION,
                anonymousUser
        );
    }

    public static Article createTestAnonymousArticle3(AnonymousUser anonymousUser) {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_3_CAPTION,
                anonymousUser
        );
    }

    public static Article createTestAnonymousArticle4(AnonymousUser anonymousUser) {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_4_CAPTION,
                anonymousUser
        );
    }

    public static Article createCustomAnonymousArticle(
            String caption,
            AnonymousUser anonymousUser
    ) {
        return Article.createAnonymousArticle(
                caption,
                anonymousUser
        );
    }
}
