// 商品评价
var Comment = {
  createdAt: new Date(),
  type: "store/goods/...",
  serviceId: "storeId/goodsId/...",
  customerId: "userId",
  stars: [{
    type: "taste/pack/...",
    star: 1/2/3/4/5
  }],
  message: {
    imgs: [],
    text: ""
  },
  replies: [{
    type: "store/customer",
    from: "storeId/customerId",
    imgs: [],
    text: ""
  }]
}