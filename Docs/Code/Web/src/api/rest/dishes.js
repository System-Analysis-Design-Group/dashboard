import fetch from '@/api/fetch.js'
import FormUtil from '@/utils/form'

const DishesServicePrefix = "/DishesService"

class DishesService {
  static ServicePrefix = DishesServicePrefix;

  static _uploadDishes(formData, method) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "id", "name", "storeId", "typeName", "description", "orPrice", "cuPrice", "imagePath", 
    ])
    return fetch({
      url: `${DishesServicePrefix}/dishes`,
      method: method,
      data: uploadData,
    })
  }

  static getAllDishes (storeId) {
    return fetch({
      url: `${DishesServicePrefix}/dishes/store/${storeId}`,
      method: 'get',
    })
  }
  static getDishById (dishId) {
    return fetch({
      url: `${DishesServicePrefix}/dishes/${dishId}`,
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