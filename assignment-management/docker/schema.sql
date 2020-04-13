CREATE TABLE assignments
(
    id          VARCHAR(36) not null,
    title       VARCHAR(50) not null,
    description TEXT        not null,
    deadline    DATETIME    not null,
    status      int         not null,
    PRIMARY KEY (id)
);
