import React from 'react'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'
import { Route } from 'react-router-dom'
import Search from '../../containers/Search/Search'
import AUX from '../../hoc/_Aux'

const layout = ({component: Component, ...rest}) => {
  return (

    <Route {...rest} render={matchProps => (
      <AUX>
        <Navigation/>

        <main className="donut-masthead">
          <div className="container">
            <div className="page-title">
              <h1>
                {rest.titleOfPage}
              </h1>
            </div>
          </div>
        </main>

        <div className="qa-body-wrapper container">
          <div className="qa-main-shadow clearfix">
            <div className="qa-main-wrapper clearfix row">
              <div className="qa-main col-md-9 col-xs-12 pull-left">
                <Component {...matchProps}/>
              </div>
              <div className="qa-sidepanel col-md-3 col-xs-12 pull-right">
                <Search/>
              </div>
            </div>
          </div>
        </div>

        <Footer/>
      </AUX>
    )}
    />
  )
}

export default layout