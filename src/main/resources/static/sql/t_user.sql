DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
	uid INT AUTO_INCREMENT COMMENT 'userId',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT 'username',
	password CHAR(32) NOT NULL COMMENT 'password',
	salt CHAR(36) COMMENT 'salt to encrypt',
	phone VARCHAR(20) COMMENT 'phone no',
	email VARCHAR(30) COMMENT 'email',
	gender INT COMMENT 'sex:0-femail?1-male',
	avatar VARCHAR(50) COMMENT 'head photo',
	is_delete INT COMMENT 'delete?0-no?1-yes',
	created_user VARCHAR(20) COMMENT 'creater',
	created_time DATETIME COMMENT 'modify user',
	modified_user VARCHAR(20) COMMENT 'modify user',
	modified_time DATETIME COMMENT 'modify time',
	PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
DROP TABLE IF EXISTS t_address;
CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT 'receive address',
	uid INT COMMENT 'user id',
	name VARCHAR(20) COMMENT 'receiver',
	province_name VARCHAR(50) COMMENT 'name of provice',
	province_code CHAR(6) COMMENT 'code of provice',
	city_name VARCHAR(100) COMMENT 'name of city',
	city_code CHAR(6) COMMENT 'code of city',
	area_name VARCHAR(100) COMMENT 'name of district',
	area_code CHAR(6) COMMENT 'code of district',
	zip CHAR(6) COMMENT 'zipcode',
	address VARCHAR(50) COMMENT 'address',
	phone VARCHAR(20) COMMENT 'phone',
	tel VARCHAR(20) COMMENT 'telphone',
	tag VARCHAR(6) COMMENT 'tag',
	is_default INT COMMENT '0 no 1 yes',
	created_user VARCHAR(20) COMMENT 'creater',
	created_time DATETIME COMMENT 'create time',
	modified_user VARCHAR(20) COMMENT 'modify user',
	modified_time DATETIME COMMENT 'modify time',
	PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;