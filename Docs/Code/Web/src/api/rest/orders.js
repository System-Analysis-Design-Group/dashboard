import FormUtil from '@/utils/form'
import fetch from '@/api/fetch.js'

const OrdersServicePrefix = '/OrdersService'

class OrdersService {
  static UnpaidStatus = 0
  static PaidedStatus = 1
  static AcceptedStatus = 2
  static RejectedStatus = 3

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
    return this._updateOrderStatus(orderId, this.AcceptedStatus)
  }
  static rejectOrder (orderId) {
    return this._updateOrderStatus(orderId, this.RejectedStatus)
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