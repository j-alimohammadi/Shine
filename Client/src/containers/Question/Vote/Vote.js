import React, {Component} from 'react';
import ShineClient from "../../../utils/ShineClient/ShineClient";
import {ShineResponseParser} from "../../../utils/ShineClient/Response";

class Vote extends Component {
    constructor(props) {
        super(props)

        this.state = {
            questionId: -1,
            vote: 0
        }

        // Event handler
        this.handleVote = this.handleVote.bind(this)

    }

    handleVote(event) {
        const questionId = event.currentTarget.getAttribute('data-question-id')
        const isVotingUp = event.currentTarget.getAttribute('data-is-vote-up') === 'true'

        ShineClient.vote(questionId, isVotingUp)
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

    render() {
        return (
          <div className="qa-voting qa-voting-net" id="voting_1">
              <div className="qa-vote-buttons qa-vote-buttons-net">
                  <button title=" Click to vote up" data-is-vote-up="true" data-question-id={this.state.questionId}
                          onClick={this.handleVote}
                          className="qa-vote-first-button qa-vote-up-button">
                      <span className="fa fa-chevron-up"/>
                  </button>
                  <button title="Click to vote down" data-is-vote-up="false" data-question-id={this.state.questionId}
                          onClick={this.handleVote}
                          className="qa-vote-second-button qa-vote-down-button">
                      <span className="fa fa-chevron-down"/>
                  </button>
              </div>
              <div className='qa-vote-count qa-vote-count-net'>
                             <span className='qa-netvote-count'>
                             <span className='qa-netvote-count-data'> {this.state.vote} </span>
                                 <span className='qa-netvote-count-pad'> votes </span>
                            </span>
              </div>
              <div className="qa-vote-clear clearfix">
              </div>
          </div>
        );
    }
}

export default Vote;