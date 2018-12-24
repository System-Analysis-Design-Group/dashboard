> 说明，除了特殊说明以外，请求失败一律返回：
>```json
>{
>    "code": 500, 
>    "message": "xx" //错误信息
>}
>```



# /Accounts/Signin
## POST 登录
- 发送 { "username": "James", "password": "abcd" }
-  成功返回 
```json
{
    "obj": {
        "username": "James", 
        "id": 123, 
        "rolename": "default"
    }, 
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 
```json
{
    "code": 500, 
    "message": "xx"
}
```


# /Accounts/Signup
## POST 新建一个账户
- 发送 { "username": "James", "password": "abcd" }
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】



# /Accounts/Customer/{userId}

## GET 获取某个账户信息
- 成功返回 { "info": { "id": 123, "username":"James", "rolename": "default" }, "isSuccess": { "status": 200, "detail": "done" } }
- 失败返回 【略】


## DELETE 删除一个账户

- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】

# /Accounts/Permission/{uerId}
## GET 获取某个账户权限
- 发送 { "userId":123 }
- 成功返回 { "info": { "role_ids":[1,2] ,"rolenames": [ "default", "admin" ] }, "isSuccess": { "status": 200, "detail": "done" } }
- 失败返回 【略】

# /Accounts/Permission/
## POST 给某个用户新建权限
- 发送 { "userId": 123, "role_id": 123 }
- 失败返回 【略】
- 成功返回 

```json
{
    "code": 200, 
    "message": "ok"
}
```
# /Accounts/Permission/{uerId}/{role_id}
## DELETE 给某个用户删除权限
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】
## PUT 修改一个账户的权限

# /Accounts/Stores
## GET 所有商家
- 发送 { "type": [ "Chinese", "snack" ],"sum":5 }
- 返回 { "num_found":5, "storeId": [ 1, 2, 3, 4, 5 ], "isSuccess": { "status": 200, "detail": "done" } }
- 失败返回 【略】

## POST 新建商家
- 发送 { "name": "KFC", "phone": "123456789", "type": "Chinese food", "user\_id": 123, "address": "Wall Street", "longitude": 123.123, "latitude": 231.231, "cover": "" }
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】

# /Comments/{commentId}
## GET 获取某个评论
- 成功返回 { "content": "qnmb", "isSuccess": { "status": 200, "detail": "done" } }
- 失败返回 【略】
## DELETE 删除某个评论
- 失败返回 【略】
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```

## PUT 更新某个评论
- 发送 {"content":"new comment" }

```json
"obj": {
    "content": "sasa", 
}
```
- 失败返回 【略】
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```

<!-- ## POST 商家回应顾客评价，在此要判断是否商家请求的该接口
- 发送 {"storeId":123 }

        {"content":"new comment" } -->


# /Comments/store/{storeId}
## GET 获取某个商家所有评论
- 成功返回 { "info": { "commentId":12, "number": 3, "content": [ "abcd", "efgh", "ijk" ] }, "isSuccess": { "status": 200, "detail": "done" } }
- 失败返回 【略】
## POST 给某个商家新建评论
- 发送 {"content": "fucking delicious" ,"userId" :12}
- 成功返回 
```json
{
    "code": "200", 
    "message": "ok", 
    "obj": {
        "id": "5c1b7f0a3a1d393eb097aa7f", 
        "type": "store", 
        "storeId": 1256, 
        "userId": 233, 
        "content": "sasa", 
        "dishId": null
    }
}
```
- 失败返回 【略】



# /Comments/dish/{dishId}
## GET 获取某个菜式的所有评论 
- 成功返回 
```json

