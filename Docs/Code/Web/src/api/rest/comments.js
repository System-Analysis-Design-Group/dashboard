import FormUtil from '@/utils/form'
import fetch from '@/api/fetch.js'

const CommentsServicePrefix = '/CommentsService'

class CommentsService {

  static getCommentsByStore (storeId) {
    return fetch({
      url: `${CommentsServicePrefix}/Comments/store/${storeId}`,
      method: 'get',
    })
  }
  static getCommentsByDish (dishId) {
    return fetch({
      url: `${CommentsServicePrefix}/Comments/dish/${dishId}`,
      method: 'get',
    })
  }
}

export default CommentsService