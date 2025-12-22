CREATE TABLE IF NOT EXISTS drops (
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(200) NOT NULL,
                                     slug VARCHAR(220) NOT NULL UNIQUE,
                                     description TEXT,
                                     status VARCHAR(32) NOT NULL DEFAULT 'DRAFT', -- DRAFT, LIVE, ENDED
                                     starts_at TIMESTAMPTZ,
                                     ends_at TIMESTAMPTZ,
                                     hero_image_path VARCHAR(500),
                                     created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                     updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_drops_status ON drops(status);
CREATE INDEX IF NOT EXISTS idx_drops_window ON drops(starts_at, ends_at);
