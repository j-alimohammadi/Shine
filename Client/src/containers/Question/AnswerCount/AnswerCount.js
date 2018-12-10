import React, { Fragment } from 'react'

const AnswerCount = props => {
  const postType = props.postType
  const answerCount = props.answer_count

  let output = ''
  if (postType === 'QUESTION') {
    output = <span className="qa-a-count">
               <span className="qa-a-count-data">{answerCount}</span>
               <span className="qa-a-count-pad"> answer</span>
            </span>

  }

  return (
    <Fragment>
      {output}
    </Fragment>
  )
}

AnswerCount.propTypes = {}

export default AnswerCount