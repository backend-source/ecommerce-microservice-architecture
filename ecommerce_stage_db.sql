CREATE DATABASE ecommerce_stage_db;

USE ecommerce_stage_db;

-- favourite db
CREATE TABLE favourites
(
    user_id    INT                                 NOT NULL,
    product_id INT                                 NOT NULL,
    like_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, product_id, like_date)
);

INSERT INTO favourites
    (user_id, product_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);
       
       
-- order db
CREATE TABLE carts
(
    cart_id    INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO carts
    (user_id)
VALUES (1),
       (2),
       (3),
       (4);
       
CREATE TABLE orders
(
    order_id   INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cart_id    INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    order_desc VARCHAR(255),
    order_fee  DECIMAL(7, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO orders
    (cart_id, order_desc, order_fee)
VALUES (1, 'init', 5000),
       (2, 'init', 5000),
       (3, 'init', 5000),
       (4, 'init', 5000);

ALTER TABLE orders
    ADD CONSTRAINT fk5_assign FOREIGN KEY (cart_id) REFERENCES carts (cart_id);
    

-- payment db:
CREATE TABLE payments
(
    payment_id     INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id       INT,
    is_payed        BOOLEAN,
    payment_status VARCHAR(255),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO payments
    (order_id, is_payed, payment_status)
VALUES (1, false, 'IN_PROGRESS'),
       (2, false, 'IN_PROGRESS'),
       (3, false, 'IN_PROGRESS'),
       (4, false, 'IN_PROGRESS');
       

-- product db:
CREATE TABLE categories
(
    category_id        INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category_title     VARCHAR(255),
    image_url          VARCHAR(255),
    parent_category_id INT,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO categories
(category_title, image_url, parent_category_id, created_at, updated_at)
VALUES
    ('Computer', null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mode', null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Game', null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

CREATE TABLE products
(
    product_id    INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category_id   INT,
    product_title VARCHAR(255),
    image_url     VARCHAR(255),
    sku           VARCHAR(255),
    price_unit    DECIMAL(7, 2),
    quantity      INT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO products
    (category_id, product_title, image_url, sku, price_unit, quantity)
VALUES (1, 'asus', 'xxx', 'dfqejklejrkn', 0, 50),
       (1, 'hp', 'xxx', 'zsejfedbjh', 0, 50),
       (2, 'Armani', 'xxx', 'fjdvf', 0, 50),
       (3, 'GTA', 'xxx', 'qsdkjnvfrekjrf', 0, 50);
       
ALTER TABLE categories
    ADD CONSTRAINT fk7_assign FOREIGN KEY (parent_category_id) REFERENCES categories (category_id);

ALTER TABLE products
    ADD CONSTRAINT fk8_assign FOREIGN KEY (category_id) REFERENCES categories (category_id);







