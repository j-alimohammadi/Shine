import React, { Fragment } from 'react'
import { Link, withRouter } from 'react-router-dom'
import queryString from 'query-string'

const pageNumberBoundary = 3

const Pagination = (props) => {
  const currentPage = props.currentPage
  const totalPage = props.totalPage
  const location = props.location.pathname
  const params = queryString.parse(props.location.search)
  const onClickPageHandler = props.onClickPageHandler

  const beginPageNumber = currentPage - pageNumberBoundary <= 2 ? 1 : (currentPage - pageNumberBoundary)
  const endPageNumber = totalPage - (currentPage + pageNumberBoundary) <= 2 ? totalPage : (currentPage + pageNumberBoundary)
  const hasPrevious = currentPage > 1
  const hasNext = currentPage < totalPage
  const hasAfterFirstEllipse = beginPageNumber !== 1
  const hasBeforeLastEllipse = endPageNumber !== totalPage

  let outPut = []

  if (totalPage === 1) {
    return (
      <Fragment/>
    )
  }

  if (hasPrevious) {
    params.page = (currentPage - 1)
    const query = queryString.stringify(params)

    outPut.push(
      <li className="qa-page-links-item" key={-1}>
        <Link to={location + '?' + query}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, currentPage - 1)}>« prev
        </Link>
      </li>)
  }

  if (hasAfterFirstEllipse) {
    params.page = 1
    const query = queryString.stringify(params)

    outPut.push(
      <li className="qa-page-links-item">
        <Link to={location + '?' + query}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, params.page)}>{params.page}
        </Link>
      </li>
    )
    outPut.push(
      <li className="qa-page-links-item disabled">
        <span className="qa-page-ellipsis">...</span>
      </li>
    )

  }

  for (let page = beginPageNumber; page <= endPageNumber; page++) {
    params.page = page
    const query = queryString.stringify(params)

    const active = page === currentPage ? 'active' : ''
    outPut.push(
      <li className={'qa-page-links-item ' + active} key={page}>
        <Link to={location + '?' + query}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, page)}>{page}
        </Link>
      </li>)
  }

  if (hasBeforeLastEllipse) {
    params.page = totalPage
    const query = queryString.stringify(params)

    outPut.push(
      <li className="qa-page-links-item disabled">
        <span className="qa-page-ellipsis">...</span>
      </li>
    )
    outPut.push(
      <li className="qa-page-links-item">
        <Link to={location + '?' + query}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, params.page)}>{params.page}
        </Link>
      </li>
    )

  }

  if (hasNext) {
    params.page = currentPage + 1
    const query = queryString.stringify(params)

    outPut.push(
      <li className="qa-page-links-item" key={-2}>
        <Link to={location + '?' + query}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, currentPage + 1)}>next »
        </Link>

      </li>)
  }

  return (
    <Fragment>
      <div className="qa-page-links">
        <span className="qa-page-links-label">Page:</span>
        <ul className="qa-page-links-list">
          {outPut}
        </ul>
        <div className="qa-page-links-clear clearfix">
        </div>
      </div>
    </Fragment>
  )

}

export default withRouter(Pagination)