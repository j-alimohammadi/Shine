import React, {Fragment} from 'react';

const Pagination = (props) => {
    const currentPage = props.currentPage;
    const totalPage = props.totalPage;

    const beginPageNumber = currentPage - 2 <= 1 ? 1 : (currentPage - 2)
    const endPageNumber = currentPage + 2 >= totalPage ? totalPage : (currentPage + 2)
    const hasPrevious = currentPage > 1
    const hasNext = currentPage < totalPage

    let outPut = []


    if (hasPrevious) {
        outPut.push(<li className="qa-page-links-item">
            <a href="./index.php?qa=questions&amp;start=3" className="qa-page-next">« prev</a>
        </li>);
    }

    for (let page = beginPageNumber; page <= endPageNumber; page++) {
        const active = page === currentPage ? 'active' : '';
        outPut.push(<li className={'qa-page-links-item ' + active}>
            <a href="./index.php?qa=questions&amp;start=6" className="qa-page-link">{page}</a>
        </li>);
    }

    if (hasNext) {
        outPut.push(<li className="qa-page-links-item">
            <a href="./index.php?qa=questions&amp;start=3" className="qa-page-next">next »</a>
        </li>);
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
    );

};

export default Pagination;