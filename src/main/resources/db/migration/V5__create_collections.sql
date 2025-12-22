CREATE TABLE IF NOT EXISTS collections (
                                           id BIGSERIAL PRIMARY KEY,
                                           name VARCHAR(200) NOT NULL,
                                           slug VARCHAR(220) NOT NULL UNIQUE,
                                           description TEXT,
                                           status VARCHAR(32) NOT NULL DEFAULT 'DRAFT', -- DRAFT, PUBLISHED, ARCHIVED
                                           hero_image_path VARCHAR(500),
                                           created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                           updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_collections_status ON collections(status);
