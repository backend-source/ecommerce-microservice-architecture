CREATE TABLE categories
(
    category_id        INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category_title     VARCHAR(255),
    image_url          VARCHAR(255),
    parent_category_id INT,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

