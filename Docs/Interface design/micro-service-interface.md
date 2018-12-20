* /Accounts/Signin
    * POST 登录
        * 发送 { "username": "James", "password": "abcd" }
        * 成功返回 { "info": { "username": "James","id": 123, "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "username or password is wrong." } }
* /Accounts/Signup
    * POST 新建一个账户
        * 发送 { "username": "James", "password": "abcd" }
        * 成功返回 { "info": { "username": "James","id": 123, "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "username is used." } }
* /Accounts/Customer/{user_id}
    * GET 获取某个账户信息
        * 成功返回 { "info": { "id": 123, "username":"James", "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * DELETE 删除一个账户
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
* /Accounts/Permission
    * GET 获取某个账户权限
        * 发送 { "user_id":123 }
        * 成功返回 { "info": { "role_ids":[1,2] ,"rolenames": [ "default", "admin" ] }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * POST 给某个用户新建权限
        * 发送 { "user_id": 123, "role_id": 123 }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
    * DELETE 给某个用户删除权限
        * 发送 { "user_id": 123, "role_id": 123 }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * PUT 修改一个账户的权限
* /Accounts/Stores
    * GET 所有商家
        * 发送 { "type": [ "Chinese", "snack" ],"sum":5 }
        * 返回 { "num_found":5, "store_id": [ 1, 2, 3, 4, 5 ], "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "can't find any store" } }
    * POST 新建商家
        * 发送 { "name": "KFC", "phone": "123456789", "type": "Chinese food", "user\_id": 123, "address": "Wall Street", "longitude": 123.123, "latitude": 231.231, "cover": "" }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }

* /Comments/store/{store_id}
    * GET 获取某个商家所有评论
        * 成功返回 { "info": { "number": 3, "comment": [ "abcd", "efgh", "ijk" ] }, "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "can't find any comment" } }
    * POST 给某个商家新建评论
        * 发送 {"comment": "fucking delicious" }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }


* /Comments/store/{store_id}/{comment_id}
    * POST 商家回应顾客评价，在此要判断是否商家请求的该接口
        {"content":"new comment" }
    * GET 获取某个商家的某个评论
        * 成功返回 { "comment": "qnmb", "isSuccess": { "status": 200, "detail": "done" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "can't find this comment" } }
    * DELETE 删除某个商家的评论
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
    * PUT 更新某个商家的评论
        * 发送 {"content":"new comment" }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
        * 成功返回 { "isSuccess": { "status": 200, "detail": "updated" } }

* /Comments/dish/{store_id}/{dish_id}
    * GET 获取某个商家的某个菜式的评论 
        * 成功返回 { "isSuccess": { "status": 200, "detail": "updated" }, "num": 3, "comment": [ "abc", "def", "abcd" ] }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * DELETE 删除某个商家的某个菜式的评论
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * PUT 更新某个商家的某个菜式的评论

* /orders
    * POST 新建订单
        * 请求{
    "user_id": 123, 
    "sotre_id": 123, 
    "num_dish": 3, 
    "dishs_info": [
        {
            "dish_id": 1, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dish_id": 2, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dish_id": 3, 
            "num": 2, 
            "unit_price": 10
        }
    ]
}
        * 成功返回 { "isSuccess": { "status": 200, "detail": "created" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
* /orders/{order_id}
    * GET 获取某个订单的信息
    ** status 四种状态 unpaid, paided, accepted, rejected **
      - 成功返回
      {
    "user_id": 123, 
    "sotre_id": 123, 
    "status": "paid", 
    "num_dish": 3, 
    "address": 123, 
    "created_time":"2018-11-11",
    "dishs_info": [
        {
            "dish_id": 1, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dish_id": 2, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dish_id": 3, 
            "num": 2, 
            "unit_price": 10
        }
    ]
}
    * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
        
        
    * DELETE 删除某个订单
        * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }      
    
    
    * PUT 更新订单状态
        - 请求
      {
      "status" : "rejected"
      }    
        * 成功返回 { "isSuccess": { "status": 200, "detail": "" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "" } }      
    



* /orders/store/{store_id}
    * GET 获取某个商家的所有订单信息
      - 成功返回
```json
[
    {
        "order_id": 123, 
        "user_id": 123, 
        "sotre_id": 123, 
        "status": "paid", 
        "num_dish": 3, 
        "address": 123, 
        "created_time":"2018-11-11",
        "dishs_info": [
            {
                "dish_id": 1, 
                "num": 2, 
                "unit_price": 10
            }
        ]
    }, 
    {
        "order_id": 123,
        "user_id": 123, 
        "sotre_id": 123, 
        "status": "paid", 
        "num_dish": 3, 
        "created_time":"2018-11-11",
        "address": 123, 
        "dishs_info": [
            {
                "dish_id": 1, 
                "num": 2, 
                "unit_price": 10
            }
        ]
    }
]

```
  - 失败返回
```json

     { "isSuccess": { "status": 500, "detail": "failed" } }
```        


* /dishes
    * POST 新建菜式
      - 请求
    {
    "name": "jj", 
    "store_id": 123, 
    "type_id": 123, 
    "description": "dish", 
    "or_price": 22, 
    "curr_price": 20, 
    "image_path": "/images/xxx.jpg"
}

      }    
        * 成功返回 { "isSuccess": { "status": 200, "detail": "created" } }
        * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
* /dishes/{dish_id}
    * GET 获取某个菜式的信息
      * 成功返回 {     
        "name": "jj", 
    "store_id": 123, 
    "type_id": 123, 
    "description": "dish", 
    "or_price": 22, 
    "curr_price": 20, 
    "image_path": "/images/xxx.jpg",
    "isSuccess": { "status": 200, "detail": "created" } }
      * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
    * DELETE 删除某个菜式

      * 成功返回 { "isSuccess": { "status": 200, "detail": "deleted" } }
      * 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }     
    
    * PUT 更新某个菜式
    
* /dishes/store/{store_id}
    * GET 获取某个商家的所有菜单信息
          - 成功返回 [{"name": "jj", "store_id": 123, "type_id": 123, "description": "dish", "or_price": 22, "curr_price": 20, "image_path": "/images/xxx.jpg","isSuccess": { "status": 200, "detail": "created" } }, {"name": "jxj", "store_id": 1223, "type_id": 123, "description": "dish", "or_price": 22, "curr_price": 20, "image_path": "/images/xxx.jpg","isSuccess": { "status": 200, "detail": "created" } }]
          - 失败返回 { "isSuccess": { "status": 500, "detail": "failed" } }
