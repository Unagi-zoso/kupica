package org.nightdivers.kupica.support.factory;

import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.constant.ArticleConstant;

public class ArticleFactory {

    public static Article createTestMemberArticle1() {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_1_CAPTION,
                null
        );
    }

    public static Article createTestMemberArticle2() {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_2_CAPTION,
                null
        );
    }

    public static Article createTestMemberArticle3() {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_3_CAPTION,
                null
        );
    }

    public static Article createTestMemberArticle4() {
        return Article.createMemberArticle(
                ArticleConstant.TEST_MEMBER_ARTICLE_4_CAPTION,
                null
        );
    }

    public static Article createCustomMemberArticle(String caption, Member member) {
        return Article.createMemberArticle(
                caption,
                member
        );
    }

    public static Article createTestAnonymousArticle1() {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_1_CAPTION,
                null
        );
    }

    public static Article createTestAnonymousArticle2() {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_2_CAPTION,
                null
        );
    }

    public static Article createTestAnonymousArticle3() {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_3_CAPTION,
                null
        );
    }

    public static Article createTestAnonymousArticle4() {
        return Article.createAnonymousArticle(
                ArticleConstant.TEST_ANONYMOUS_ARTICLE_4_CAPTION,
                null
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
