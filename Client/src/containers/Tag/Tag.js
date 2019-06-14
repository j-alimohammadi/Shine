import React, { Component, Fragment } from 'react'
import queryString from 'query-string'
import ShineClient from '../../utils/ShineClient/ShineClient'
import { ShineResponseParser } from '../../utils/ShineClient/Response'
import Pagination from '../Pagination/Pagination'

//todo: read page size from server
const pageSize = 3

class Tag extends Component {
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
    const sortBy = values.sortBy === undefined ? 'recent' : values.sortBy
    this.setState({sortBy: sortBy})
    this.getTags(page)
  }

  getTags (page) {
    ShineClient.findTags(page, pageSize)
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
    const sortBy = this.state.sortBy
    const oldCurrentPage = this.state.result.page
    if (oldCurrentPage !== page) {
      this.getTags(page)
    }

  }

  render () {
    const perColumnTag = pageSize / 3
    let firstColumn = this.state.result.tags.slice(0, (perColumnTag))
    let secondColumn = this.state.result.tags.slice((perColumnTag), (2 * perColumnTag))
    let thiredColumn = this.state.result.tags.slice((2 * perColumnTag), (3 * perColumnTag))
    const currentPage = this.state.result.page
    const totalPage = this.state.result.total_page

    return (
      <Fragment>
        <div className="qa-part-ranking qa-ranking-tags-count">
          <div id="tags-list" className="row qa-top-tags">
            <div className="col-md-4 col-xs-12">
              <ul className="donut-tags-list">
                {
                  firstColumn.map((item, index) => {
                    return (
                      <li className="tag-item">
                        <div className="tag-head clearfix">
                          <span> {item.used_count} ×</span>
                          <div className="qa-tags-rank-tag-item">
                            <a href="../index.php/tag/tag7" className="qa-tag-link">{item.name}</a>
                          </div>
                        </div>
                      </li>
                    )
                  })
                }
              </ul>
            </div>
            <div className="col-md-4 col-xs-12">
              <ul className="donut-tags-list">
                {
                  secondColumn.map((item, index) => {
                    return (
                      <li className="tag-item">
                        <div className="tag-head clearfix">
                          <span> {item.used_count} ×</span>
                          <div className="qa-tags-rank-tag-item">
                            <a href="../index.php/tag/tag7" className="qa-tag-link">{item.name}</a>
                          </div>
                        </div>
                      </li>
                    )
                  })
                }
              </ul>
            </div>
            <div className="col-md-4 col-xs-12">
              <ul className="donut-tags-list">
                {
                  thiredColumn.map((item, index) => {
                    return (
                      <li className="tag-item">
                        <div className="tag-head clearfix">
                          <span> {item.used_count} ×</span>
                          <div className="qa-tags-rank-tag-item">
                            <a href="../index.php/tag/tag7" className="qa-tag-link">{item.name}</a>
                          </div>
                        </div>
                      </li>
                    )
                  })
                }
              </ul>
            </div>
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

export default Tag