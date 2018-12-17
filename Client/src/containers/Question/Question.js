import React, {Component, Fragment} from 'react'
import ShineClient from '../../utils/ShineClient/ShineClient'
import {ShineResponseParser} from '../../utils/ShineClient/Response'
import queryString from 'query-string'
import Tag from './Tag/Tag'
import Vote from './Vote/Vote'
import Pagination from "../Pagination/Pagination";

class Question extends Component {
    constructor(props) {
        super(props)

        this.state = {
            result: {
                posts: []
            }

        }

        // Event handler
        this.handleVote = this.handleVote.bind(this)
        this.updateQuestion = this.updateQuestion.bind(this)
        this.handleGetQuestion = this.handleGetQuestion.bind(this)


    }

    // Event handling

    handleVote(questionId, isVotingUp, event) {
        ShineClient.voteQuestion(questionId, isVotingUp)
          .then((JSONResponse) => {
              if (ShineResponseParser.isResponseOk(JSONResponse)) {
                  return JSONResponse.json()
              } else {
                  throw new Error('Something bad happened.')
              }
          })
          .then((jsonData) => {
              this.updateQuestion(jsonData)
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


    handleGetQuestion(page, sortBy, event) {
        this.getQuestions(page, sortBy)


    }

    updateQuestion(updatedQuestion) {

        const questions = this.state.result.posts
        const foundIndex = questions.findIndex(element => {
            return element.id === updatedQuestion.id
        })

        questions.splice(foundIndex, 1, updatedQuestion)
        this.setState({
            result: {
                posts: questions
            }
        })
    }

    componentDidMount() {
        const values = queryString.parse(this.props.location.search)
        const sortBy = values.sortBy === undefined ? 'recent' : values.sortBy
        this.setState({ sortBy: sortBy })
        this.getQuestions(1, sortBy)

    }

    getQuestions(page, sortBy) {
        ShineClient.findQuestions(page, sortBy)
          .then((JSONResponse) => {
              if (ShineResponseParser.isResponseOk(JSONResponse)) {
                  return JSONResponse.json()
              } else {
                  throw new Error('Something bad happened.')
              }
          })
          .then((jsonData) => {
              this.setState({ result: jsonData })
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


    render() {
        let questions = this.state.result.posts
        const sortBy = this.state.sortBy
        return (
          <Fragment>
              <div className="row">
                  <div className="pull-left col-xs-12 visible-xs side-toggle-button">
                      <button type="button" className="btn btn-primary btn-xs" data-toggle="offcanvas">
                          <i className="fa fa-chevron-right toggle-icon"/>
                      </button>
                  </div>
              </div>
              <div className="hidden-xs subnav-row clearfix">
                  <div className="qa-nav-sub">
                      <ul className="qa-nav-sub-list">
                          <li className={sortBy === 'recent' ? ' active ' : ''}>
                              <a href="/question?sortBy=recent"
                                 className='qa-nav-sub-link'>Recent</a>
                          </li>
                          <li className={sortBy === 'vote' ? ' active ' : ''}>
                              <a href="/question?sortBy=vote"
                                 className="qa-nav-sub-link">Most votes</a>
                          </li>
                          <li className={sortBy === 'answerCount' ? ' active ' : ''}>
                              <a href="/question?sortBy=answerCount" className="qa-nav-sub-link">Most answers</a>
                          </li>
                          <li className={sortBy === 'view' ? ' active ' : ''}>
                              <a href="/question?sortBy=view" className="qa-nav-sub-link">Most views</a>
                          </li>
                      </ul>
                      <div className="qa-nav-sub-clear clearfix">
                      </div>
                  </div>
              </div>
              <div className="qa-part-q-list">
                  <form method="post" action="./index.php?qa=questions">
                      <div className="qa-q-list">
                          {
                              questions.map((item, index) => {
                                  return (
                                    <div className="qa-q-list-item row" key={item.id} id={'q' + index}>
                                        <div className="qa-q-item-stats">
                                            <Vote onChangeVote={this.handleVote}
                                                  postId={item.id}
                                                  vote={item.vote}/>
                                            <span className="qa-a-count">
                         <span className="qa-a-count-data">{item.answer_count}</span>
                            <span className="qa-a-count-pad"> answer</span>
                        </span>
                                        </div>
                                        <div className="qa-q-item-main">
                                            <div className="qa-q-item-title">
                                                <a
                                                  href={`./answer/${item.id}/${item.question_url}`}>{item.question_title}</a>
                                            </div>
                                            <span className="qa-q-item-avatar-meta">
                          <span className="qa-q-item-meta">
                          <span className="qa-q-item-what">asked</span>
                          <span className="qa-q-item-when">
                          <span className="qa-q-item-when-data"> 4 days</span><span
                            className="qa-q-item-when-pad"> ago</span>
                          </span>
                          <span className="qa-q-item-who">
                          <span className="qa-q-item-who-pad">by </span>
                          <span className="qa-q-item-who-data">anonymous</span>
                          </span>
                          </span>
                          </span>
                                            <Tag tags={item.tag_names}/>
                                        </div>
                                        <div className='qa-q-item-clear clearfix'>
                                        </div>
                                    </div>
                                  )

                              })
                          }
                      </div>
                  </form>

              </div>
              <Pagination currentPage={5} totalPage={10} />
              <div className="qa-suggest-next col-xs-12 text-center clearfix alert">
                  Help get things started by <a href="./index.php?qa=ask">asking a question</a>.
              </div>

          </Fragment>
        )
    }

}

export default Question