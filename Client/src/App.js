import React, { Component } from 'react'
import './App.css'
import { Switch } from 'react-router-dom'
import HOME from './components/HOME'
import ERROR from './components/ERROR'
import DefaultLayout from './components/Layout/DefaultLayout'
import Question from './containers/Question/Question'
import Ask from './containers/Ask/Ask'
import Answer from './containers/Question/Answer/Answer'
import AnswerTemplate from './components/Layout/AnswerTemplate'
import SearchResult from './containers/Question/SearchResult/SearchResult'
import Login from './containers/User/Login/Login'
import LoginTemplate from './components/Layout/LoginTemplate'
import Logout from './containers/User/Logout/Logout'
import TagPage from './containers/Tag/TagPage'
import TagSearchResult from './containers/Tag/TagSearchResult'

class App extends Component {
  render () {

    return (
      <div>
        <Switch>
          <DefaultLayout path="/" exact component={HOME} titleOfPage='Home'/>
          <DefaultLayout path="/post/search" component={SearchResult} titleOfPage='Search Result'/>
          <DefaultLayout path="/question/tag/:tag" component={TagSearchResult} titleOfPage='Tag Search'/>
          <DefaultLayout path="/question" component={Question} titleOfPage='Recent questions'/>
          <DefaultLayout path="/ask" component={Ask} titleOfPage='Ask a question'/>
          <DefaultLayout path="/tags" component={TagPage}
                         titleOfPage='A tag is a keyword or label that categorizes your questions'/>
          <LoginTemplate path="/login" component={Login} titleOfPage=''/>
          <LoginTemplate path="/oauth/redirect" component={Login} titleOfPage=''/>
          <LoginTemplate path="/logout" component={Logout} titleOfPage=''/>
          <AnswerTemplate path="/answer/:questionId/:title" component={Answer}/>
          <DefaultLayout component={ERROR} titleOfPage='Error in response'/>

        </Switch>

      </div>
    )
  }
}

export default App
