import React from 'react'
import AUX from '../../hoc/_Aux'

const layout = (props) => (
  <AUX>
    <div>Header, Search, Footer</div>

    <main>
      {props.children}
    </main>
  </AUX>
)

export default layout