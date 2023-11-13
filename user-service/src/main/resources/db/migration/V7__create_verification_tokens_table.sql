CREATE TABLE verification_tokens
(
    verification_token_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    credential_id         INT,
    verify_token           VARCHAR(255),
    expire_date           DATE,
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at            TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);


