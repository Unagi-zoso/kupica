CREATE TABLE IF NOT EXISTS member (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      nickname VARCHAR(18) NOT NULL,
                                      email_address VARCHAR(255) NOT NULL,
                                      user_role VARCHAR(255) NOT NULL,
                                      social_login_type VARCHAR(255) NOT NULL,
                                      created_datetime DATETIME(6),
                                      updated_datetime DATETIME(6),
                                      primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS article (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       author_name VARCHAR(18) NOT NULL,
                                       password VARCHAR(64),
                                       caption VARCHAR(600),
                                       member_id BIGINT,
                                       created_datetime DATETIME(6),
                                       updated_datetime DATETIME(6),
                                       primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS comment (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       author_name VARCHAR(18) NOT NULL,
                                       content VARCHAR(600),
                                       reply_target_comment_id BIGINT,
                                       password VARCHAR(64),
                                       member_id BIGINT,
                                       article_id BIGINT NOT NULL,
                                       created_datetime DATETIME(6),
                                       updated_datetime DATETIME(6),
                                       primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS article_like (
                                            id BIGINT NOT NULL AUTO_INCREMENT,
                                            ip_address VARCHAR(20),
                                            member_id BIGINT,
                                            article_id BIGINT NOT NULL,
                                            created_datetime DATETIME(6),
                                            updated_datetime DATETIME(6),
                                            primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS photo_download_source (
                                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                                     photo_resolution VARCHAR(18) NOT NULL,
                                                     file_source VARCHAR(600) NOT NULL,
                                                     file_byte_size BIGINT NOT NULL,
                                                     download_count BIGINT NOT NULL,
                                                     article_id BIGINT NOT NULL,
                                                     created_datetime DATETIME(6),
                                                     updated_datetime DATETIME(6),
                                                     primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS hashtag (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       tag_name VARCHAR(26) NOT NULL,
                                       created_datetime DATETIME(6),
                                       updated_datetime DATETIME(6),
                                       primary key (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS article_hashtag (
                                               id BIGINT NOT NULL AUTO_INCREMENT,
                                               article_id BIGINT NOT NULL,
                                               hashtag_id BIGINT NOT NULL,
                                               created_datetime DATETIME(6),
                                               updated_datetime DATETIME(6),
                                               primary key (id)
) engine=InnoDB;

ALTER TABLE member
    ADD CONSTRAINT UK_hh9kg6jti4n1eoiertn2k6qsc UNIQUE (nickname);

ALTER TABLE member
    ADD CONSTRAINT UK_cgclm058f54tosrpko2h125op UNIQUE (email_address);

ALTER TABLE article_like
    ADD CONSTRAINT article_like_member_id_article_id_unique UNIQUE (ip_address, member_id, article_id);

ALTER TABLE photo_download_source
    ADD CONSTRAINT UK_ntge09558j807a9e33r0gvukq UNIQUE (file_source);

ALTER TABLE hashtag
    ADD CONSTRAINT UK_3f3xt16e9nburcj5lolwpgnvh UNIQUE (tag_name);
