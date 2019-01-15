import Cache from './cache'
const InfoKey = window.config.infoKey

class CacheProxy {
  constructor (proxyKey) {
    this._key = proxyKey
    this._val = null
  }

  getVal () {
    if (!this._val) {
      this._val = Cache.sessionGet(this._key)
    }
    return this._val
  }
  setVal (val) {
    this._val = val
    Cache.sessionSet(this._key, this._val)
  }
  removeKey () {
    this._val = null
    Cache.sessionRemove(this._key)
  }
}

class UserUtils {
  static RoleCustomer = "customer"
  static RoleBusiness = "owner"
  static userInfoProxy = new CacheProxy(InfoKey)
  
  static setUserInfo (info) {
    this.userInfoProxy.setVal(info)
  }
  static removeUserInfo () {
    this.userInfoProxy.removeKey()
  }
  static getStoreId () {
    let userInfo = this.userInfoProxy.getVal()
    return userInfo && userInfo.rolename === this.RoleBusiness ? userInfo.store_id[0] : null
  }
  static getUserId () {
    let userInfo = this.userInfoProxy.getVal()
    return userInfo ? userInfo.id : null
  }
  static isBusiness () {
    let userInfo = this.userInfoProxy.getVal()
    return userInfo && userInfo.rolename === this.RoleBusiness
  }
  static getUserInfo () {
    return this.userInfoProxy.getVal()
  }
}

export default UserUtils