CREATE TABLE IF NOT EXISTS user (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  username CHAR(32) NOT NULL,
  `password` CHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_roles_table (
  user_id INT UNSIGNED NOT NULL,
  role_id INT UNSIGNED NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS role (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  rolename VARCHAR(32) CHARACTER SET utf8 NOT NULL,
  detail VARCHAR(255) CHARACTER SET utf8 DEFAULT '',
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- 可选做后面的详细资源点
CREATE TABLE IF NOT EXISTS permission (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED, -- 对于user的特例
  role_id INT UNSIGNED, -- 对于常规的role
  entry_id INT UNSIGNED, -- 对于特定的entry, 否则是所有的entry
  -- x(all)x(get)x(create)x(update)x(delete) all可以表示如果有特定资源点，那么这个资源点所有资源可以访问，get/create/update/delete 表示其中某一项
  access ENUM('all', 'get', 'create', 'update', 'delete') NOT NULL
) ENGINE=InnoDB;

-- 资源点，例如可以表示一个服务，或者一个服务中的某个接口
CREATE TABLE IF NOT EXISTS entry (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  info VARCHAR(255) CHARACTER SET utf8 DEFAULT '' -- 例如可以特殊表示成一个接口正则，或者一个service_name
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS address (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  `name` CHAR(32) CHARACTER SET utf8 NOT NULL,
  gender ENUM('male', 'female') NOT NULL,
  phone CHAR(32) NOT NULL,
  shipping_address VARCHAR(255) CHARACTER SET utf8 DEFAULT '', -- 收货地址(思考是否需要加上坐标)
  longitude FLOAT NOT NULL, -- 经度
  latitude FLOAT NOT NULL, -- 纬度
  house_number VARCHAR(255) CHARACTER SET utf8 DEFAULT '',
  tag ENUM('family', 'company', 'school'),
  PRIMARY KEY (id)
) ENGINE=InnoDB;