{
    "code": "200", 
    "message": "ok", 
    "obj": [
        {
            "id": "5c1bb2033a1d3959b8b6c4f0", 
            "type": "dish", 
            "storeId": null, 
            "userId": 244433, 
            "content": null, 
            "dishId": 1256
        }, 
        {
            "id": "5c1bb22c3a1d3959b8b6c4f1", 
            "type": "dish", 
            "storeId": 54, 
            "userId": 244433, 
            "content": "sadsdssa", 
            "dishId": 1256
        }
    ]
}
```
- 失败返回 【略】

## POST 新建对菜式的评论
- 发送
```json
{"content": "fucking delicious" ,"userId" :12, "storeId":12}
```
- 成功返回
```json
{
    "code": "200", 
    "message": "ok", 
    "obj": {
        "id": "5c1bb22c3a1d3959b8b6c4f1", 
        "type": "dish", 
        "storeId": 54, 
        "userId": 244433, 
        "content": "sadsdssa", 
        "dishId": 1256
    }
}
```

- 失败返回【略】

## DELETE 删除某个商家的某个菜式的评论
如 /Comments/{commentId}
## PUT 更新某个商家的某个菜式的评论
如 /Comments/{commentId}


# /orders
## POST 新建订单
- 请求
```json
{
    "userId": 123, 
    "sotre_id": 123, 
    "num_dish": 3, 
    "dishs_info": [
        {
            "dishId": 1, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dishId": 2, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dishId": 3, 
            "num": 2, 
            "unit_price": 10
        }
    ]
}
```
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】
# /orders/{order_id}
## GET 获取某个订单的信息
**status 四种状态 unpaid, paided, accepted, rejected**

- 成功返回
```json
{
    "userId": 123, 
    "sotre_id": 123, 
    "status": "paid", 
    "num_dish": 3, 
    "address": 123, 
    "created_time":"2018-11-11",
    "dishs_info": [
        {
            "dishId": 1, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dishId": 2, 
            "num": 2, 
            "unit_price": 10
        }, 
        {
            "dishId": 3, 
            "num": 2, 
            "unit_price": 10
        }
    ]
}
```
- 失败返回 【略】
        
        
## DELETE 删除某个订单
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】      
    
    
## PUT 更新订单状态
        - 请求
      {
      "status" : "rejected"
      }    
- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 { "isSuccess": { "status": 500, "detail": "" } }      
    


# /orders/store/{storeId}
## GET 获取某个商家的所有订单信息
      - 成功返回
```json
[
    {
        "order_id": 123, 
        "userId": 123, 
        "sotre_id": 123, 
        "status": "paid", 
        "num_dish": 3, 
        "address": 123, 
        "created_time":"2018-11-11",
        "dishs_info": [
            {
                "dishId": 1, 
                "num": 2, 
                "unit_price": 10
            }
        ]
    }, 
    {
        "order_id": 123,
        "userId": 123, 
        "sotre_id": 123, 
        "status": "paid", 
        "num_dish": 3, 
        "created_time":"2018-11-11",
        "address": 123, 
        "dishs_info": [
            {
                "dishId": 1, 
                "num": 2, 
                "unit_price": 10
            }
        ]
    }
]

```
  - 失败返回 【略】
     


# /dishes
## POST 新建菜式
- 请求
```json
{
    "name": "jj", 
    "storeId": 123, 
    "type_id": 123, 
    "description": "dish", 
    "or_price": 22, 
    "curr_price": 20, 
    "image_path": "/images/xxx.jpg"
}
```

- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】

# /dishes/{dishId}
## GET 获取某个菜式的信息
- 成功返回 
```json
{     
        "name": "jj", 
    "storeId": 123, 
    "type_id": 123, 
    "description": "dish", 
    "or_price": 22, 
    "curr_price": 20, 
    "image_path": "/images/xxx.jpg",
    "isSuccess": { "status": 200, "detail": "created" } 
}
```
- 失败返回 【略】
## DELETE 删除某个菜式

- 成功返回 
```json
{
    "code": 200, 
    "message": "ok"
}
```
- 失败返回 【略】     
    
## PUT 更新某个菜式
    
# /dishes/store/{storeId}
## GET 获取某个商家的所有菜单信息
- 成功返回 
```json
[
    {
        "name": "jj", 
        "storeId": 123, 
        "type_id": 123, 
        "description": "dish", 
        "or_price": 22, 
        "curr_price": 20, 
        "image_path": "/images/xxx.jpg", 
        "isSuccess": {
            "status": 200, 
            "detail": "created"
        }
    }, 
    {
        "name": "jxj", 
        "storeId": 1223, 
        "type_id": 123, 
        "description": "dish", 
        "or_price": 22, 
        "curr_price": 20, 
        "image_path": "/images/xxx.jpg", 
        "isSuccess": {
            "status": 200, 
            "detail": "created"
        }
    }
]
```
- 失败返回 【略】
