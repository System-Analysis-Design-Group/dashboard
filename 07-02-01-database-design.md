
# 概念设计

![](https://github.com/YummyOrder/dashboard/blob/gh-pages/Docs/UML%20diagram/%E6%95%B0%E6%8D%AE%E5%BA%93ER%E5%85%B3%E7%B3%BB%E5%9B%BE.png?raw=true)

# 逻辑设计

![](https://github.com/YummyOrder/dashboard/blob/gh-pages/Docs/Database%20design/%E9%80%BB%E8%BE%91%E5%BB%BA%E6%A8%A1.png?raw=true)

# 物理设计（在 MySQL 环境下）

![](https://github.com/YummyOrder/dashboard/blob/gh-pages/Docs/Database%20design/%E7%89%A9%E7%90%86%E5%BB%BA%E6%A8%A1.png?raw=true)

# 数据字典
- User

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | userId| INTEGER| 主键 |
  | username| VARCHAR(30)|
  | password| VARCHAR(128)|
  | avatar| VARCHAR(30)|


- Role

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | rolename| VARCHAR|
  | roleId| INTEGER| 主键 |


- Permission

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | name| VARCHAR|
  | perId| INTEGER| 主键 |


- Address

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | name| VARCHAR|
  | phone| VARCHAR|
  | longitude| FLOAT|
  | latitude| FLOAT|
  | addId| INTEGER| 主键 |


- Order

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | orderId| INTEGER| 主键 |
  | charge| FLOAT|
  | state| VARCHAR|
  | created_at| TIME|


- Comment

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | parent_Id| CHAR| 主键 |
  | content| VARCHAR|
  | type| VARCHAR|
  | comId| CHAR|


- Dish

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | name| VARCHAR|
  | original_price| FLOAT|
  | current_price| REAL|
  | description| VARCHAR(150)|
  | dishId| INTEGER| 主键 |


- Store

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | name| VARCHAR|
  | phone| VARCHAR|
  | address| VARCHAR|
  | longitude| FLOAT|
  | latitude| FLOAT|
  | type| VARCHAR|
  | storeId| INTEGER| 主键 |


- UserRole

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | userId| INTEGER|  主键 |NOT NULL
  | roleId| INTEGER|  主键 |NOT NULL


- RolePermission

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | roleId| INTEGER|  主键 |NOT NULL
  | perId| INTEGER|  主键 |NOT NULL


- UserOrder

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | userId| INTEGER|  主键 |NOT NULL
  | orderId| INTEGER|  主键 |NOT NULL


- OrderDish

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | orderId| INTEGER|  主键 |NOT NULL


- OrderStore

| 字段 | 数据类型 | 键 | 约束 |
| ------ | ------ | ------ | ------ |
  | orderId| INTEGER|  主键 |NOT NULL
  | storeId| INTEGER|  主键 |NOT NULL

