import React from 'react'

const tag = (props) => {
  const tags = props.tags
  let out = []

  if (tags.length > 0) {
    out = <div className="qa-q-item-tags clearfix">
      <ul className="qa-q-item-tag-list">
        {
          tags.map((tag) => {
            return (
              <li key={tag} className="qa-q-item-tag-item">
                <a href={'./tag/' + tag}
                   className="qa-tag-link">{tag}</a>
              </li>
            )
          })
        }
      </ul>
    </div>

  }

  return (
    out
  )
}

export default tag