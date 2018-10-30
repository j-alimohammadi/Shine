import ShineHttpClient from '../ShineHttpClient'

const PATH_BASE = 'http://localhost:8090'
const QUESTIONS_PATH = 'question'

class ShineClient {

  /////////////////////////////////////////////
  //            Question Management
  /////////////////////////////////////////////
  static findQuestions (offset, limit) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}`

    return ShineHttpClient.getData(url)
  }

  static voteUp (questionId) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/increment`
    return ShineHttpClient.putData(url, '')

  }

  static voteDown (questionId) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/decrement`
    return ShineHttpClient.putData(url, '')

  }

}

export default ShineClient