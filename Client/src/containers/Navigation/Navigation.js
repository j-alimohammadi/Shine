import React, { Component, Fragment } from 'react'
import { Link } from 'react-router-dom'

class Navigation extends Component {
  render () {
    return (
      <Fragment>
        <header id="top-header">
          <div className="container">
            <div className="row">
              <div className="col-xs-12">
                <div className="left-part pull-left">

                </div>
                <div className="right-part pull-right hidden-xs">
                  <div className="top-html-block">
                    <div className="social-links">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </header>
        <header id="nav-header">
          <nav id="nav" className="navbar navbar-static-top"
               data-spy="affix" data-offset-top="120">
            <div className="container">
              <div className="navbar-header">
                <button type="button" className="navbar-toggle collapsed" data-toggle="collapse"
                        data-target=".navbar-collapse">
                  <span className="sr-only">Toggle navigation</span>
                  <span className="glyphicon glyphicon-menu-hamburger"/>
                </button>
              </div>
              <div className="col-sm-3 col-xs-8 logo-wrapper">
                <div className="qa-logo">
                  <a href="./index.php" className="qa-logo-link">Shine Q&amp;A</a>
                </div>
              </div>
              <div className="col-sm-7 navbar-collapse collapse main-nav navbar-left">
                <ul className="nav navbar-nav inner-drop-nav">
                  <li><a href="/question">Questions</a></li>
                  <li><a href="/unanswered">Un-answered</a></li>
                  <li className="active"><a href="./tags">Tags</a></li>
                  <li><a href="/users">Users</a></li>
                  <li><a href="/ask">Ask a Question</a></li>
                  <li><Link to="/login">Login</Link></li>
                </ul>
              </div>
            </div>
          </nav>
        </header>

      </Fragment>
    )
  }
}

export default Navigation