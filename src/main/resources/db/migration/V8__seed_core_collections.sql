-- Core store collections
INSERT INTO collections (name, slug, description, status)
VALUES
    ('Men',   'men',   'Mens apparel',   'PUBLISHED'),
    ('Women', 'women', 'Womens apparel', 'PUBLISHED'),
    ('Kids',  'kids',  'Kids apparel',   'PUBLISHED')
ON CONFLICT (slug) DO NOTHING;
