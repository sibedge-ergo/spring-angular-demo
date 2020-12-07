CREATE TABLE "person" (
    id              BIGSERIAL       PRIMARY KEY,

    personal_id     VARCHAR(50)     NOT NULL UNIQUE,
    first_name      VARCHAR(50)     NOT NULL,
    last_name       VARCHAR(100)    NOT NULL,
    gender          VARCHAR(10)     NOT NULL,
    date_of_birth   DATE            NOT NULL,

    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

COMMENT ON TABLE "person" IS 'Person record data';