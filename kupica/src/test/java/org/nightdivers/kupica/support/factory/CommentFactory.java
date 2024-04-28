package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_ANONYMOUS_COMMENT_CONTENT_1;
import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_ANONYMOUS_COMMENT_CONTENT_2;
import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_ANONYMOUS_COMMENT_CONTENT_3;
import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_MEMBER_COMMENT_CONTENT_1;
import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_MEMBER_COMMENT_CONTENT_2;
import static org.nightdivers.kupica.support.constant.CommentConstant.TEST_MEMBER_COMMENT_CONTENT_3;

import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.comment.Comment;
import org.nightdivers.kupica.domain.member.Member;

public class CommentFactory {
    public static Comment createTestMemberComment1(Member member, Article memberArticle) {
        return Comment.createMemberComment(
                TEST_MEMBER_COMMENT_CONTENT_1,
                member,
                memberArticle
        );
    }

    public static Comment createTestMemberComment2(Member member, Article memberArticle) {
        return Comment.createMemberComment(
                TEST_MEMBER_COMMENT_CONTENT_2,
                member,
                memberArticle
        );
    }

    public static Comment createTestMemberComment3(Member member, Article memberArticle) {
        return Comment.createMemberComment(
                TEST_MEMBER_COMMENT_CONTENT_3,
                member,
                memberArticle
        );
    }

    public static Comment createTestMemberReplyComment1(Comment parentComment, Member member, Article memberArticle) {
        return Comment.createMemberReplyComment(
                TEST_MEMBER_COMMENT_CONTENT_1,
                parentComment,
                member,
                memberArticle
        );
    }

    public static Comment createTestMemberReplyComment2(Comment parentComment, Member member, Article memberArticle) {
        return Comment.createMemberReplyComment(
                TEST_MEMBER_COMMENT_CONTENT_2,
                parentComment,
                member,
                memberArticle
        );
    }

    public static Comment createTestMemberReplyComment3(Comment parentComment, Member member, Article memberArticle) {
        return Comment.createMemberReplyComment(
                TEST_MEMBER_COMMENT_CONTENT_3,
                parentComment,
                member,
                memberArticle
        );
    }

    public static Comment createTestAnonymousComment1(AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousUserComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_1,
                anonymousUser,
                anonymousArticle
        );
    }

    public static Comment createTestAnonymousComment2(AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousUserComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_2,
                anonymousUser,
                anonymousArticle
        );
    }

    public static Comment createTestAnonymousComment3(AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousUserComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_3,
                anonymousUser,
                anonymousArticle
        );
    }

    public static Comment createTestAnonymousReplyComment1(Comment parentComment, AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousReplyComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_1,
                parentComment,
                anonymousUser,
                anonymousArticle
        );
    }

    public static Comment createTestAnonymousReplyComment2(Comment parentComment, AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousReplyComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_2,
                parentComment,
                anonymousUser,
                anonymousArticle
        );
    }

    public static Comment createTestAnonymousReplyComment3(Comment parentComment, AnonymousUser anonymousUser, Article anonymousArticle) {
        return Comment.createAnonymousReplyComment(
                TEST_ANONYMOUS_COMMENT_CONTENT_3,
                parentComment,
                anonymousUser,
                anonymousArticle
        );
    }
}
