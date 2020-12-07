CREATE TABLE "audit_event" (
    id          BIGSERIAL       PRIMARY KEY,

    key         VARCHAR(20)     NOT NULL,
    details     VARCHAR(1000)   NOT NULL,

    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

COMMENT ON TABLE "audit_event" IS 'System events';