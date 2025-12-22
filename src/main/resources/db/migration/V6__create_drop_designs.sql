CREATE TABLE IF NOT EXISTS drop_designs (
                                            drop_id BIGINT NOT NULL REFERENCES drops(id) ON DELETE CASCADE,
                                            design_id BIGINT NOT NULL REFERENCES designs(id) ON DELETE CASCADE,
                                            position INT NOT NULL DEFAULT 0,
                                            created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                            PRIMARY KEY (drop_id, design_id)
);

CREATE INDEX IF NOT EXISTS idx_drop_designs_drop_position ON drop_designs(drop_id, position);
