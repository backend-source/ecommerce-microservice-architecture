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

