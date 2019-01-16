import axios from 'axios'
import Cache from '@/utils/cache';
import { Message, MessageBox } from 'element-ui'

const TOKEN_KEY = window.config.tokenKey
const API_PREFIX = window.config.apiPrefix
const WHITE_LIST = ['/login']

const fetch = axios.create({
  timeout: 30 * 60 * 1000,
})

// 请求拦截器
fetch.interceptors.request.use(
  (request) => {
    const baseUrl = request.url || ''
    // 添加token
    if (WHITE_LIST.indexOf(baseUrl) === -1 && Cache.getToken()) {
      if (!request.headers[TOKEN_KEY]) {
        request.headers[TOKEN_KEY] = "Bearer " + Cache.getToken()
      }
    }
    request.url = `${API_PREFIX}${baseUrl}`
    return request
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
fetch.interceptors.response.use(
  (response) => {
    if (response.data.code == 500) {
      return Promise.reject(new Error(response.data.message))
    }
    return response
  },
  (error) => {
    if (error.response && error.response.status && error.response.status === 401 && location.href.slice(-2) !== '#/') {
      Cache.sessionClear()
      MessageBox.alert('该会话已过时，请重新登录', {
        confirmButtonText: '知道了',
        callback: () => {
          location.href = '/#/'
        },
        showClose: false
      })
    } else if (error.response && error.response.status && (error.response.status === 500 || error.response.status === 504)) { //  && error.response.data.message === null
      Message.error({ message: '服务器崩溃，系统无法响应，请联系管理员处理' })
    }
    return Promise.reject(error)
  }
)

export default fetch