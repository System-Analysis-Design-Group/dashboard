
// 包括系统通知/售后通知/商店通知
var Notification = {
  type: "system/sales/store",
  from: "admin/salesId/storeId",
  createdAt: new Date(),
  message: {
    // 可以随意组合的消息
    img: "...",
    text: "...",
    url: "..."
  }
}

// 在线对话, updatedAt时间过后半个小时可以新建新的session
var Session = {
  createdAt: new Date(),
  updatedAt: new Date(),
  type: "store/sales/system/...",
  serviceId: "storeId/salesId/system/...",
  customerId: "userId",
  messages: [{
    customer: true,
    message: {
      // 可以随意组合的消息
      img: "...",
      text: "...",
      url: "..."
    }
  }]
}
