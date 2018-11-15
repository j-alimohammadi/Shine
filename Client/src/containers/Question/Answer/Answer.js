import React, { Component, Fragment } from 'react'
import ShineClient from '../../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../../utils/ShineClient/Response'
import Vote from '../Vote/Vote'
import Tag from '../Tag/Tag'
import { EditorState } from 'draft-js'
import { Editor } from 'react-draft-wysiwyg'


class Answer extends Component {
  constructor (props) {
    super(props)

    this.state = {
      editorState: EditorState.createEmpty(),
      questionId: props.match.params.questionId,
      question: null
    }

    // Event handler
    this.handleAnswerChange = this.handleAnswerChange.bind(this)

  }

  getQuestionDetail () {
    const {questionId} = this.state

    ShineClient.findQuestion(questionId)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((jsonData) => {
        this.setState({question: jsonData})
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

  handleAnswerChange (editorState) {
    this.setState({
      editorState: editorState
    })
  };

  componentDidMount () {
    this.getQuestionDetail()
  }

  render () {
    const {editorState} = this.state

    let vote = ''
    let tags = ''
    let title = ''

    if (this.state.question) {
      vote = <Vote questionId={this.state.question.id} vote={this.state.question.vote}/>
      tags = <Tag tags={this.state.question.tag_names}/>
      title = this.state.question.title
    }

    return (

      <Fragment>
        <div className="qa-template-question qa-body-js-on">
          <div className="qa-part-q-view">
            <div className="qa-q-view" id="q1">
              <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">
                <div className="qa-q-view-stats">
                  {vote}
                </div>
                <input name="code" type="hidden"
                       value="0-1542193599-3d03c828c1cd3f84c4edd816dfb740c42ca4ccf1"/>
              </form>
              <div className="qa-q-view-main">
                <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">
                  <div className="qa-q-view-content qa-post-content">
                    <a name="1"/>
                    <div itemProp="text">{title}</div>
                  </div>
                  {tags}
                  <span className="qa-q-view-avatar-meta">
                                <span className="qa-q-view-meta">
                                <a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask"
                                   className="qa-q-view-what">asked</a>
                                <span className="qa-q-view-when">
                                <span className="qa-q-view-when-data"><time itemProp="dateCreated"
                                                                            dateTime="2018-10-21T11:13:02+0000" title=""
                                                                            data-original-title="2018-10-21T11:13:02+0000">Oct 21</time></span>
                                </span>
                                <span className="qa-q-view-who">
                                <span className="qa-q-view-who-pad">by </span>
                                <span className="qa-q-view-who-data"><span itemProp="author" itemScope=""
                                                                           itemType="http://schema.org/Person"><span
                                  itemProp="name">me</span></span></span>
                                </span>
                                </span>
                                </span>
                  <div className="qa-q-view-buttons">
                    <button name="q_doedit" title="" type="submit"
                            className="qa-form-light-button qa-form-light-button-edit"
                            data-original-title="Edit this question">edit
                    </button>
                    <button name="q_doclose" title="" type="submit"
                            className="qa-form-light-button qa-form-light-button-close"
                            data-original-title="Close this question to any new answers">close
                    </button>
                    <button name="q_dohide" title=""
                            type="submit" className="qa-form-light-button qa-form-light-button-hide"
                            data-original-title="Hide this question">hide
                    </button>
                    <button name="q_doanswer" id="q_doanswer" title="" type="submit"
                            className="qa-form-light-button qa-form-light-button-answer"
                            data-original-title="Answer this question">answer
                    </button>
                  </div>
                  <input name="code" type="hidden"
                         value="0-1542193599-0c728c53f25f18ce6f962a1d743142c68f013db7"/>
                  <input name="qa_click" type="hidden"/>
                </form>
                <div className="qa-q-view-c-list" style={{display: 'none'}} id="c1_list">
                </div>

                <div className="qa-c-form">
                </div>

              </div>

              <div className="qa-q-view-clear clearfix">
              </div>
            </div>
          </div>
          <div className="qa-part-a-form">
            <div className="qa-a-form" id="anew">
              <h2>Your answer</h2>
              <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask" name="a_form">
                <table className="qa-form-tall-table">
                  <tbody>
                  <tr>
                    <td className="qa-form-tall-data">
                      <Editor
                        editorState={editorState}
                        editorClassName="ask-editor"
                        onEditorStateChange={this.handleAnswerChange}
                      />
                    </td>
                  </tr>
                  <tr>
                    <td className="qa-form-tall-label">
                      Your name to display (optional):
                    </td>
                  </tr>
                  <tr>
                    <td className="qa-form-tall-data">
                      <input name="a_name" type="text"
                             className="qa-form-tall-text form-control"/>
                    </td>
                  </tr>
                  <tr>
                    <td className="qa-form-tall-label">
                      <label>
                        <input name="a_notify" id="a_notify"
                               type="checkbox" value="1" className="qa-form-tall-checkbox"/>
                        <span
                          id="a_email_shown">Email me at this address if my answer is selected or commented on:</span>
                        <span id="a_email_hidden" style={{display: 'none'}}>Email me if my answer is selected or commented on</span>
                      </label>
                    </td>
                  </tr>
                  </tbody>
                  <tbody id="a_email_display">
                  <tr>
                    <td className="qa-form-tall-data">
                      <input name="a_email" id="a_email" type="text"
                             className="qa-form-tall-text form-control"/>
                      <div className="qa-form-tall-note">Privacy: Your email address will only be
                        used for sending these notifications.
                      </div>
                    </td>
                  </tr>
                  </tbody>
                  <tbody>
                  <tr>
                    <td colSpan="1" className="qa-form-tall-buttons">
                      <button
                        title="" type="submit"
                        className="qa-form-tall-button qa-form-tall-button-answer"
                        data-original-title="">Add answer
                      </button>
                      <button name="docancel" title=""
                              type="submit" className="qa-form-tall-button qa-form-tall-button-cancel"
                              data-original-title="">Cancel
                      </button>
                    </td>
                  </tr>
                  </tbody>
                </table>
                <input name="a_editor" type="hidden" value="WYSIWYG Editor"/>
              </form>
            </div>
          </div>
          <div className="qa-part-a-list">
            <h2 id="a_list_title"><span itemProp="answerCount">1</span> Answer</h2>
            <div className="qa-a-list" id="a_list">
              <div className="qa-a-list-item " id="a2" itemProp="suggestedAnswer" itemScope=""
                   itemType="http://schema.org/Answer">
                <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">
                  <div className="qa-voting qa-voting-net" id="voting_2">
                    <div className="qa-vote-buttons qa-vote-buttons-net">
                      <button title="You cannot vote on your own posts" type="submit"
                              className="qa-vote-first-button qa-vote-up-disabled" disabled="disabled">
                        <span className="fa fa-chevron-up"/></button>
                      <button title="You cannot vote on your own posts" type="submit"
                              className="qa-vote-second-button qa-vote-down-disabled"
                              disabled="disabled"><span className="fa fa-chevron-down"/></button>
                    </div>
                    <div className="qa-vote-count qa-vote-count-net">
                                    <span className="qa-netvote-count">
                                    <span className="qa-netvote-count-data">0</span><span
                                      className="qa-netvote-count-pad"> votes
                                        <meta itemProp="upvoteCount" content="0"/></span>
                                    </span>
                    </div>
                    <div className="qa-vote-clear clearfix">
                    </div>
                  </div>
                  <input name="code" type="hidden"
                         value="0-1542193599-3d03c828c1cd3f84c4edd816dfb740c42ca4ccf1"/>
                </form>
                <div className="qa-a-item-main">
                  <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">
                    <div className="qa-a-selection">
                      <button title="" name="a2_doselect"
                              type="submit" className="qa-a-select-button"
                              data-original-title="Click to select as best answer">
                        <span className="fa fa-check"></span></button>
                    </div>
                    <div className="qa-a-item-content qa-post-content">
                      <a name="2"></a>
                      <div itemProp="text">this is my answer you can see it</div>
                    </div>
                    <span className="qa-a-item-avatar-meta">
<span className="qa-a-item-meta">
<a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask&amp;show=2#a2" className="qa-a-item-what">answered</a>
<span className="qa-a-item-when">
<span className="qa-a-item-when-data"><time itemProp="dateCreated" dateTime="2018-10-21T11:19:06+0000" title=""
                                            data-original-title="2018-10-21T11:19:06+0000">Oct 21</time></span>
</span>
<span className="qa-a-item-who">
<span className="qa-a-item-who-pad">by </span>
<span className="qa-a-item-who-data"><span itemProp="author" itemScope="" itemType="http://schema.org/Person"><span
  itemProp="name">name</span></span></span>
</span>
</span>
</span>
                    <div className="qa-a-item-buttons">
                      <button name="a2_doedit" title="" type="submit"
                              className="qa-form-light-button qa-form-light-button-edit"
                              data-original-title="Edit this answer">edit
                      </button>
                      <button name="a2_dohide" title=""
                              type="submit" className="qa-form-light-button qa-form-light-button-hide"
                              data-original-title="Hide this answer">hide
                      </button>
                      <button name="a2_dofollow" title="" type="submit"
                              className="qa-form-light-button qa-form-light-button-follow"
                              data-original-title="Ask a new question relating to this answer">ask
                        related question
                      </button>
                      <button name="a2_docomment" title=""
                              type="submit"
                              className="qa-form-light-button qa-form-light-button-comment"
                              data-original-title="Add a comment on this answer">comment
                      </button>
                    </div>
                    <input name="code" type="hidden"
                           value="0-1542193599-129c4e6e11473cc427d3baa3a29990efebc56532"/>
                    <input name="qa_click" type="hidden"/>
                  </form>
                  <div className="qa-a-item-c-list" style={{display: 'none'}} id="c2_list">
                  </div>
                  <div className="qa-c-form" id="c2" style={{display: 'none'}}>
                    <h2>Your comment on this answer:</h2>
                    <form method="post" action="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask"
                          name="c_form_2">
                      <table className="qa-form-tall-table">
                        <tbody>
                        <tr>
                          <td className="qa-form-tall-data">
                                                  <textarea name="c2_content" id="c2_content" rows="4" cols="40"
                                                            className="qa-form-tall-text  form-control"/>
                          </td>
                        </tr>
                        <tr>
                          <td className="qa-form-tall-label">
                            Your name to display (optional):
                          </td>
                        </tr>
                        <tr>
                          <td className="qa-form-tall-data">
                            <input name="c2_name" type="text"
                                   className="qa-form-tall-text form-control"/>
                          </td>
                        </tr>
                        <tr>
                          <td className="qa-form-tall-label">
                            <label>
                              <input name="c2_notify" id="c2_notify"
                                     type="checkbox" value="1"
                                     className="qa-form-tall-checkbox"/>
                              <span
                                id="c2_email_shown">Email me at this address if a comment is added after mine:</span><span
                              id="c2_email_hidden"
                              style={{display: 'none'}}>Email me if a comment is added after mine</span>
                            </label>
                          </td>
                        </tr>
                        </tbody>
                        <tbody id="c2_email_display">
                        <tr>
                          <td className="qa-form-tall-data">
                            <input name="c2_email" id="c2_email" type="text"
                                   className="qa-form-tall-text form-control"/>
                            <div className="qa-form-tall-note">Privacy: Your email address
                              will only be used for sending these notifications.
                            </div>
                          </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                          <td colSpan="1" className="qa-form-tall-buttons">
                            <button title=""
                                    type="submit"
                                    className="qa-form-tall-button qa-form-tall-button-comment"
                                    data-original-title="">Add comment
                            </button>
                            <button name="docancel" title=""
                                    type="submit"
                                    className="qa-form-tall-button qa-form-tall-button-cancel"
                                    data-original-title="">Cancel
                            </button>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                      <input name="c2_editor" type="hidden"/>
                      <input name="c2_doadd" type="hidden" value="1"/>
                      <input name="c2_code" type="hidden"
                             value="0-1542193599-cf24f81e8a404abdbfaa51a284eab2f779fe929e"/>
                    </form>
                  </div>
                </div>
                <div className="qa-a-item-clear clearfix">
                </div>
              </div>
            </div>
          </div>
        </div>
      </Fragment>

    )
  }
}

export default Answer