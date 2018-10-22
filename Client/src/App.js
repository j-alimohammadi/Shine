import React, { Component } from 'react'
import './App.css'
import { Route, Switch } from 'react-router-dom'
import ERROR from './components/ERROR'
import Question from './components/Question'
import HOME from './components/HOME'
import UnAnswered from './components/UnAnswered'

class App extends Component {
  render () {
    return (
      <div>
        <Switch>
          <Route path="/" exact component={HOME}/>
          <Route path="/question" component={Question}/>
          <Route path="/unanswered" component={UnAnswered}/>
          <Route component={ERROR}/>
        </Switch>
      </div>
    )
  }
}

export default App
