CREATE TABLE IF NOT EXISTS `order` (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  store_id INT UNSIGNED NOT NULL,
  address_id INT UNSIGNED NOT NULL,
  -- canceling 状态指的是用户发起取消请求，如果商家同意，状态变更为canceled，否则回到paid状态
  -- 而rejected状态指的是商家取消订单，只有rejected和canceled状态发起退款
  state ENUM('unpaid', 'paid', 'rejected', 'preparing', 'dispatching', 'finished', 'canceling', 'canceled') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS order_goods (
  order_id INT UNSIGNED NOT NULL,
  goods_id INT UNSIGNED NOT NULL,
  num INT UNSIGNED NOT NULL,
  unit_price FLOAT NOT NULL
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
  tag ENUM('family', 'company', 'school')
) ENGINE=InnoDB;