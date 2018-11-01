* /Accounts
    * POST 新建一个账户
        * 发送 { "username": "James", "password": "abcd" }
        * 成功返回 { "info": { "id": 123, "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "username is used." } }
* /Accounts/{id}
    * GET 获取某个账户信息
        * 发送 { "username": "James" }
        * 成功返回 { "info": { "id": 123, "username":"James", "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * DELETE 删除一个账户
        * 发送 { "username": "James" }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * PUT 修改一个账户的信息
* /Accounts/{id}/Permission
    * GET 获取某个账户权限
        * 发送 { "username":"James" }
        * 成功返回 { "info": { "role\_ids":[1,2] ,"rolenames": [ "default", "admin" ] }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * POST 给某个用户新建权限
        * 发送 { "username": "James", "role": "admin" }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
    * DELETE 给某个用户删除权限
        * 发送 { "username": "James", "role": "admin" }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * PUT 修改一个账户的权限
* /stores
    * GET 所有商家
        * 发送 { "type": [ "Chinese", "snack" ] }
        * 返回 { "store\_id": [ 1, 2, 3, 4, 5 ], "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "can't find any store" } }
    * POST 新建商家
        * 发送 { "name": "store1", "phone": "123456789", "type": "Chinese food", "user\_id": 123, "address": "Wall Street", "longitude": 123.123, "latitude": 231.231, "cover": "" }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
* /stores/{id}/comments
    * GET 获取某个商家所有评论
        * 发送 { "name": "store1" }
        * 成功返回 { "info": { "number": 3, "comment": [ "abcd", "efgh", "ijk" ] }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "can't find any comment" } }
    * POST 给某个商家新建评论
        * 发送 { "name": "store1", "comment": "fucking delicious" }
        * 成功返回 { "comment\_id":12,"isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
* /stores/{id}/comments/{id}
    * GET 获取某个商家的某个评论
        * 发送 { "name": "store1", "comment\_id": 123 }
        * 成功返回 { "comment": "qnmb", "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "can't find this comment" } }
    * DELETE 删除某个商家的评论
        * 发送 { "name": "store1", "comment\_id": 123 }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
    * PUT 更新某个商家的评论
        * 发送 { "name": "store1", "comment\_id": 123, "content":"new comment" }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "updated" } }
* /stores/{id}/dish/{id}/comments
    * GET 获取某个商家的某个菜式的评论
        * 请求 { "store\_id": 123, "good\_id": 123 } 
        * 成功返回 { "isSuccess": { "status": 200, "detail": "updated" }, "num": 3, "comment": [ "abc", "def", "abcd" ] }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * DELETE 删除某个商家的某个菜式的评论
        * 请求 { "store\_id": 123, "good\_id": 123 }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
        * 失败返回 { "isSuccess": { "status": 400, "detail": "failed" } }
    * PUT 更新某个商家的某个菜式的评论
* /orders
    * POST 新建订单
        * 请求
* /orders/{id}
    * GET 获取某个订单的信息
    * DELETE 删除某个订单
    * PUT 更新某个订单
* /dishes
    * POST 新建菜式
* /dishes/{id}
    * GET 获取某个菜式的信息
    * DELETE 删除某个菜式
    * PUT 更新某个菜式

