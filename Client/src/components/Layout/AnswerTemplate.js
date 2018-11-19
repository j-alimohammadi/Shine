import React, {Fragment} from 'react'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'
import {Route} from 'react-router-dom'


const answerLayout = ({ component: Component, ...rest }) => {

    return (

      <Route {...rest} render={matchProps => (
        <Fragment>
            <Navigation/>
            <Component {...matchProps}/>
            <Footer/>
        </Fragment>
      )}
      />
    )
}

export default answerLayout