import _promise from 'babel-runtime/core-js/promise'
window.Promise = _promise

Promise.return = function (data) {
  return new Promise((resovle, reject) => resovle({data: { obj: data }}))
}

class Mocker {
  static getAllDishes() {
    return Promise.return([{
      "id": 2,
      "name": "jj",
      "storeId": 123,
      "typeName": "123",
      "description": "dish",
      "orPrice": 22.0,
      "cuPrice": 20.0,
      "imagePath": "http://element-cn.eleme.io/static/hamburger.50e4091.png"
    },
    {
      "id": 7,
      "name": "jjjj",
      "storeId": 123,
      "typeName": "1234",
      "description": "dish",
      "orPrice": 22.0,
      "cuPrice": 20.0,
      "imagePath": "http://element-cn.eleme.io/static/hamburger.50e4091.png"
    }])
  }
}

export default Mocker