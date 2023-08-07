DELETE FROM `order_item`;
DELETE FROM `orders`;
DELETE FROM `customer`;
DELETE FROM `address`;
DELETE FROM `state`;
DELETE FROM `country`;
DELETE FROM `product`;
DELETE FROM `product_category`;

INSERT INTO product_category(id, category_name)
VALUES (1, 'Luggage Tags');

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES (1, 'BOOK-TECH-1000', 'Crash Course in Python', 'Learn Python at your own pace.', 'assets/images/products/books/book-1000.png', 1, 100, 14.99, 1, NOW());
       (2, 'BOOK-TECH-1001', 'Become a Guru in JavaScript', 'Learn JavaScript at your own pace.', 'assets/images/products/books/book-1001.png', 1, 100, 20.99, 1, NOW());
       (3, 'BOOK-TECH-1002', 'Exploring Vue.js', 'Learn Vue.js at your own pace.', 'assets/images/products/books/book-1002.png', 1, 100, 14.99, 1, NOW());
