import React, { Component, Fragment } from 'react'
import ShineClient from '../../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../../utils/ShineClient/Response'
import Tag from '../Tag/Tag'
import { convertFromRaw, convertToRaw, EditorState } from 'draft-js'
import { Editor } from 'react-draft-wysiwyg'
import Vote from '../Vote/Vote'
import Search from '../../Search/Search'

class Answer extends Component {
  constructor (props) {
    super(props)

    this.state = {
      editorState: EditorState.createEmpty(),
      questionId: props.match.params.questionId,
      question: null,
      answers: [],
      questionVote: -1,
      answerVote: -1,
      answerBody: ''
    }

    // Event handler
    this.handleAnswerChange = this.handleAnswerChange.bind(this)
    this.handleQuestionVote = this.handleQuestionVote.bind(this)
    this.handleAnswerVote = this.handleAnswerVote.bind(this)
    this.handleSubmitFormAnswer = this.handleSubmitFormAnswer.bind(this)
    this.handleAcceptAnswer = this.handleAcceptAnswer.bind(this)

  }

  getQuestionDetail () {
    const {questionId} = this.state

    ShineClient.findQuestion(questionId)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((jsonData) => {
        this.setState({question: jsonData, questionVote: jsonData.vote})
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })
  }

  getAnswerForQuestion () {
    const {questionId} = this.state

    ShineClient.findAnswersForQuestion(questionId)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((answerData) => {
        let answers = []
        for (let answer of answerData) {
          let id = answer.id
          let vote = answer.vote
          let body = EditorState.createWithContent(convertFromRaw(answer.body))
          let isAnswerAccept = answer.is_answer_accept
          let newAnswer = {id: id, vote: vote, body: body, isAnswerAccept: isAnswerAccept}
          answers.push(newAnswer)

        }

        this.setState({answers: answers})
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })
  }

  handleAcceptAnswer (questionId, answerId, event) {
    event.preventDefault()

    ShineClient.acceptAnswer(questionId, answerId)
      .then((JSONResponse) => {

        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((acceptedAnswer) => {
        const answers = this.state.answers
        for (let answer of answers) {
          if (answer.id === acceptedAnswer.id) {
            answer.isAnswerAccept = true
          } else {
            answer.isAnswerAccept = false
          }
        }

        this.setState({answers: answers})

      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })
  }

  handleAnswerChange (editorState) {
    this.setState({
      editorState: editorState
    })
  };

  handleQuestionVote (questionId, isVotingUp, event) {

    ShineClient.voteQuestion(questionId, isVotingUp)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened')
        }
      })
      .then((jsonData) => {
        this.updateQuestionVote(jsonData)
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })

