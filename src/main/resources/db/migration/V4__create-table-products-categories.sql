CREATE TABLE products_categories (
    id_product BIGINT,
    id_category BIGINT,
    PRIMARY KEY (id_product, id_category),
    FOREIGN KEY (id_product) REFERENCES products(id_product),
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);