import React, {Component} from 'react'
import './App.css'
import {Switch} from 'react-router-dom'
import HOME from './components/HOME'
import ERROR from './components/ERROR'
import UnAnswered from './components/UnAnswered'
import DefaultLayout from './components/Layout/DefaultLayout'
import Question from './containers/Question/Question'
import Ask from './containers/Ask/Ask'
import Answer from './containers/Question/Answer/Answer'
import AnswerTemplate from "./components/Layout/AnswerTemplate";

class App extends Component {
  render () {

    return (
      <div>
        <Switch>
          <DefaultLayout path="/" exact component={HOME} titleOfPage='Home'/>
          <DefaultLayout path="/question" component={Question} titleOfPage='Recent questions'/>
          <DefaultLayout path="/ask" component={Ask} titleOfPage='Ask a question'/>
          <DefaultLayout path="/un-answered" component={UnAnswered} titleOfPage='Recent questions without answers'/>
          <AnswerTemplate path="/answer/:questionId/:title" component={Answer} />
          <DefaultLayout component={ERROR} titleOfPage='Error in response'/>


        </Switch>

      </div>
    )
  }
}

export default App
