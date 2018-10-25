import React from 'react'
import AUX from '../../hoc/_Aux'
import Navigation from '../../containers/Navigation/Navigation'
import Footer from '../../containers/Footer/Footer'

const layout = (props) => (
  <AUX>
    <Navigation/>
    
    <main>
      {props.children}
    </main>

    <Footer/>
  </AUX>
)

export default layout