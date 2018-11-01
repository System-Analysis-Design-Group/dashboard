
```java

    //请求商家列表
    public ArrayList(Shop) getShops(Location loc);
    //请求商家的菜式列表
    public ArrayList(Dish) getDishes(Shop s);
    //请求某用户的订单
    public ArrayList(Order) getOrders(User user);
    //请求某商家的订单
    public ArrayList(Order) getOrders(Shop s);
    //请求某菜式的评价
    public ArrayList(Comment) getComments(Dish d);
    //请求某商家的评价
    public ArrayList(Comment) getComments(Shop s);


    //商家设置菜单
    public boolean setDishes(ArrayList(Dish));
    //管理订单
    public boolean manageOrder(Long OrderID, boolean isAccepted);
    //针对商家进行评价
    public boolean makeComment(String comment,Shop s);    
    //评价菜式
    public boolean makeComment(String comment,Dish d); 
    //回复评价
    public boolean makeComment(String comment,Comment c);
    //提交订单（付款成功后调用）
    public boolean submitOrder(Order o);   


```

