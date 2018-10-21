import React, { Component } from 'react'
import './App.css'
import Question from './components/question/Question'
import { Route, Switch } from 'react-router-dom'
import UnAnswered from './components/unanswered/UnAnswered'

class App extends Component {
  render () {
    return (
      <div>
        <Switch>
          <Route path="/question" exact component={Question}/>
          <Route path="/unanswered" exact component={UnAnswered}/>
        </Switch>
      </div>
    )
  }
}

export default App
