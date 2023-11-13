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



