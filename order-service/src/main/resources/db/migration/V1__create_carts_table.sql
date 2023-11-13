CREATE TABLE carts
(
    cart_id    INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

