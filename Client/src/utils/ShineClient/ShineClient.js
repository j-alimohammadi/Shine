import ShineHttpClient from '../ShineHttpClient'

const PATH_BASE = 'http://localhost:8090'
const QUESTIONS_PATH = 'question'

class ShineClient {

  /////////////////////////////////////////////
  //            Record Management
  /////////////////////////////////////////////
  static findQuestions (offset, limit) {
    let url = `${PATH_BASE}/${QUESTIONS_PATH}`

    return ShineHttpClient.getData(url)
  }

  static updateRecord (tableName, updateObject) {
    const url = `${PATH_BASE}/${tableName}?loadRelation=true`
    return ShineHttpClient.putData(url, updateObject).then(response => response.json())
  }

  static createRecord (tableName, newObject) {
    const url = `${PATH_BASE}/${tableName}?loadRelation=true`
    return ShineHttpClient.postData(url, newObject).then(response => response.json())
  }

  static deleteRecord (tableName, objectId) {
    const url = `${PATH_BASE}/${tableName}/${objectId}`
    return ShineHttpClient.deleteData(url).then(response => response.json())
  }

}

export default ShineClient