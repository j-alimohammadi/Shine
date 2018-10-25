import React, { Component } from 'react'
import './App.css'
import Layout from './components/Layout/Layout'
import Question from './containers/Question/Question'

class App extends Component {
  render () {
    return (
      <div>
        <Layout>
          <Question/>
        </Layout>
        {/*<Switch>*/}
        {/*<Route path="/" exact component={HOME}/>*/}
        {/*<Route path="/question" component={Question}/>*/}
        {/*<Route path="/unanswered" component={UnAnswered}/>*/}
        {/*<Route component={ERROR}/>*/}
        {/*</Switch>*/}
      </div>
    )
  }
}

export default App
