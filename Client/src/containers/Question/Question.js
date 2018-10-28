import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'
import ShineClient from '../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../utils/ShineClient/Response'

class Question extends Component {

  state = {
    questions: []
  }

  componentDidMount () {
    this.getAllQuestions()

  }

  getAllQuestions () {
    ShineClient.findQuestions(0, 1)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((jsonData) => {
        this.setState({questions: jsonData})
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

  render () {
    const questions = this.state.questions

    return (
      <AUX>
        <div className="row">
          <div className="pull-left col-xs-12 visible-xs side-toggle-button">
            <button type="button" className="btn btn-primary btn-xs" data-toggle="offcanvas">
              <i className="fa fa-chevron-right toggle-icon"></i>
            </button>
          </div>
        </div>
        <div className="hidden-xs subnav-row clearfix">
          <div className="qa-nav-sub">
            <ul className="qa-nav-sub-list">
              <li className="qa-nav-sub-item qa-nav-sub-recent active">
                <a href="./index.php?qa=questions" className="qa-nav-sub-link qa-nav-sub-selected">Recent</a>
              </li>
              <li className="qa-nav-sub-item qa-nav-sub-hot">
                <a href="./index.php?qa=questions&amp;sort=hot" className="qa-nav-sub-link">Hot!</a>
              </li>
              <li className="qa-nav-sub-item qa-nav-sub-votes">
                <a href="./index.php?qa=questions&amp;sort=votes" className="qa-nav-sub-link">Most votes</a>
              </li>
              <li className="qa-nav-sub-item qa-nav-sub-answers">
                <a href="./index.php?qa=questions&amp;sort=answers" className="qa-nav-sub-link">Most answers</a>
              </li>
              <li className="qa-nav-sub-item qa-nav-sub-views">
                <a href="./index.php?qa=questions&amp;sort=views" className="qa-nav-sub-link">Most views</a>
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
                questions.map((item, i) => {
                  return (

                      <div className="qa-q-list-item row" key={i} id={'q' + i}>
                        <div className="qa-q-item-stats">
                          <div className="qa-voting qa-voting-net" id="voting_1">
                            <div className="qa-vote-buttons qa-vote-buttons-net">
                              <button title=" Click to vote up" type="submit"
                                      className="qa-vote-first-button qa-vote-up-button">
                                <span className="fa fa-chevron-up"></span>
                              </button>
                              <button title="Click to vote down" type="submit" value="–"
                                      className="qa-vote-second-button qa-vote-down-button">
                                <span className="fa fa-chevron-down"></span></button>
                            </div>
                            <div className='qa-vote-count qa-vote-count-net'>
                    < span className='qa-netvote-count'>
                    < span className='qa-netvote-count-data'> {item.vote} </span><span
                      className='qa-netvote-count-pad'> votes </span>
                    </span>
                            </div>
                            <div className="qa-vote-clear clearfix">
                            </div>
                          </div>
                          <span className="qa-a-count">
              <span className="qa-a-count-data">{item.answer_count}</span><span className="qa-a-count-pad"> answer</span>
              </span>
                        </div>
                        <div className="qa-q-item-main">
                          <div className="qa-q-item-title">
                            <a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">{item.title}</a>
                          </div>
                          <span className="qa-q-item-avatar-meta">
              <span className="qa-q-item-meta">
              <span className="qa-q-item-what">asked</span>
              <span className="qa-q-item-when">
              <span className="qa-q-item-when-data">4 days</span><span
                className="qa-q-item-when-pad"> ago</span>
              </span>
              <span className="qa-q-item-who">
              <span className="qa-q-item-who-pad">by </span>
              <span className="qa-q-item-who-data">anonymous</span>
              </span>
              </span>
              </span>
                          <div className="qa-q-item-tags clearfix">
                            <ul className="qa-q-item-tag-list">
                              <li className="qa-q-item-tag-item"><a href="./index.php?qa=tag&amp;qa_1=javad-ali-d"
                                                                    className="qa-tag-link">javad-ali-d</a></li>
                            </ul>
                          </div>
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
        <div className="qa-suggest-next col-xs-12 text-center clearfix alert">
          Help get things started by <a href="./index.php?qa=ask">asking a question</a>.
        </div>

      </AUX>
    )
  }

}

export default Question