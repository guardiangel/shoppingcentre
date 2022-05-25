CREATE TABLE t_address
(
    aid           int NOT NULL AUTO_INCREMENT COMMENT 'receive address',
    uid           int          DEFAULT NULL COMMENT 'user id',
    name          varchar(20)  DEFAULT NULL COMMENT 'receiver',
    province_name varchar(50)  DEFAULT NULL COMMENT 'name of provice',
    province_code char(6)      DEFAULT NULL COMMENT 'code of provice',
    city_name     varchar(100) DEFAULT NULL COMMENT 'name of city',
    city_code     char(6)      DEFAULT NULL COMMENT 'code of city',
    area_name     varchar(100) DEFAULT NULL COMMENT 'name of district',
    area_code     char(6)      DEFAULT NULL COMMENT 'code of district',
    zip           char(6)      DEFAULT NULL COMMENT 'zipcode',
    address       varchar(50)  DEFAULT NULL COMMENT 'address',
    phone         varchar(20)  DEFAULT NULL COMMENT 'phone',
    tel           varchar(20)  DEFAULT NULL COMMENT 'telphone',
    tag           varchar(6)   DEFAULT NULL COMMENT 'tag',
    is_default    int          DEFAULT NULL COMMENT '0 no 1 yes',
    created_user  varchar(20)  DEFAULT NULL COMMENT 'creater',
    created_time  datetime     DEFAULT NULL COMMENT 'create time',
    modified_user varchar(20)  DEFAULT NULL COMMENT 'modify user',
    modified_time datetime     DEFAULT NULL COMMENT 'modify time',
    PRIMARY KEY (aid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
