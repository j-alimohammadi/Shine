import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'

const Pagination = (props) => {
  const currentPage = props.currentPage
  const totalPage = props.totalPage
  const linkPage = props.linkPage
  const onClickPageHandler = props.onClickPageHandler

  const beginPageNumber = currentPage - 2 <= 1 ? 1 : (currentPage - 2)
  const endPageNumber = currentPage + 2 >= totalPage ? totalPage : (currentPage + 2)
  const hasPrevious = currentPage > 1
  const hasNext = currentPage < totalPage

  let outPut = []

  if (totalPage === 1) {
    return (
      <Fragment/>
    )
  }

  if (hasPrevious) {
    outPut.push(
      <li className="qa-page-links-item" key={-1}>
        <Link to={linkPage + '?page=' + (currentPage - 1)}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, currentPage - 1)}>« prev
        </Link>
      </li>)
  }

  for (let page = beginPageNumber; page <= endPageNumber; page++) {
    const active = page === currentPage ? 'active' : ''
    outPut.push(
      <li className={'qa-page-links-item ' + active} key={page}>
        <Link to={linkPage + '?page=' + page}
              className="qa-page-next"
              onClick={onClickPageHandler.bind(this, page)}>{page}
        </Link>
      </li>)
  }

  if (hasNext) {
    outPut.push(
      <li className="qa-page-links-item" key={-2}>
        <Link to={linkPage + '?page=' + (currentPage + 1)}
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

export default Pagination