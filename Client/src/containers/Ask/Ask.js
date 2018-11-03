import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'
import CKEditor from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

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
                    <input name="title" id="title" autoComplete="off"
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
                    <CKEditor
                      editor={ClassicEditor}
                      onChange={(event, editor) => {
                        const data = editor.getData()
                      }}
                    />

                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-label">
                    Tags - use hyphens to combine words:
                  </td>
                </tr>
                <tr>
                  <td className="qa-form-tall-data">
                    <input name="tags" id="tags" autoComplete="off"
                           type="text" value="" className="qa-form-tall-text form-control"/>

                    <div className="qa-form-tall-note"><span id="tag_examples_title" style={{display: 'none'}}>Example tags: </span><span
                      id="tag_complete_title" style={{display: 'none'}}>Matching tags: </span><span
                      id="tag_hints"></span>
                    </div>
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
