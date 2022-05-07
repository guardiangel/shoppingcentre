CREATE TABLE t_order (
oid INT AUTO_INCREMENT COMMENT 'Order id',
uid INT NOT NULL COMMENT 'userid',
recv_name VARCHAR(20) NOT NULL COMMENT 'reciever',
recv_phone VARCHAR(20) COMMENT 'receiver phone',
recv_province VARCHAR(15) COMMENT 'The province where the consignee is located',
recv_city VARCHAR(15) COMMENT 'The city of the consignee',
recv_area VARCHAR(15) COMMENT 'The area where the consignee is located',
recv_address VARCHAR(50) COMMENT 'Detailed delivery address',
total_price BIGINT COMMENT 'Total price',
status INT COMMENT 'Status: 0-unpaid, 1-paid, 2-cancelled, 3-closed, 4-completed',
order_time DATETIME COMMENT 'Order time',
pay_time DATETIME COMMENT 'Payment time',
created_user VARCHAR(20) COMMENT 'Created by',
created_time DATETIME COMMENT 'created time',
modified_user VARCHAR(20) COMMENT 'modified person',
modified_time DATETIME COMMENT 'Modified time',
PRIMARY KEY (oid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE t_order_item (
id INT AUTO_INCREMENT COMMENT 'The id of the product record in the order',
oid INT NOT NULL COMMENT 'The id of the order to which it belongs',
pid INT NOT NULL COMMENT 'Product id',
title VARCHAR(100) NOT NULL COMMENT 'Product title',
image VARCHAR(500) COMMENT 'Product image',
price BIGINT COMMENT 'Product price',
num INT COMMENT 'purchase quantity',
created_user VARCHAR(20) COMMENT 'Created by',
created_time DATETIME COMMENT 'created time',
modified_user VARCHAR(20) COMMENT 'modified person',
modified_time DATETIME COMMENT 'Modified time',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;