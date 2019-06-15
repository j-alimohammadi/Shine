import React from 'react'

const column = function (props) {
  const tags = props.tags
  const colNo = props.colNo
  const perColumnTag = props.perColumnTag

  const renderTags = tags.slice((colNo - 1) * perColumnTag, (colNo * perColumnTag))

  return (
    <div className="col-md-4 col-xs-12">
      <ul className="donut-tags-list">
        {
          renderTags.map((item, index) => {
            return (
              <li className="tag-item">
                <div className="tag-head clearfix">
                  <span> {item.used_count} ×</span>
                  <div className="qa-tags-rank-tag-item">
                    <a href={`/question/tag/${item.name}`} className="qa-tag-link">{item.name}</a>
                  </div>
                </div>
              </li>
            )
          })
        }
      </ul>
    </div>
  )

}

export default column