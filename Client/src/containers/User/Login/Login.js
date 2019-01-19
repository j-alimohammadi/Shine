import React, { Component } from 'react'
import AuthService from '../../../components/authentication/AuthenticationService'

class Login extends Component {

  constructor (props) {
    super(props)

    this.state = {
      userName: '',
      password: ''

    }

    // Event handler
    this.handleFormSubmitLogin = this.handleFormSubmitLogin.bind(this)
    this.handleUserNameChange = this.handleUserNameChange.bind(this)
    this.handlePasswordChange = this.handlePasswordChange.bind(this)

    // property
    this.authenticationService = new AuthService()

  }

  handleFormSubmitLogin (event) {
    event.preventDefault()

    const {userName,password} =this.state

    this.authenticationService.login(userName, password)
  }

  handleUserNameChange (event) {
    this.setState({userName: event.target.value})
  }

  handlePasswordChange (event) {
    this.setState({password: event.target.value})
  }

  render () {

    return (
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

        logo for login other third party app for example google
      </div>
    )
  }
}

export default Login