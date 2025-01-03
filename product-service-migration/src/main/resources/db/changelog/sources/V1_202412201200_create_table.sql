CREATE TABLE `product` (
                           `id` varchar(36) PRIMARY KEY,
                           `overview` text,
                           `feature` text,
                           `created_at` bigint,
                           `created_by` varchar(255),
                           `updated_at` bigint,
                           `updated_by` varchar(255),
                           `deleted` tinyint(1),
                           `name` varchar(255),
                           `code` varchar(255),
                           `thumbnail` varchar(255),
                           `category_id` varchar(36)
);

CREATE TABLE `comment` (
                           `id` varchar(36) PRIMARY KEY,
                           `content` text,
                           `vote` tinyint(1),
                           `user_id` varchar(255),
                           `product_id` varchar(255),
                           `created_at` bigint,
                           `created_by` varchar(255),
                           `updated_at` bigint,
                           `updated_by` varchar(255),
                           `deleted` tinyint(1)
);

CREATE TABLE `product_image` (
                                 `id` varchar(36) PRIMARY KEY,
                                 `image_name` varchar(255),
                                 `created_at` bigint,
                                 `created_by` varchar(255),
                                 `updated_at` bigint,
                                 `updated_by` varchar(255),
                                 `deleted` tinyint(1),
                                 `product_id` varchar(36)
);

CREATE TABLE `product_guide` (
                                 `id` varchar(36) PRIMARY KEY,
                                 `title` text,
                                 `description` text,
                                 `content` varchar(255),
                                 `created_at` bigint,
                                 `created_by` varchar(255),
                                 `updated_at` bigint,
                                 `updated_by` varchar(255),
                                 `deleted` tinyint(1),
                                 `product_id` varchar(36)
);

CREATE TABLE `category` (
                            `id` varchar(36) PRIMARY KEY,
                            `name` varchar(255),
                            `code` varchar(255),
                            `created_at` bigint,
                            `created_by` varchar(255),
                            `updated_at` bigint,
                            `updated_by` varchar(255),
                            `deleted` tinyint(1)
);

CREATE TABLE `category_tree` (
                                 `id` varchar(36) PRIMARY KEY,
                                 `parent` varchar(36),
                                 `child` varchar(36),
                                 `created_at` bigint,
                                 `created_by` varchar(255),
                                 `updated_at` bigint,
                                 `updated_by` varchar(255),
                                 `deleted` tinyint(1)
);

CREATE TABLE `product_variant` (
                                   `id` varchar(36) PRIMARY KEY,
                                   `name` varchar(255),
                                   `code` varchar(255),
                                   `price` float,
                                   `description` text,
                                   `created_at` bigint,
                                   `created_by` varchar(255),
                                   `updated_at` bigint,
                                   `updated_by` varchar(255),
                                   `deleted` tinyint(1),
                                   `product_id` varchar(36),
                                   `tag_id` varchar(36)
);

CREATE TABLE `tag` (
                       `id` varchar(36) PRIMARY KEY,
                       `created_at` bigint,
                       `created_by` varchar(255),
                       `updated_at` bigint,
                       `updated_by` varchar(255),
                       `deleted` tinyint(1),
                       `name` varchar(255),
                       `code` varchar(255),
                        `product_id` varchar(36)
);

CREATE TABLE `cart` (
                        `id` varchar(36) PRIMARY KEY,
                        `created_at` bigint,
                        `created_by` varchar(255),
                        `updated_at` bigint,
                        `updated_by` varchar(255),
                        `deleted` tinyint(1),
                        `user_id` varchar(255)
);

CREATE TABLE `cart_items` (
                              `id` varchar(36) PRIMARY KEY,
                              `created_at` bigint,
                              `created_by` varchar(255),
                              `updated_at` bigint,
                              `updated_by` varchar(255),
                              `deleted` tinyint(1),
                              `quantity` int,
                              `product_id` varchar(255),
                              `cart_id` varchar(255)
);

CREATE TABLE `order` (
                         `id` varchar(36) PRIMARY KEY,
                         `user_id` varchar(255),
                        `status` varchar(30),
                        `am_phone` varchar(12),
                         `created_at` bigint,
                         `created_by` varchar(255),
                         `updated_at` bigint,
                         `updated_by` varchar(255),
                         `deleted` tinyint(1)
                         
);

CREATE TABLE `order_items` (
                               `id` varchar(36) PRIMARY KEY,
                               `created_at` bigint,
                               `created_by` varchar(255),
                               `updated_at` bigint,
                               `updated_by` varchar(255),
                               `deleted` tinyint(1),
                               `product_id` varchar(255),
                               `quantity` int,
                               `price` float,
                               `order_id` varchar(255)
);

ALTER TABLE `comment` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `tag` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_guide` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_image` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `product_variant` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_variant` ADD FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

ALTER TABLE `category_tree` ADD FOREIGN KEY (`parent`) REFERENCES `category` (`id`);

ALTER TABLE `category_tree` ADD FOREIGN KEY (`child`) REFERENCES `category` (`id`);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);