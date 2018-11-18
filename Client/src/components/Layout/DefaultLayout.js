import React, { Fragment } from 'react'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'
import { Route } from 'react-router-dom'
import Search from '../../containers/Search/Search'

function titleOfPage (titleOfPage, titlePath, Component) {

  if (titleOfPage)
    return titleOfPage

  return titlePath.params.title

}

const defaultLayout = ({component: Component, ...rest}) => {

  return (

    <Route {...rest} render={matchProps => (
      <Fragment>
        <Navigation/>

        <main className="donut-masthead">
          <div className="container">
            <div className="page-title">
              <h1>
                {
                  titleOfPage(rest.titleOfPage, matchProps.match, Component)
                }
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
      </Fragment>
    )}
    />
  )
}

export default defaultLayout