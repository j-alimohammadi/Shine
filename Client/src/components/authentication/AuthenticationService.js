import ShineClient from '../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../utils/ShineClient/Response'
import decode from 'jwt-decode'

export default class AuthService {

  login (userName, password) {
    ShineClient.login(userName, password)
      .then((response) => {
        if (ShineResponseParser.isResponseOk(response)) {
          const token = response.headers.get('Authorization')
          this.storeToken(token)
          return true
        } else {
          return false
        }

      }).catch((error) => {
      console.log(error)

    })

  }

  logout () {
    localStorage.removeItem('token')
  }

  isUserLoggined () {

  }

  storeToken (token) {
    localStorage.setItem('token', token)
  }

  getToken () {
    return localStorage.getItem('token')
  }

  isTokenExoired (token) {
    const decodeToken = decode(token)
    
    
  }
}