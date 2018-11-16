import React, { Component } from 'react'

class Vote extends Component {
  constructor (props) {
    super(props)

    // Event handler
    this.handleVote = props.onChangeVote.bind(this)

  }

  render () {
    const postId = this.props.postId
    const vote = this.props.vote
    return (
      <div className="qa-voting qa-voting-net">
        <div className="qa-vote-buttons qa-vote-buttons-net">
          <button title=" Click to vote up"
                  onClick={this.props.onChangeVote.bind(this, postId, true)}
                  className="qa-vote-first-button qa-vote-up-button">
            <span className="fa fa-chevron-up"/>
          </button>
          <button title="Click to vote down"
                  onClick={this.props.onChangeVote.bind(this, postId, false)}
                  className="qa-vote-second-button qa-vote-down-button">
            <span className="fa fa-chevron-down"/>
          </button>
        </div>
        <div className='qa-vote-count qa-vote-count-net'>
             <span className='qa-netvote-count'>
             <span className='qa-netvote-count-data'> {vote} </span>
               <span className='qa-netvote-count-pad'> votes </span>
             </span>
        </div>
        <div className="qa-vote-clear clearfix">
        </div>
      </div>
    )
  }
}

export default Vote