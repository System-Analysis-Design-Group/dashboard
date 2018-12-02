insert into orders
(user_id, store_id, address_id, state) 
values
(1,1,1,'unpaid'),
(2,1,2,'unpaid'),
(3,1,3,'paid'),
(1,1,1,'paid');


insert into order_goods
(order_id, goods_id, num, unit_price) 
values
(1,1,1,12),
(1,2,1,13),
(2,1,1,12),
(3,2,1,13),
(4,1,2,12);