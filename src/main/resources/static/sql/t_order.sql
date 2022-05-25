CREATE TABLE t_order
(
    oid           int         NOT NULL AUTO_INCREMENT COMMENT 'Order id',
    uid           int         NOT NULL COMMENT 'userid',
    recv_name     varchar(20) NOT NULL COMMENT 'reciever',
    recv_phone    varchar(20)  DEFAULT NULL COMMENT 'receiver phone',
    recv_province varchar(100) DEFAULT NULL COMMENT 'The province where the consignee is located',
    recv_city     varchar(100) DEFAULT NULL COMMENT 'The city of the consignee',
    recv_area     varchar(100) DEFAULT NULL COMMENT 'The area where the consignee is located',
    recv_address  varchar(100) DEFAULT NULL COMMENT 'Detailed delivery address',
    total_price   bigint       DEFAULT NULL COMMENT 'Total price',
    status        int          DEFAULT NULL COMMENT 'Status: 0-unpaid, 1-paid, 2-cancelled, 3-closed, 4-completed',
    order_time    datetime     DEFAULT NULL COMMENT 'Order time',
    pay_time      datetime     DEFAULT NULL COMMENT 'Payment time',
    created_user  varchar(20)  DEFAULT NULL COMMENT 'Created by',
    created_time  datetime     DEFAULT NULL COMMENT 'created time',
    modified_user varchar(20)  DEFAULT NULL COMMENT 'modified person',
    modified_time datetime     DEFAULT NULL COMMENT 'Modified time',
    PRIMARY KEY (oid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE t_order_item
(
    id            INT AUTO_INCREMENT COMMENT 'The id of the product record in the order',
    oid           INT          NOT NULL COMMENT 'The id of the order to which it belongs',
    pid           INT          NOT NULL COMMENT 'Product id',
    title         VARCHAR(100) NOT NULL COMMENT 'Product title',
    image         VARCHAR(500) COMMENT 'Product image',
    price         BIGINT COMMENT 'Product price',
    num           INT COMMENT 'purchase quantity',
    created_user  VARCHAR(20) COMMENT 'Created by',
    created_time  DATETIME COMMENT 'created time',
    modified_user VARCHAR(20) COMMENT 'modified person',
    modified_time DATETIME COMMENT 'Modified time',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;