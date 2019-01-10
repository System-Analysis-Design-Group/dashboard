class FormUtil {
  static isContainAttrs(obj, attrs) {
    for (const key of attrs) {
      if (obj[key] === undefined || obj[key] === null) {
        return false
      }
    }
    return true
  }
  static assignTo(from, to, attrs = Object.keys(from)) {
    for (const key of attrs) {
      if (from.hasOwnProperty(key)) {
        to[key] = this.clone(from[key])
      }
    }
  }
  static clone(obj) {
    return JSON.parse(JSON.stringify(obj))
  }
}

export default FormUtil