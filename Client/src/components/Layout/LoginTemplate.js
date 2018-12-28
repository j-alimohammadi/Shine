import React, { Fragment } from 'react'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'
import { Route } from 'react-router-dom'

const loginTemplate = ({component: Component, ...rest}) => {

  return (

    <Route {...rest} render={matchProps => (
      <Fragment>
        <Navigation/>
        <div className="qa-body-wrapper container">
          <div className="qa-main-shadow clearfix">
            <div className="qa-main-wrapper clearfix row">
              <div className="qa-main pull-left">
                <Component {...matchProps}/>
              </div>
            </div>
          </div>
        </div>

        <Footer/>
      </Fragment>
    )}
    />
  )
}

export default loginTemplate