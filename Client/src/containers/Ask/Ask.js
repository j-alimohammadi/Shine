import React, { Component } from 'react'
import { Editor } from 'react-draft-wysiwyg'
import { convertToRaw, EditorState } from 'draft-js'

import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css'
import './Ask.css'
import validate from 'validate.js'
import ValidationErrorMessage from '../../components/form/ValidationErrorMessage'
import ShineClient from '../../utils/ShineClient/ShineClient'
import { Redirect } from 'react-router-dom'
import { ShineResponseParser } from '../../utils/ShineClient/Response'

const constraints = {
  questionTitleValidation: {
    presence: {
      message: '^Question Title is required',
      allowEmpty: false
    },
    length: {
      minimum: 12,
      tooShort: '^Please provide more information - at least 12 characters',
      tokenizer: function (value) {
        return value.trim()
      }
    }
  }
}

class Ask extends Component {

  constructor (props) {
    super(props)

    this.state = {
      editorState: EditorState.createEmpty(),
      questionTitle: '',
      questionTag: '',
      goToQuestionPage: false,
      errors: new Map()
    }

    // Event handler
    this.handleQuestionBodyChange = this.handleQuestionBodyChange.bind(this)
    this.handleTagChange = this.handleTagChange.bind(this)
    this.handleQuestionTitleChange = this.handleQuestionTitleChange.bind(this)
    this.handleSubmitFormQuestion = this.handleSubmitFormQuestion.bind(this)
  }

  handleQuestionBodyChange (editorState) {
    this.setState({
      editorState: editorState
    })
  };

  handleTagChange (event) {
    this.setState({questionTag: event.target.value})
  }

  handleQuestionTitleChange (event) {
    this.setState({questionTitle: event.target.value})
  }

  handleSubmitFormQuestion (event) {
    event.preventDefault()

    let errors = this.validateForm()
    this.setState({errors: errors})

    if (errors.size === 0) {
      const questionTitle = this.state.questionTitle
      const questionContent = convertToRaw(this.state.editorState.getCurrentContent())
      const questionTagList = this.createTags(this.state.questionTag)

      const questionObject = {}
      questionObject['title'] = questionTitle
      questionObject['body'] = questionContent
      questionObject['tag_names'] = questionTagList

      ShineClient.createQuestion(questionObject)
        .then((JSONResponse) => {
          if (ShineResponseParser.isResponseOk(JSONResponse)) {
            this.setState({goToQuestionPage: true})
          } else {
            throw new Error('Something bad happened.')
          }
        })
        .catch((error) => {
          this.setState({
              alert: {
                alertMessage: `Failed to get table information. Error in connecting to server.`,
                showAlert: true,
                alertType: 'danger'
              }
            }
          )
        })

    }

  }

  // todo: use regular expression
  /**
   * First split by comma then by space
   * @param tag
   */
  createTags (tag) {
    const result = []

    if (tag.trim().length === 0) {
      return result
    }

    let separatedByComma = tag.trim().split(',')
    for (let item of separatedByComma) {
      result.push(item.split(' '))
    }

    return result.flat()
  }

  validateForm () {
    const {questionTitle} = this.state
    const validationResult = validate({questionTitleValidation: questionTitle}, constraints)

    const errors = new Map()
    if (validationResult !== undefined) {
      if (validationResult.hasOwnProperty('questionTitleValidation')) {
        errors.set('title', validationResult.questionTitleValidation)
      }
    }

    return errors

  }

  render () {
    const {editorState} = this.state

    if (this.state.goToQuestionPage) {
      return (<Redirect to='/question'/>)
    }

    return (
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
                  onEditorStateChange={this.handleQuestionBodyChange}
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
                <input name="tags" id="tags" type="text" value={this.state.questionTag}
                       onChange={this.handleTagChange.bind(this)}
                       className="qa-form-tall-text form-control"/>

                <div className="qa-form-tall-note"><span id="tag_examples_title"
                                                         style={{display: 'none'}}>Example tags: </span>
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
    )
  }
}

export default Ask
