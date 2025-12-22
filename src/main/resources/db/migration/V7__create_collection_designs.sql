CREATE TABLE IF NOT EXISTS collection_designs (
                                                  collection_id BIGINT NOT NULL REFERENCES collections(id) ON DELETE CASCADE,
                                                  design_id BIGINT NOT NULL REFERENCES designs(id) ON DELETE CASCADE,
                                                  position INT NOT NULL DEFAULT 0,
                                                  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                                                  PRIMARY KEY (collection_id, design_id)
);

CREATE INDEX IF NOT EXISTS idx_collection_designs_collection_position ON collection_designs(collection_id, position);
