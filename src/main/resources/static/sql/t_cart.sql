CREATE TABLE t_cart (
cid INT AUTO_INCREMENT COMMENT 'Shopping cart data id',
uid INT NOT NULL COMMENT 'userid',
pid INT NOT NULL COMMENT 'product id',
price BIGINT COMMENT 'The unit price of the product when joining',
num INT COMMENT 'Item quantity',
created_user VARCHAR(20) COMMENT 'Created by',
created_time DATETIME COMMENT 'created time',
modified_user VARCHAR(20) COMMENT 'modified person',
modified_time DATETIME COMMENT 'Modified time',
PRIMARY KEY (cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;