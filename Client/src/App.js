import React, { Component } from 'react'
import './App.css'
import { Switch } from 'react-router-dom'
import HOME from './components/HOME'
import ERROR from './components/ERROR'
import UnAnswered from './components/UnAnswered'
import Layout from './components/Layout/Layout'
import Question from './containers/Question/Question'
import ASK from './containers/Ask/ASK'

class App extends Component {
  constructor (props) {
    super(props)

    this.state = {
      pageTitle: ''
    }

    // Event handler

  }

  render () {

    return (
      <div>
        <Switch>
          <Layout path="/" exact component={HOME} titleOfPage='Home'/>
          <Layout path="/question" component={Question} titleOfPage='Recent questions'/>
          <Layout path="/ask" component={ASK} titleOfPage='Ask a question'/>
          <Layout path="/un-answered" component={UnAnswered} titleOfPage='Recent questions without answers'/>
          <Layout component={ERROR} titleOfPage='Error in reposnse of page'/>
        </Switch>

      </div>
    )
  }
}

export default App
