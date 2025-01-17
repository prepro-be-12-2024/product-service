insert into category
values ('7407c535-95f5-4a66-8734-9b6adaabe66f', 'SẢN PHẨM THIẾT YẾU',
        'NECESSARY_PRODUCT', null, current_date, '4a387361-2489-45f4-8783-be645b9f8bb5', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', 1);

insert into product
values ('9a453594-b16c-4d54-90a0-bb9e3eda9311', 'SP1',
        'NECESSARY_PRODUCT', current_date, '4a387361-2489-45f4-8783-be645b9f8bb5', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', 0, 'Hợp đồng điện tử', 'VCONTRACT', 'ABC',
        '7407c535-95f5-4a66-8734-9b6adaabe66f'),
       ('7aae51b1-2c0f-418b-8f70-3ca87308abe3', 'SP2',
        'NECESSARY_PRODUCT', current_date, '4a387361-2489-45f4-8783-be645b9f8bb5', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', 0, 'MySign', 'SIGN', 'ABC',
        '7407c535-95f5-4a66-8734-9b6adaabe66f');

insert into product_attribute
values ('5f9534f0-acb3-4374-8787-0e030c9a94de', 'price', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', current_date, '4a387361-2489-45f4-8783-be645b9f8bb5',
        0);

insert into product_attribute_value
values ('a0028967-f029-4070-b784-aadd81505c23', '9a453594-b16c-4d54-90a0-bb9e3eda9311',
        '5f9534f0-acb3-4374-8787-0e030c9a94de', '20', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', current_date,
        '4a387361-2489-45f4-8783-be645b9f8bb5', 0),
    ('145c8d48-5240-47e6-9332-9ff0ce67ac61', '7aae51b1-2c0f-418b-8f70-3ca87308abe3',
     '5f9534f0-acb3-4374-8787-0e030c9a94de', '12', current_date,
     '4a387361-2489-45f4-8783-be645b9f8bb5', current_date,
     '4a387361-2489-45f4-8783-be645b9f8bb5', 0);


