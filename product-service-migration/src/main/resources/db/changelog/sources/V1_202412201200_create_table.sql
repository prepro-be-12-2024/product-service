CREATE TABLE `category`
(
    `id`         varchar(36) PRIMARY KEY,
    `name`       varchar(255),
    `code`       varchar(255),
    `parent_id`  varchar(36),
    `created_at` datetime,
    `created_by` varchar(255),
    `updated_at` datetime,
    `updated_by` varchar(255),
    `deleted`    tinyint(1),
    foreign key (parent_id) references category (id)
);

CREATE TABLE `product`
(
    `id`          varchar(36) PRIMARY KEY,
    `overview`    text,
    `feature`     text,
    `created_at`  datetime,
    `created_by`  varchar(255),
    `updated_at`  datetime,
    `updated_by`  varchar(255),
    `deleted`     tinyint(1),
    `name`        varchar(255),
    `code`        varchar(255),
    `thumbnail`   varchar(255),
    `category_id` varchar(36),
    foreign key (category_id) references category (id)
);

CREATE TABLE `comment`
(
    `id`         varchar(36) PRIMARY KEY,
    `content`    text,
    `vote`       tinyint(1),
    `user_id`    varchar(255),
    `product_id` varchar(255),
    `created_at` datetime,
    `created_by` varchar(255),
    `updated_at` datetime,
    `updated_by` varchar(255),
    `deleted`    tinyint(1),
    foreign key (product_id) references product (id)
);

CREATE TABLE `product_image`
(
    `id`         varchar(36) PRIMARY KEY,
    `image_name` varchar(255),
    `created_at` datetime,
    `created_by` varchar(255),
    `updated_at` datetime,
    `updated_by` varchar(255),
    `deleted`    tinyint(1),
    `product_id` varchar(36),
    foreign key (product_id) references product (id)
);

CREATE TABLE `product_guide`
(
    `id`          varchar(36) PRIMARY KEY,
    `title`       text,
    `description` text,
    `content`     varchar(255),
    `created_at`  datetime,
    `created_by`  varchar(255),
    `updated_at`  datetime,
    `updated_by`  varchar(255),
    `deleted`     tinyint(1),
    `product_id`  varchar(36),
    foreign key (product_id) references product (id)
);

CREATE TABLE product_attribute
(
    id         varchar(36) PRIMARY KEY,
    name       varchar(255) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    deleted    tinyint(1)
);

CREATE TABLE product_attribute_value
(
    id           varchar(36) PRIMARY KEY,
    product_id   varchar(36) NOT NULL,
    attribute_id varchar(36) NOT NULL,
    value        text,
    created_at   datetime,
    created_by   varchar(255),
    updated_at   datetime,
    updated_by   varchar(255),
    deleted      tinyint(1),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (attribute_id) REFERENCES product_attribute (id)
);

CREATE TABLE `cart`
(
    `id`         varchar(36) PRIMARY KEY,
    `created_at` datetime,
    `created_by` varchar(255),
    `updated_at` datetime,
    `updated_by` varchar(255),
    `deleted`    tinyint(1),
    `user_id`    varchar(255)
);

CREATE TABLE `cart_items`
(
    `id`         varchar(36) PRIMARY KEY,
    `created_at` datetime,
    `created_by` varchar(255),
    `updated_at` datetime,
    `updated_by` varchar(255),
    `deleted`    tinyint(1),
    `quantity`   int,
    `product_id` varchar(255),
    `cart_id`    varchar(255),
    foreign key (cart_id) references cart(id)
);