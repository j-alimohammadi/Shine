import ShineClient from '../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../utils/ShineClient/Response'
import decode from 'jwt-decode'

export default class AuthService {

  login (userName, password) {
    return ShineClient.login(userName, password)
      .then((response) => {
        if (ShineResponseParser.isResponseOk(response)) {
          const token = response.headers.get('Authorization')
          this.storeToken(token)
          return true
        } else {
          return false
        }
      })

  }

  logout () {
    localStorage.removeItem('token')
  }

  isUserLoggined () {
    const token = this.getToken()
    if (token && !this.isTokenExpired(token)) {
      return true
    }

    return false
  }

  storeToken (token) {
    localStorage.setItem('token', token)
  }

  getToken () {
    return localStorage.getItem('token')
  }

  isTokenExpired (token) {
    const decodeToken = decode(token)

  }
}