import FormUtil from '@/utils/form'
import fetch from '@/api/fetch.js'

const OrdersServicePrefix = '/OrdersService'

class OrdersService {
  static (
    UnpaidStatus = "unpaid",
    PaidedStatus = "paided",
    AcceptedStatus = "accepted",
    RejectedStatus = "rejected",
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
      method: 'put'
    })
  }
  static acceptOrder (orderId) {
    return _updateOrderStatus(orderId, this.AcceptedStatus)
  }
  static rejectOrder (orderId) {
    return _updateOrderStatus(orderId, this.RejectedStatus)
  }
}

export default OrdersService