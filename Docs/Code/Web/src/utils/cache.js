const formValueObj = value => ({ value })
const getRealVal = obj => obj && obj.value
const tokenKey = window.config.tokenKey
const infoKey = window.config.infoKey
const Cache = {
  sessionGet(key) {
    try {
      return getRealVal(JSON.parse(window.sessionStorage.getItem(key)))
    } catch (error) {
      console.error(error)
    }
  },
  sessionGetOnce(key) {
    const data = getRealVal(JSON.parse(window.sessionStorage.getItem(key)))
    window.sessionStorage.removeItem(key)
    return data
  },
  sessionSet(key, value) {
    window.sessionStorage.setItem(key, JSON.stringify(formValueObj(value)))
  },
  sessionRemove(key) {
    window.sessionStorage.removeItem(key)
  },
  sessionClear() {
    window.sessionStorage.clear()
  },
  localGet(key) {
    try {
      return getRealVal(JSON.parse(window.localStorage.getItem(key)))
    } catch (error) {
      console.error(error)
    }
  },
  localSet(key, value) {
    window.localStorage.setItem(key, JSON.stringify(formValueObj(value)))
  },
  localRemove(key) {
    window.localStorage.removeItem(key)
  },
  localClear() {
    window.localStorage.clear()
  },
  getToken() {
    return this.sessionGet(tokenKey)
  },
  setToken(value) {
    this.sessionSet(tokenKey, value)
  },
  removeToken() {
    this.sessionRemove(tokenKey)
  }
}

export default Cache
