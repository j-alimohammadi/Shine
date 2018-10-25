import React from 'react'
import AUX from '../../hoc/_Aux'
import Navigation from '../../containers/Navigation/Navigation'

const layout = (props) => (
  <AUX>
    <div><Navigation/>, Search, Footer</div>

    <main>
      {props.children}
    </main>
  </AUX>
)

export default layout