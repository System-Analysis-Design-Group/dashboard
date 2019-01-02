import fetch from '@/api/fetch.js'

function login({ username, password }) {
  return fetch({
    url: '/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

function logout(token) {
  let fetchData = {
    url: '/token',
    method: 'delete'
  }
  return fetch(fetchData)
}

export default {
  login,
  logout
}