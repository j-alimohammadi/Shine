import React, { Component } from 'react'

class NAVIGATION extends Component {
  render () {
    return (
      <div className="col-sm-7 navbar-collapse collapse main-nav navbar-left">
        <ul className="nav navbar-nav inner-drop-nav">
          <li><a href="./question">Questions</a></li>
          <li><a href="./unanswered">Un-answered</a></li>
          <li className="active"><a href="./tags">Tags</a></li>
          <li><a href="./users">Users</a></li>
          <li><a href="./ask">Ask a Question</a></li>
        </ul>
      </div>
    )
  }
}

export default NAVIGATION