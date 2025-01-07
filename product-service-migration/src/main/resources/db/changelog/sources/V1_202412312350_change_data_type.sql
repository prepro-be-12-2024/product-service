delete from product.product;
delete from category;


alter table `cart`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `cart_items`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `category`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `category_tree`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `comment`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table orders
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `order_items`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `product`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `product_guide`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `product_image`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `product_variant`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

alter table `tag`
    modify created_at DATETIME null,
    modify updated_at DATETIME null;

insert into category
values ('7407c535-95f5-4a66-8734-9b6adaabe66f', 'SẢN PHẨM THIẾT YẾU',
        'NECESSARY_PRODUCT', now(), 'minh', now(),
        'minh', 1);

insert into product
values ('9a453594-b16c-4d54-90a0-bb9e3eda9311', 'SẢN PHẨM THIẾT YẾU',
        'NECESSARY_PRODUCT', now(), 'minh', now(),
        'minh', 0, 'Hợp đồng điện tử', 'VCONTRACT', 'ABC',
        '7407c535-95f5-4a66-8734-9b6adaabe66f')