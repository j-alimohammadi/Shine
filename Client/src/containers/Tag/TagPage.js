import React, { Component, Fragment } from 'react'
import queryString from 'query-string'
import ShineClient from '../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../utils/ShineClient/Response'
import Pagination from '../Pagination/Pagination'
import Column from './Column/Column'

//todo: read page size from server
const pageSize = 3

class TagPage extends Component {
  constructor (props) {
    super(props)

    this.state = {
      result: {
        tags: []
      }
    }

    // Event handler
    this.handleClickOnPagination = this.handleClickOnPagination.bind(this)

  }

  componentDidMount () {
    const values = queryString.parse(this.props.location.search)
    const page = values.page === undefined ? 1 : values.page
    const sortBy = values.sortBy === undefined ? 'usedCount' : values.sortBy
    this.setState({sortBy: sortBy})
    this.getTags(page)
  }

  getTags (page, sortBy) {
    ShineClient.findTags(page, sortBy, pageSize)
      .then((JSONResponse) => {
        if (ShineResponseParser.isResponseOk(JSONResponse)) {
          return JSONResponse.json()
        } else {
          throw new Error('Something bad happened.')
        }
      })
      .then((jsonData) => {
        this.setState({result: jsonData})
      })
      .catch((error) => {
        this.setState({
            alert: {
              alertMessage: `Failed to get question. Error in connecting to server.`,
              showAlert: true,
              alertType: 'danger'
            }
          }
        )
      })

  }

  handleClickOnPagination (page, event) {
    const oldCurrentPage = this.state.result.page
    if (oldCurrentPage !== page) {
      this.getTags(page)
    }

  }

  render () {
    const perColumnTag = pageSize / 3
    const currentPage = this.state.result.page
    const totalPage = this.state.result.total_page

    return (
      <Fragment>
        <div className="qa-part-ranking qa-ranking-tags-count">
          <div id="tags-list" className="row qa-top-tags">
            <Column tags={this.state.result.tags} colNo={1} perColumnTag={perColumnTag}/>
            <Column tags={this.state.result.tags} colNo={2} perColumnTag={perColumnTag}/>
            <Column tags={this.state.result.tags} colNo={3} perColumnTag={perColumnTag}/>
          </div>
        </div>
        <Pagination currentPage={currentPage} totalPage={totalPage} linkPage={'/question'}
                    onClickPageHandler={this.handleClickOnPagination}/>
        <div className="qa-suggest-next col-xs-12 text-center clearfix alert">
          Help get things started by <a href="./index.php?qa=ask">asking a question</a>.
        </div>
      </Fragment>
    )
  }
}

export default TagPage