CREATE TABLE users (
                       id          BIGSERIAL PRIMARY KEY,
                       email       VARCHAR(255) NOT NULL UNIQUE,
                       password    VARCHAR(255) NOT NULL,
                       role        VARCHAR(50)  NOT NULL,
                       enabled     BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
                       updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_users_email ON users (email);
