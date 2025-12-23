alter table designs
    add column if not exists gelato_product_id varchar(64);

create unique index if not exists uk_designs_gelato_product_id
    on designs(gelato_product_id)
    where gelato_product_id is not null;
