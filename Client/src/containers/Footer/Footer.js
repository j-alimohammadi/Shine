import React, { Component, Fragment } from 'react'

class Footer extends Component {
  render () {
    return (
      <Fragment>
        <div className="donut-site-stats-bottom">
          <div className="container">
            <div className="row">
              <div className="stats-wrap">
                <div className="count-item"><span className="count-data">1</span> question</div>
                <div className="count-item"><span className="count-data">1</span> answer</div>
                <div className="count-item"><span className="count-data">0</span> comments</div>
                <div className="count-item"><span className="count-data">2</span> users</div>
              </div>
            </div>
          </div>
        </div>
        <footer className="donut-footer">
          <a className="donut-top"><span className="fa fa-chevron-up"></span></a>
          <div className="container">
            <div className="qa-footer">
              <div className="qa-nav-footer">
                <div className="qa-nav-footer-clear clearfix">
                </div>
              </div>
              <div className="footer-social">
                <ul>
                </ul>
              </div>
              <div className="footer-bottom">
                <div className="qa-attribution">
                  <a href="https://github.com/amiyasahu/Donut">Donut Theme</a> <span className="fa fa-code"></span>
                  with
                  <span className="fa fa-heart"></span> by <a href="http://amiyasahu.github.io">Amiya Sahu</a>
                </div>
                <div className="donut-copyright">
                  <span className="fa fa-copyright"></span>
                  Donut theme
                </div>
              </div>
              <div className="qa-footer-clear clearfix">
              </div>
            </div>
          </div>
        </footer>
        <div style={{position: 'absolute', left: '-9999px', top: '-9999px'}}>
          <span id="qa-waiting-template" className="qa-waiting fa fa-spinner fa-spin"></span>
        </div>
      </Fragment>
    )
  }
}

export default Footer