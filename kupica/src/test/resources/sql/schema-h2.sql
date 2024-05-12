CREATE TABLE member
(
    id                NUMERIC(19, 0) NOT NULL,
    nickname          VARCHAR2(18)   NOT NULL UNIQUE,
    email_address     VARCHAR2(255)  NOT NULL UNIQUE,
    user_role         VARCHAR2(255)  NOT NULL CHECK (user_role IN ('ADMIN', 'MEMBER', 'ANONYMOUS')),
    social_login_type VARCHAR2(20)   NOT NULL CHECK (social_login_type IN ('KAKAO')),
    erased_flag       BOOLEAN        NOT NULL,
    created_datetime  TIMESTAMP(6)   NOT NULL,
    updated_datetime  TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE member_sequence START WITH 11 INCREMENT BY 1;

ALTER TABLE member
    ADD CONSTRAINT uk_nickname_on_member UNIQUE (nickname);
ALTER TABLE member
    ADD CONSTRAINT uk_email_address_on_member UNIQUE (email_address);

CREATE TABLE anonymous_user
(
    id               NUMERIC(19, 0) NOT NULL,
    nickname         VARCHAR2(18)   NOT NULL,
    password         VARCHAR2(64)   NOT NULL,
    ip_address       VARCHAR2(20)   NOT NULL,
    user_role        VARCHAR2(255)  NOT NULL CHECK (user_role IN ('ADMIN', 'MEMBER', 'ANONYMOUS')),
    created_datetime TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence anonymous_user_sequence START WITH 8 INCREMENT BY 1;

CREATE TABLE article
(
    id                NUMERIC(19, 0) NOT NULL,
    caption           VARCHAR2(600)  NOT NULL,
    anonymous_user_id NUMERIC(19, 0),
    member_id         NUMERIC(19, 0),
    login_flag        BOOLEAN        NOT NULL,
    erased_flag       BOOLEAN        NOT NULL,
    created_datetime  TIMESTAMP(6)   NOT NULL,
    updated_datetime  TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence article_sequence START WITH 15 INCREMENT BY 1;

CREATE TABLE article_comment
(
    id                      NUMERIC(19, 0) NOT NULL,
    content                 VARCHAR2(600)  NOT NULL,
    reply_target_comment_id NUMERIC(19, 0),
    anonymous_user_id       NUMERIC(19, 0),
    member_id               NUMERIC(19, 0),
    article_id              NUMERIC(19, 0) NOT NULL,
    login_flag              BOOLEAN        NOT NULL,
    erased_flag             BOOLEAN        NOT NULL,
    created_datetime        TIMESTAMP(6)   NOT NULL,
    updated_datetime        TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence article_comment_sequence START WITH 31 INCREMENT BY 1;

CREATE TABLE article_like
(
    id               NUMERIC(19, 0) NOT NULL,
    ip_address       VARCHAR2(20),
    member_id        NUMERIC(19, 0),
    article_id       NUMERIC(19, 0) NOT NULL,
    login_flag       BOOLEAN        NOT NULL,
    erased_flag      BOOLEAN        NOT NULL,
    created_datetime TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT article_like_member_id_article_id_unique UNIQUE (ip_address, member_id, article_id)
);

create sequence article_like_sequence START WITH 21 INCREMENT BY 1;

ALTER TABLE article_like
    ADD CONSTRAINT uk_ip_address_member_id_article_id UNIQUE (ip_address, member_id, article_id);

CREATE TABLE photo_file_source
(
    id               NUMERIC(19, 0) NOT NULL,
    photo_resolution VARCHAR2(18)   NOT NULL,
    file_source      VARCHAR2(600)  NOT NULL UNIQUE,
    file_byte_size   NUMERIC(19, 0) NOT NULL,
    download_count   NUMERIC(19, 0) NOT NULL,
    article_id       NUMERIC(19, 0) NOT NULL,
    erased_flag      BOOLEAN        NOT NULL,
    created_datetime TIMESTAMP(6)   NOT NULL,
    updated_datetime TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence photo_file_source_sequence START WITH 21 INCREMENT BY 1;

ALTER TABLE photo_file_source
    ADD CONSTRAINT uk_file_source UNIQUE (file_source);

CREATE TABLE hashtag
(
    id               NUMERIC(19, 0) NOT NULL,
    tag_name         VARCHAR2(26)   NOT NULL UNIQUE,
    created_datetime TIMESTAMP(6)   NOT NULL,
    updated_datetime TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence hashtag_sequence START WITH 15 INCREMENT BY 1;

ALTER TABLE hashtag
    ADD CONSTRAINT uk_tag_name UNIQUE (tag_name);

CREATE TABLE article_hashtag
(
    id               NUMERIC(19, 0) NOT NULL,
    article_id       NUMERIC(19, 0) NOT NULL,
    hashtag_id       NUMERIC(19, 0) NOT NULL,
    erased_flag      BOOLEAN        NOT NULL,
    created_datetime TIMESTAMP(6)   NOT NULL,
    updated_datetime TIMESTAMP(6)   NOT NULL,
    PRIMARY KEY (id)
);

create sequence article_hashtag_sequence START WITH 15 INCREMENT BY 1;

