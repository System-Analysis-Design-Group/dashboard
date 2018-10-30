- /Accounts
    - POST 新建一个账户

- /Accounts/{id}
    - GET 获取某个账户信息
    - DELETE 删除一个账户
    - PUT 修改一个账户的信息

- /Accounts/{id}/Permission
    - GET 获取某个账户权限
    - POST 给某个用户新建权限
    - DELETE 给某个用户删除权限
    - PUT 修改一个账户的权限

- /stores
    - GET 所有商家
    - POST 新建商家

- /stores/{id}/comments
    - GET 获取某个商家所有评论
    - POST 给某个商家新建评论

- /stores/{id}/comments/{id}
    - GET 获取某个商家的某个评论
    - DELETE 删除某个商家的评论
    - PUT 更新某个商家的评论

- /stores/{id}/dish/{id}/comments
    - GET 获取某个商家的某个菜式的评论
    - DELETE 删除某个商家的某个菜式的评论
    - PUT 更新某个商家的某个菜式的评论

- /orders
    - POST 新建订单

- /orders/{id}
    - GET 获取某个订单的信息
    - DELETE 删除某个订单
    - PUT 更新某个订单

- /dishes
    - POST 新建菜式

- /dishes/{id}
    - GET 获取某个菜式的信息
    - DELETE 删除某个菜式
    - PUT 更新某个菜式