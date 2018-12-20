import React, { Component } from 'react'

class Search extends Component {
  render () {
    return (
      <div className="side-search-bar hidden-xs">
        <form className="search-form" method="get" action='/post/search'>
          <div className="input-group">
            <input type="text" name="q" className="qa-search-field form-control"
                   placeholder="Search"/>
            <span className="input-group-btn">
                    <button type="submit" value="" className="btn qa-search-button"><span
                      className="fa fa-search"/></button>
                    </span>
          </div>
        </form>
      </div>
    )
  }
}

export default Search