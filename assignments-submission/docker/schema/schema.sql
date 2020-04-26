CREATE TABLE submissions
(
    id         VARCHAR(36) not null,
    code       TEXT        not null,
    assignment VARCHAR(36) NOT NULL,
    user       VARCHAR(36) NOT NULL,
    datetime   DATETIME    NOT NULL,
    PRIMARY KEY (id)
);
