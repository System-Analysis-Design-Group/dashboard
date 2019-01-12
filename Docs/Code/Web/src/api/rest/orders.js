import FormUtil from '@/utils/form'
import fetch from '@/api/fetch.js'

const OrdersServicePrefix = '/OrdersService'

class OrdersService {
  static (
    UnpaidStatus = 0,
    PaidedStatus = 1,
    AcceptedStatus = 2,
    RejectedStatus = 3,
  )

  static getAllOrderOfStore (storeId) {
    return fetch({
      url: `${OrdersServicePrefix}/orders/store/${storeId}`,
      method: 'get',
    })
  }
  static _updateOrderStatus (orderId, status) {
    return fetch({
      url: `${OrdersServicePrefix}/orders/?id=${orderId}&status=${status}`,
      method: 'put',
    })
  }
  static acceptOrder (orderId) {
    return _updateOrderStatus(orderId, this.AcceptedStatus)
  }
  static rejectOrder (orderId) {
    return _updateOrderStatus(orderId, this.RejectedStatus)
  }
  static getOrderDetail (orderId) {
    return fetch({
      url: `${OrdersServicePrefix}/orders/${orderId}`,
      method: 'get',
    })
  }

  // 以下API用于管理员
  static deleteOrder (orderId) {
    return fetch({
      url: `${OrdersServicePrefix}/orders/${orderId}`,
      method: 'delete',
    })
  }

  // 以下API是客户端使用的，前端可以做测试
  // 例子: {"userID":123,"storeID":123,"addressID":123,"numGoods":2,"state":0,"goodsInfo":[{"num":2,"unitPrice":20.0,"dishId":1},{"num":1,"unitPrice":15.0,"dishId":2}]}}
  static createOrder (formData) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "userID", "storeID", "addressID", "numGoods", "state", "goodsInfo",
    ])
    return fetch({
      url: `${OrdersServicePrefix}/orders`,
      method: 'post',
      data: uploadData,
    })
  }

}

export default OrdersService