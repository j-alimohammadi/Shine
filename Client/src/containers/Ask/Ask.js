import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'

class Ask extends Component {
  constructor (props) {
    super(props)

    this.state = {
      questions: []
    }

    // Event handler

  }

  render () {
    return (
      <AUX>
        <div className="qa-main col-md-9 col-xs-12 pull-left">
          <div className="qa-part-form">
            <form name="ask" method="post" action="./index.php?qa=ask">
              <table className="qa-form-tall-table">
                <tbody>
                <tr>
                  <td className="qa-form-tall-label">
                    The question in one sentence:
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <input name="title" id="title" autoComplete="off" onChange="qa_title_change(this.value);"
                           type="text" value="" className="qa-form-tall-text form-control"/>
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <span id="similar"></span>
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-label">
                    More information for the question:
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <input name="content_ckeditor_ok" id="content_ckeditor_ok" type="text" value="content of question"/>
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-label">
                    Tags - use hyphens to combine words:
                  </td>
                </tr>
                <tr>
                  <td colSpan="1" className="qa-form-tall-buttons">
                    <button
                      onClick="qa_show_waiting_after(this, false); if (qa_ckeditor_content) qa_ckeditor_content.updateElement();"
                      title="" type="submit" className="qa-form-tall-button qa-form-tall-button-ask"
                      data-original-title="">Ask the Question
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>
      </AUX>
    )
  }
}

export default Ask
