import fetch from '@/api/fetch.js'
import FormUtil from '@/utils/form'

const DishesServicePrefix = "/DishesService"

class DishesService {
  static ServicePrefix = DishesServicePrefix;

  static _uploadDishes(formData, method) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "name", "storeId", "typeName", "description", "orPrice", "cuPrice",
    ])
    return fetch({
      url: `${DishesServicePrefix}/dishes`,
      method: method,
      data: uploadData,
    })
  }

  static fetchAllDishes(storeId) {
    return fetch({
      url: `${DishesServicePrefix}/dishes/store/${storeId}`,
      method: 'get',
    })
  }
  static createDishes (formData) {
    return this._uploadDishes(formData, 'post')
  }
  static updateDishes (formData) {
    return this._uploadDishes(formData, 'put')
  }
  static deleteDishes (dishId) {
    return fetch({
      url: `${DishesServicePrefix}/dishes/${dishId}`,
      method: 'delete',
    })
  }
}

export default DishesService