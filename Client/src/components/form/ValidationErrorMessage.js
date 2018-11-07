import React, { Fragment } from 'react'

const validationErrorMessage = (props) => {
  const errors = props.errors
  const showFirstErrorOnly = props.hasOwnProperty('showFirstError') ? props.showFirstError : true

  let output = []
  let errorOutPut = []
  if (errors.length > 0) {
    if (showFirstErrorOnly) {
      errorOutPut.push(errors[0])
    } else {
      for (let i = 0; i < errors.length; i++) {
        errorOutPut.push(errors[i])
        if (i !== errors.length - 1) {
          errorOutPut.push(<br/>)
        }
      }
    }

    output = <div key className="qa-form-tall-error">
      {errorOutPut}
    </div>
  }

  return (
    <Fragment>{output}</Fragment>
  )

}
export default validationErrorMessage