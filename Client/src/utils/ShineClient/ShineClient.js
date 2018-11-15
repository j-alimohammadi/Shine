import ShineHttpClient from '../ShineHttpClient'

const PATH_BASE = 'http://localhost:8090'
const QUESTIONS_PATH = 'question'

class ShineClient {

  /////////////////////////////////////////////
  //            Question Management
  /////////////////////////////////////////////

  static findQuestion (questionId) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}`

    return ShineHttpClient.getData(url)
  }

  static findQuestions (offset, limit) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}`

    return ShineHttpClient.getData(url)
  }

  static vote (questionId, isVotingUp) {
    let url

    if (isVotingUp) {
      url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/increment`
    } else {
      url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/decrement`
    }

    return ShineHttpClient.putData(url, '')

  }

  static createQuestion (questionObject) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}`
    return ShineHttpClient.postData(url, questionObject)

  }

}

export default ShineClient