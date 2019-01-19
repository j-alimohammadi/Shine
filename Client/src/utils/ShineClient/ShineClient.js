import ShineHttpClient from './ShineHttpClient'

const BASE_PATH = 'http://localhost:8090'
const QUESTIONS_PATH = 'question'
const ANSWER_PATH = 'answer'
const POST_PATH = 'post'
const LOGIN_PATH = 'api/user/login'

class ShineClient {

  /////////////////////////////////////////////
  //            Post Management
  /////////////////////////////////////////////
  static findPosts (query, page, pageSize = 15) {
    const url = `${BASE_PATH}/${POST_PATH}/search?pageSize=${pageSize}&page=${page}&q=${query}`

    return ShineHttpClient.getData(url)
  }

  /////////////////////////////////////////////
  //            SearchResult Management
  /////////////////////////////////////////////

  static findQuestion (questionId) {
    const url = `${BASE_PATH}/${QUESTIONS_PATH}/${questionId}`

    return ShineHttpClient.getData(url)
  }

  static findQuestions (page, sortBy, pageSize = 3) {
    const url = `${BASE_PATH}/${QUESTIONS_PATH}?pageSize=${pageSize}&page=${page}&sortBy=${sortBy}`

    return ShineHttpClient.getData(url)
  }

  static createQuestion (questionObject) {
    const url = `${BASE_PATH}/${QUESTIONS_PATH}`
    return ShineHttpClient.postData(url, questionObject)

  }

  static acceptAnswer (questionId, answerId) {
    const url = `${BASE_PATH}/${QUESTIONS_PATH}/${questionId}/accept/answer/${answerId}`
    return ShineHttpClient.putData(url)
  }

  static voteQuestion (questionId, isVotingUp) {
    let url

    if (isVotingUp) {
      url = `${BASE_PATH}/${QUESTIONS_PATH}/${questionId}/vote/increment`
    } else {
      url = `${BASE_PATH}/${QUESTIONS_PATH}/${questionId}/vote/decrement`
    }

    return ShineHttpClient.putData(url, '')

  }

  /////////////////////////////////////////////
  //            Answer Management
  /////////////////////////////////////////////

  static findAnswersForQuestion (questionId) {
    const url = `${BASE_PATH}/${ANSWER_PATH}/question/${questionId}`
    let answers = ShineHttpClient.getData(url)

    return answers
  }

  static createAnswer (answerObject) {
    const url = `${BASE_PATH}/${ANSWER_PATH}`
    return ShineHttpClient.postData(url, answerObject)

  }

  static voteAnswer (answerId, isVotingUp) {
    let url

    if (isVotingUp) {
      url = `${BASE_PATH}/${ANSWER_PATH}/${answerId}/vote/increment`
    } else {
      url = `${BASE_PATH}/${ANSWER_PATH}/${answerId}/vote/decrement`
    }

    return ShineHttpClient.putData(url, '')

  }

  /////////////////////////////////////////////
  //            User Management
  /////////////////////////////////////////////
  static login (userName, password) {
    const url = `${BASE_PATH}/${LOGIN_PATH}`

      const userCredential = {}
      userCredential['userName'] = userName
      userCredential['password'] = password


    return ShineHttpClient.postData(url, userCredential)
  }

}

export default ShineClient