import React from 'react'
import AUX from '../../hoc/_Aux'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'
import Search from '../../containers/Search/Search'

const layout = (props) => (
  <AUX>
    <Navigation/>

    <main className="donut-masthead">
      <div className="container">
        <div className="page-title">
          <a href="./index.php?qa=feed&amp;qa_1=questions.rss" title="" className="qa-rss-feed"
             data-original-title="Recent questions"><i className="fa fa-rss qa-rss-icon"></i></a>
          <h1>
            Recent questions
          </h1>
        </div>
      </div>
    </main>

    <div className="qa-body-wrapper container">
      <div className="qa-main-shadow clearfix">
        <div className="qa-main-wrapper clearfix row">
          <div className="qa-main col-md-9 col-xs-12 pull-left">
            {props.children}
          </div>
          <div className="qa-sidepanel col-md-3 col-xs-12 pull-right">
            <Search/>
          </div>
        </div>
      </div>
    </div>


    <Footer/>
  </AUX>
)

export default layout