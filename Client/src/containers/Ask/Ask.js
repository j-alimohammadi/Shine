import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'
import { Editor } from 'react-draft-wysiwyg'
import { convertToRaw, EditorState } from 'draft-js'

import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css'
import './Ask.css'
import validate from 'validate.js'
import ValidationErrorMessage from '../../components/form/ValidationErrorMessage'

const constraints = {
  questionTitleValidation: {
    presence: {
      message: '^Please provide more information - at least 12 characters',
      allowEmpty: false
    },
  }
}

class Ask extends Component {
  constructor (props) {
    super(props)

    this.state = {
      editorState: EditorState.createEmpty(),
      questionTitle: '',
      errors: new Map([
        ['title', []]

      ])
    }

    // Event handler
    this.handleQuestionStateChange = this.handleQuestionStateChange.bind(this)
    this.handleTagChange = this.handleTagChange.bind(this)
    this.handleQuestionTitleChange = this.handleQuestionTitleChange.bind(this)
    this.handleSubmitFormQuestion = this.handleSubmitFormQuestion.bind(this)
  }

  handleQuestionStateChange (editorState) {
    const content = convertToRaw(editorState.getCurrentContent()).blocks

    this.setState({
      editorState: editorState
    })
  };

  handleTagChange (event) {
    this.setState({tag: event.target.value})
  }

  handleQuestionTitleChange (event) {
    this.setState({questionTitle: event.target.value})
  }

  handleSubmitFormQuestion (event) {
    event.preventDefault()

    const {questionTitle} = this.state
    const validationResult = validate({questionTitleValidation: questionTitle}, constraints)

    const errors = this.state.errors

    if (validationResult !== undefined) {
      if (validationResult.hasOwnProperty('questionTitleValidation'))
        errors.set('title', validationResult.questionTitleValidation)
    }

    this.setState({errors: errors})

  }

  render () {
    const {editorState} = this.state

    return (
      <AUX>
        <div className="qa-part-form">
          <form name="ask" method="post" onSubmit={this.handleSubmitFormQuestion}>
            <table className="qa-form-tall-table">
              <tbody>
              <tr>
                <td className="qa-form-tall-label">
                  The question in one sentence:
                </td>
              </tr>
              <tr>
                <td className="qa-form-tall-data">
                  <input name="title" id="title"
                         value={this.state.questionTitle}
                         onChange={this.handleQuestionTitleChange.bind(this)}
                         type="text" className="qa-form-tall-text form-control"/>
                  <ValidationErrorMessage errors={this.state.errors.get('title')}/>
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
                  <Editor
                    editorState={editorState}
                    editorClassName="ask-editor"
                    onEditorStateChange={this.handleQuestionStateChange}
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
                  <input name="tags" id="tags" type="text" value={this.state.tag}
                         onChange={this.handleTagChange.bind(this)}
                         className="qa-form-tall-text form-control"/>

                  <div className="qa-form-tall-note"><span id="tag_examples_title" style={{display: 'none'}}>Example tags: </span>
                    <span id="tag_complete_title" style={{display: 'none'}}>Matching tags: </span>
                    <span id="tag_hints">  </span>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="1" className="qa-form-tall-buttons">
                  <button type="submit"
                          className="qa-form-tall-button qa-form-tall-button-ask">
                    Ask the Question
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
          </form>
        </div>
      </AUX>
    )
  }
}

export default Ask
