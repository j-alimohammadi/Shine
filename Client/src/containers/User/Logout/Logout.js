import React, { Component } from 'react'
import AuthService from '../../../components/authentication/AuthenticationService'
import { Redirect } from 'react-router-dom'

export default class Logout extends Component {
  constructor (props) {
    super(props)

    // property
    this.authenticationService = new AuthService()

  }

  render () {
    this.authenticationService.logout()
    return (
      <Redirect to='/login?logout=success'/>
    )
  }
}


