export class ShineResponse {
  constructor () {
    this._objectId = null
    this._updateTime = null
    this._createTime = null
    this._data = null
  }

  get objectId () {
    return this._objectId
  }

  set objectId (value) {
    this._objectId = value
  }

  get updateTime () {
    return this._updateTime
  }

  set updateTime (value) {
    this._updateTime = value
  }

  get createTime () {
    return this._createTime
  }

  set createTime (value) {
    this._createTime = value
  }

  get data () {
    return this._data
  }

  set data (value) {
    this._data = value
  }
}

export class ShineResponseParser {
  static getData (denaResponse) {
    let data = {}

    for (const key of Object.keys(denaResponse)) {
      if (key !== 'object_id' && key !== 'object_uri'
        && key !== 'update_time' && key !== 'create_time') {

        data[key] = denaResponse[key]
      }
    }

    return JSON.stringify(data)
  }

  static isResponseOk (JSONResponse) {
    return (JSONResponse.status >= 200 && JSONResponse.status < 300)
  }
}

