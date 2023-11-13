CREATE DATABASE ecommerce_stage_db;
USE ecommerce_stage_db;

-- Favourite db
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
       
       
-- Order db
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
    

-- Payment db:
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
       

-- Product db:
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
    product_id    INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
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


-- Shipping db:
CREATE TABLE order_items
(
    product_id       INT                                 NOT NULL,
    order_id         INT                                 NOT NULL,
    ordered_quantity INT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at       TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id, order_id)
);

INSERT INTO order_items
    (product_id, order_id, ordered_quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 1, 1),
       (2, 2, 1);


-- User db:
CREATE TABLE users
(
    user_id    INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    image_url  VARCHAR(255)       DEFAULT 'https://bootdey.com/img/Content/avatar/avatar7.png',
    email      VARCHAR(255)       DEFAULT 'hoangtien2k3qx1@gmail.com',
    phone      VARCHAR(255)       DEFAULT '+84 828007853',
    created_at TIMESTAMP          DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users
    (first_name, last_name)
VALUES ('hoangtien', '2k3'),
       ('hoangtien2k3', 'qx1'),
       ('hoanganh', 'tien'),
       ('admin', 'admin');

CREATE TABLE address
(
    address_id   INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id      INT,
    full_address VARCHAR(255),
    postal_code  VARCHAR(255),
    city         VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO address
    (user_id, full_address, postal_code, city)
VALUES (1, 'ha noi', '2023', 'ha noi'),
       (2, 'thanh hoa', '2022', 'thanh hoa'),
       (3, 'ho chi minh', '2020', 'ho chi minh'),
       (4, 'da nang', '2021', 'da nang'),
       (2, 'hai phong', '2020', 'hai phong'),
       (1, 'quang ngai', '2023', 'quang ngai');
       
CREATE TABLE credentials
(
    credential_id              INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id                    INT,
    username                   VARCHAR(255),
    password                   VARCHAR(255),
    role                       VARCHAR(255),
    is_enabled                 BOOLEAN DEFAULT false,
    is_account_non_expired     BOOLEAN DEFAULT true,
    is_account_non_locked      BOOLEAN DEFAULT true,
    is_credentials_non_expired BOOLEAN DEFAULT true,
    created_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at                 TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO credentials
    (user_id, username, password, role, is_enabled)
VALUES (1, 'hoangtien2k3', '$2a$04$/S7cWjHPZul03sPEivycWeKTBvLyjYdaRWmeaFbiqKy9es/3W4QB6', 'ROLE_USER', true),
       (2, 'hoangtien2k3qx1', '$2a$04$8D8OuqPbE4LhRckvtBAHrOmpeWmE92xNNVtyK8Z/lrJFjsImpjBkm', 'ROLE_USER', true),
       (3, 'hoanganhtien', '$2a$04$jelNGcF4wFHJirT5Pm7jPO8812QE/3tIWIs1DNnajS68iG4aKUqvS', 'ROLE_USER', true),
       (4, 'tienhoang', '$2a$04$1G4TwSzwf5JwZ4dKCXG1Zu1Qh3WIY9JNaM9vF6Ff05QDfyPg7nSxO', 'ROLE_USER', true);

CREATE TABLE verification_tokens
(
    verification_token_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    credential_id         INT,
    verify_token           VARCHAR(255),
    expire_date           DATE,
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at            TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO verification_tokens
    (credential_id, verify_token, expire_date)
VALUES (1, '', '2021-12-31'),
       (2, '', '2021-12-31'),
       (3, '', '2021-12-31'),
       (4, '', '2021-12-31');
       
ALTER TABLE address
    ADD CONSTRAINT fk1_assign FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE credentials
    ADD CONSTRAINT fk2_assign FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE verification_tokens
    ADD CONSTRAINT fk3_assign FOREIGN KEY (credential_id) REFERENCES credentials (credential_id);