    event.preventDefault()
  }

  handleAnswerVote (answerId, isVotingUp, event) {
    ShineClient.voteAnswer(answerId, isVotingUp)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened')
        }
      })
      .then((jsonData) => {
        this.updateAnswerVote(jsonData)
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })

    event.preventDefault()
  }

  updateQuestionVote (updatedQuestion) {
    const newVote = updatedQuestion.vote
    this.setState({questionVote: newVote})
  }

  updateAnswerVote (updatedAnswer) {
    const answers = this.state.answers
    const foundIndex = answers.findIndex(element => {
      return element.id === updatedAnswer.id
    })

    answers[foundIndex].vote = updatedAnswer.vote

    this.setState({answers: answers})

  }

  handleSubmitFormAnswer (event) {
    event.preventDefault()

    const answerContent = convertToRaw(this.state.editorState.getCurrentContent())
    const questionId = this.state.questionId

    const answerObject = {}
    answerObject['body'] = answerContent
    answerObject['question_id'] = questionId

    ShineClient.createAnswer(answerObject)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          this.setState({toQuestionPage: true, editorState: EditorState.createEmpty()})
          this.getAnswerForQuestion()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get table information. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })

  }

  componentDidMount () {
    this.getQuestionDetail()
    this.getAnswerForQuestion()
  }

  render () {
    const {editorState} = this.state

    let questionVote = ''
    let tags = ''
    let body = EditorState.createEmpty()
    let answerTag = ''
    let questionTitle = ''
    const answer = this.state.answers

    if (this.state.question) {
      questionVote =
        <Vote onChangeVote={this.handleQuestionVote} vote={this.state.questionVote} postId={this.state.question.id}/>
      tags = <Tag tags={this.state.question.tag_names}/>
      body = EditorState.createWithContent(convertFromRaw(this.state.question.body))
      questionTitle = this.state.question.title
      if (this.state.question.answer_count > 0) {
        answerTag =
          <div className="qa-part-a-list">
            <h2 id="a_list_title">{this.state.question.answer_count} Answer</h2>
            <div className="qa-a-list" id="a_list">

              {
                answer.map((item, index) => {
                  return (

                    <div className={'qa-a-list-item ' + (item.isAnswerAccept ? 'qa-a-list-item-selected' : '')}
                         key={item.id}>
                      <div className="qa-q-view-stats">
                        <Vote onChangeVote={this.handleAnswerVote} vote={item.vote} postId={item.id}/>
                      </div>
                      <div className="qa-a-item-main">
                        <form method="post" action="/">
                          <div className="qa-a-selection">
                            <button title="Click to select as best answer" name="a8_doselect" type="submit"
                                    className={item.isAnswerAccept ? 'qa-a-unselect-button' : 'qa-a-select-button'}
                                    onClick={this.handleAcceptAnswer.bind(this, this.state.question.id, item.id)}>
                              <span className="fa fa-check"/>
                            </button>
                          </div>
                          <div className="qa-a-item-content qa-post-content">
                            <div itemProp="text">
                              <Editor
                                toolbarHidden="true"
                                readOnly="true"
                                editorState={item.body}
                                editorClassName="answer-editor"
                                onEditorStateChange={this.handleAnswerChange}
                              />

                            </div>
                          </div>
                          <span className="qa-a-item-avatar-meta">
                            <span className="qa-a-item-meta">
                            <a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask&amp;show=2#a2"
                               className="qa-a-item-what">answered</a>
                            <span className="qa-a-item-when">
                            <span className="qa-a-item-when-data"><time itemProp="dateCreated"
                                                                        dateTime="2018-10-21T11:19:06+0000" title=""
                                                                        data-original-title="2018-10-21T11:19:06+0000">Oct 21</time></span>
                            </span>
                              <span className="qa-a-item-who">
                                <span className="qa-a-item-who-pad">by </span>
                                  <span className="qa-a-item-who-data"><span itemProp="author" itemScope="">
                                    <span itemProp="name">name</span>
                                  </span>
                                </span>
                              </span>
                            </span>
                            </span>
                          <div className="qa-a-item-buttons">
                            <button name="a11_doedit" title="Edit this answer" type="submit"
                                    className="qa-form-light-button qa-form-light-button-edit">edit
                            </button>
                            <button name="a11_dohide" title="Hide this answer" type="submit"
                                    className="qa-form-light-button qa-form-light-button-hide">hide
                            </button>
                            <button name="a11_dofollow" title="Ask a new question relating to this answer" type="submit"
                                    className="qa-form-light-button qa-form-light-button-follow">ask related question
                            </button>
                            <button name="a11_docomment" title="Add a comment on this answer" type="submit"
                                    className="qa-form-light-button qa-form-light-button-comment">comment
                            </button>
                          </div>
                        </form>
                      </div>

                      <div className="qa-a-item-clear clearfix">
                      </div>
                    </div>
                  )
                })
              }
            </div>

          </div>
      }

    }

    return (

      <Fragment>
        <main className="donut-masthead">
          <div className="container">
            <div className="page-title">
              <h1>
                {
                  questionTitle
                }
              </h1>
            </div>
          </div>
        </main>

        <div className="qa-body-wrapper container">
          <div className="qa-main-shadow clearfix">
            <div className="qa-main-wrapper clearfix row">
              <div className="qa-main col-md-9 col-xs-12 pull-left">
                <div className="qa-template-question qa-body-js-on">
                  <div className="qa-part-q-view">
                    <div className="qa-q-view" id="q1">
                      <form method="post">
                        <div className="qa-q-view-stats">
                          {questionVote}
                        </div>
                      </form>
                      <div className="qa-q-view-main">
                        <form method="post">
                          <div className="qa-q-view-content qa-post-content">
                            <div itemProp="text">
                              <Editor
                                toolbarHidden="true"
                                readOnly="true"
                                editorState={body}
                                editorClassName="answer-editor"
                              />
                            </div>
                          </div>
                          {tags}
                          <span className="qa-q-view-avatar-meta">
                                <span className="qa-q-view-meta">
                                <a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask"
                                   className="qa-q-view-what">asked</a>
                                <span className="qa-q-view-when">
                                <span className="qa-q-view-when-data"><time dateTime="2018-10-21T11:13:02+0000" title=""
                                                                            data-original-title="2018-10-21T11:13:02+0000">Oct 21</time></span>
                                </span>
                                <span className="qa-q-view-who">
                                <span className="qa-q-view-who-pad">by </span>
                                <span className="qa-q-view-who-data"><span itemProp="author" itemScope=""
                                                                           itemType="http://schema.org/Person"><span
                                  itemProp="name">me</span></span></span>
                                </span>
                                </span>
                                </span>
                          <div className="qa-q-view-buttons">
                            <button name="q_doedit" title="" type="submit"
                                    className="qa-form-light-button qa-form-light-button-edit"
                                    data-original-title="Edit this question">edit
                            </button>
                            <button name="q_doclose" title="" type="submit"
                                    className="qa-form-light-button qa-form-light-button-close"
                                    data-original-title="Close this question to any new answers">close
                            </button>
                            <button name="q_dohide" title=""
                                    type="submit" className="qa-form-light-button qa-form-light-button-hide"
                                    data-original-title="Hide this question">hide
                            </button>
                            <button name="q_doanswer" id="q_doanswer" title="" type="submit"
                                    className="qa-form-light-button qa-form-light-button-answer"
                                    data-original-title="Answer this question">answer
                            </button>
                          </div>
                        </form>
                        <div className="qa-q-view-c-list" style={{display: 'none'}} id="c1_list">
                        </div>

                        <div className="qa-c-form">
                        </div>

                      </div>

                      <div className="qa-q-view-clear clearfix">
                      </div>
                    </div>
                  </div>
                  <div className="qa-part-a-form">
                    <div className="qa-a-form" id="anew">
                      <h2>Your answer</h2>
                      <form method="post" onSubmit={this.handleSubmitFormAnswer}>
                        <table className="qa-form-tall-table">
                          <tbody>
                          <tr>
                            <td className="qa-form-tall-data">
                              <Editor
                                editorState={editorState}
                                editorClassName="ask-editor"
                                onEditorStateChange={this.handleAnswerChange}
                              />
                            </td>
                          </tr>
                          </tbody>
                          <tbody>
                          <tr>
                            <td colSpan="1" className="qa-form-tall-buttons">
                              <button type="submit" className="qa-form-tall-button qa-form-tall-button-answer">Add
                                answer
                              </button>
                            </td>
                          </tr>
                          </tbody>
                        </table>
                      </form>
                    </div>
                  </div>
                  {answerTag}
                </div>

              </div>
              <div className="qa-sidepanel col-md-3 col-xs-12 pull-right">
                <Search/>
              </div>
            </div>
          </div>
        </div>
      </Fragment>

    )
  }
}

export default Answer