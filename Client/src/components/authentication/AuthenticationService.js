import ShineClient from '../../utils/ShineClient/ShineClient'

export default class AuthService {
  login (userName, password) {
    ShineClient.login(userName, password)
      .then((JSONResponse) => {
        console.log(JSONResponse)
      })

  }

}