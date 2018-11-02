import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'
import Footer from '../Footer/Footer'

class ASK extends Component {
  constructor (props) {
    super(props)

    this.state = {
      questions: []
    }

    // Event handler

  }

  render () {
    return (
      <AUX>
           Ask a question
      </AUX>
    )
  }
}

export default ASK
