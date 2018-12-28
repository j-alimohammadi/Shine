import React, { Component } from 'react'

class Login extends Component {
  render () {

    return (
      <div className="qa-part-form">
        <h1 className="text-center">Login</h1>
        <div className="col-md-5">
        <form method="post" className="col-mod-10">
          <table className="qa-form-tall-table">
            <tbody>
            <tr>
              <td className="qa-form-tall-label">
                Username:
              </td>
            </tr>
            <tr>
              <td className="qa-form-tall-data">
                <input name="handle" id="handle" dir="auto" type="text" 
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
                <input name="password" id="password" dir="auto" type="password"
                       className="qa-form-tall-text form-control"/>
              </td>
            </tr>
            <tr>
              <td className="qa-form-tall-label">
                Email:
              </td>
            </tr>
            <tr>
              <td className="qa-form-tall-data">
                <input name="email" id="email" dir="auto" type="text"
                       className="qa-form-tall-text form-control"/>
                <div className="qa-form-tall-note">Privacy: Your email address will not be shared or sold to third
                  parties.
                </div>
              </td>
            </tr>
            <tr>
              <td className="col-md-4 text-center">
                <button type="submit"
                        className="qa-form-tall-button qa-form-tall-button-register ">Register
                </button>
              </td>
            </tr>
            </tbody>
          </table>
        </form>
        </div>
        <div className="vl col-md-1">
        </div>

        logo for login other thired party app for example google
      </div>
    )
  }
}

export default Login