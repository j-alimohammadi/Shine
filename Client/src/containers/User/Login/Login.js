import React, { Component, Fragment } from 'react'
import googleLogo from '../../../assets/btn_google_signin.png'
import AuthService from '../../../components/authentication/AuthenticationService'
import { Redirect } from 'react-router-dom'
import * as queryString from 'query-string'

class Login extends Component {

  constructor (props) {
    super(props)

    this.state = {
      userName: '',
      password: '',
      isUserLoggedin: false

    }

    // Event handler
    this.handleFormSubmitLogin = this.handleFormSubmitLogin.bind(this)
    this.handleUserNameChange = this.handleUserNameChange.bind(this)
    this.handlePasswordChange = this.handlePasswordChange.bind(this)
    this.handleOAuthLogin = this.handleOAuthLogin.bind(this)

    // property
    this.GOOGLE_AUTH_URL = 'http://localhost:8090/oauth2/authorization/google?redirect_uri=http://localhost:3001/oauth/redirect'
    this.authenticationService = new AuthService()

  }

  handleFormSubmitLogin (event) {
    debugger
    event.preventDefault()

    const {userName, password} = this.state

    this.authenticationService.loginWithUserPassword(userName, password)
      .then((isOk) => {
        if (isOk) {
          this.setState({isUserLoggedin: true})

        } else {
          console.log('error in authentication')
        }
      })

  }

  handleUserNameChange (event) {
    this.setState({userName: event.target.value})
  }

  handlePasswordChange (event) {
    this.setState({password: event.target.value})
  }

  handleOAuthLogin (token) {
    this.authenticationService.loginWithOAuth(token)
    this.setState({isUserLoggedin: true})

  }

  render () {
    const requestParameter = queryString.parse(this.props.location.search)

    if (typeof requestParameter.token !== 'undefined') {
      this.handleOAuthLogin(requestParameter.token)
    }

    let logoutMessage = null
    if (typeof requestParameter.logout !== 'undefined') {
      logoutMessage = <h1 class="text-center">Log out successfully</h1>
    }

    if (this.state.isUserLoggedin) {
      return (<Redirect to='/question'/>)
    }

    return (
      <Fragment>
        {logoutMessage}
        <div className="qa-part-form">
          <h1 className="text-center">Login</h1>
          <div className="col-md-5">
            <form method="post" className="col-mod-10" onSubmit={this.handleFormSubmitLogin}>
              <table className="qa-form-tall-table">
                <tbody>
                <tr>
                  <td className="qa-form-tall-label">
                    Username:
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <input name="handle" id="handle" dir="auto" type="text" value={this.state.userName}
                           onChange={this.handleUserNameChange.bind(this)}
                           className="qa-form-tall-text form-control"/>
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-label">
                    Password:
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <input name="password" id="password" dir="auto" type="password" value={this.state.password}
                           onChange={this.handlePasswordChange.bind(this)}
                           className="qa-form-tall-text form-control"/>
                  </td>
                </tr>
                <tr>
                  <td className="col-md-4 text-center">
                    <button type="submit"
                            className="qa-form-tall-button qa-form-tall-button-login ">Login
                    </button>
                  </td>
                </tr>
                <tr>
                  <td className="col-md-4 text-center">
                    <a href={'/'}>Register
                    </a>
                  </td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
          <div className="vl col-md-1">
          </div>
          <div className="col-md-5">
            <a href={this.GOOGLE_AUTH_URL}>
              <img src={googleLogo}/>
            </a>
          </div>
        </div>
      </Fragment>
    )
  }
}

export default Login