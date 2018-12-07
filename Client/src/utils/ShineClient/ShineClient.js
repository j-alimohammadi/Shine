import ShineHttpClient from '../ShineHttpClient'

const PATH_BASE = 'http://localhost:8090'
const QUESTIONS_PATH = 'question'
const ANSWER_PATH = 'answer'

class ShineClient {

  /////////////////////////////////////////////
  //            Post Management
  /////////////////////////////////////////////
  // static getPosts (pageSize) {
  //   const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}`
  //
  //   return ShineHttpClient.getData(url)
  // }

  /////////////////////////////////////////////
  //            Question Management
  /////////////////////////////////////////////

  static findQuestion (questionId) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}`

    return ShineHttpClient.getData(url)
  }

  static findQuestions (page, sortBy, pageSize = 15) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}?pageSize=${pageSize}&page=${page}&sortBy=${sortBy}`

    return ShineHttpClient.getData(url)
  }

  static createQuestion (questionObject) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}`
    return ShineHttpClient.postData(url, questionObject)

  }

  static acceptAnswer (questionId, answerId) {
    const url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/accept/answer/${answerId}`
    return ShineHttpClient.putData(url)
  }

  static voteQuestion (questionId, isVotingUp) {
    let url

    if (isVotingUp) {
      url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/increment`
    } else {
      url = `${PATH_BASE}/${QUESTIONS_PATH}/${questionId}/vote/decrement`
    }

    return ShineHttpClient.putData(url, '')

  }

  /////////////////////////////////////////////
  //            Answer Management
  /////////////////////////////////////////////

  static findAnswersForQuestion (questionId) {
    const url = `${PATH_BASE}/${ANSWER_PATH}/question/${questionId}`
    let answers = ShineHttpClient.getData(url)

    return answers
  }

  static createAnswer (answerObject) {
    const url = `${PATH_BASE}/${ANSWER_PATH}`
    return ShineHttpClient.postData(url, answerObject)

  }

  static voteAnswer (answerId, isVotingUp) {
    let url

    if (isVotingUp) {
      url = `${PATH_BASE}/${ANSWER_PATH}/${answerId}/vote/increment`
    } else {
      url = `${PATH_BASE}/${ANSWER_PATH}/${answerId}/vote/decrement`
    }

    return ShineHttpClient.putData(url, '')

  }

}

export default ShineClient