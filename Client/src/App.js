import React, { Component } from 'react'
import './App.css'
import { Switch } from 'react-router-dom'
import HOME from './components/HOME'
import ERROR from './components/ERROR'
import UnAnswered from './components/UnAnswered'
import Layout from './components/Layout/DefaultLayout'
import Question from './containers/Question/Question'
import Ask from './containers/Ask/Ask'
import Answer from './containers/Question/Answer/Answer'

class App extends Component {
  render () {

    return (
      <div>
        <Switch>
          <Layout path="/" exact component={HOME} titleOfPage='Home'/>
          <Layout path="/question" component={Question} titleOfPage='Recent questions'/>
          <Layout path="/ask" component={Ask} titleOfPage='Ask a question'/>
          <Layout path="/un-answered" component={UnAnswered} titleOfPage='Recent questions without answers'/>
          <Layout path="/answer/:questionId/:title" component={Answer} />
          <Layout component={ERROR} titleOfPage='Error in response'/>
        </Switch>

      </div>
    )
  }
}

export default App
