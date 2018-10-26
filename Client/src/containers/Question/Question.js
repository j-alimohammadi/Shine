import React, { Component } from 'react'
import AUX from '../../hoc/_Aux'

class Question extends Component {
  render () {
    return (
      <AUX>
        <div className="qa-body-wrapper container">
          <div className="qa-header clearfix">
            <div className="qa-header-clear clearfix">
            </div>
          </div>
          <div className="qa-main-shadow clearfix">
            <div className="qa-main-wrapper clearfix row">
              <div className="qa-left-side-bar" id="sidebar" role="navigation">
                <div className="list-group">
                  <a href="./index.php?qa=questions" className="list-group-item  active">Recent</a>
                  <a href="./index.php?qa=questions&amp;sort=hot" className="list-group-item ">Hot!</a>
                  <a href="./index.php?qa=questions&amp;sort=votes" className="list-group-item ">Most votes</a>
                  <a href="./index.php?qa=questions&amp;sort=answers" className="list-group-item ">Most answers</a>
                  <a href="./index.php?qa=questions&amp;sort=views" className="list-group-item ">Most views</a>
                </div>
              </div>
              <div className="qa-main col-md-9 col-xs-12 pull-left">
                <div className="row">
                  <div className="pull-left col-xs-12 visible-xs side-toggle-button">
                    <button type="button" className="btn btn-primary btn-xs" data-toggle="offcanvas">
                      <i className="fa fa-chevron-right toggle-icon"></i>
                    </button>
                  </div>
                </div>
                <div className="hidden-xs subnav-row clearfix">
                  <div className="qa-nav-sub">
                    <ul className="qa-nav-sub-list">
                      <li className="qa-nav-sub-item qa-nav-sub-recent active">
                        <a href="./index.php?qa=questions" className="qa-nav-sub-link qa-nav-sub-selected">Recent</a>
                      </li>
                      <li className="qa-nav-sub-item qa-nav-sub-hot">
                        <a href="./index.php?qa=questions&amp;sort=hot" className="qa-nav-sub-link">Hot!</a>
                      </li>
                      <li className="qa-nav-sub-item qa-nav-sub-votes">
                        <a href="./index.php?qa=questions&amp;sort=votes" className="qa-nav-sub-link">Most votes</a>
                      </li>
                      <li className="qa-nav-sub-item qa-nav-sub-answers">
                        <a href="./index.php?qa=questions&amp;sort=answers" className="qa-nav-sub-link">Most answers</a>
                      </li>
                      <li className="qa-nav-sub-item qa-nav-sub-views">
                        <a href="./index.php?qa=questions&amp;sort=views" className="qa-nav-sub-link">Most views</a>
                      </li>
                    </ul>
                    <div className="qa-nav-sub-clear clearfix">
                    </div>
                  </div>
                </div>
                <div className="qa-part-q-list">
                  <form method="post" action="./index.php?qa=questions">
                    <div className="qa-q-list">
                      <div className="qa-q-list-item row" id="q1">
                        <div className="qa-q-item-stats">
                          <div className="qa-voting qa-voting-net" id="voting_1">
                            <div className="qa-vote-buttons qa-vote-buttons-net">
                              <button title="Click to vote up" name="vote_1_1_q1" 
                                      type="submit" value="+" className="qa-vote-first-button qa-vote-up-button"><span
                                className="fa fa-chevron-up"></span></button>
                              <button title="Click to vote down" name="vote_1_-1_q1"
                                      type="submit" value="–"
                                      className="qa-vote-second-button qa-vote-down-button"><span
                                className="fa fa-chevron-down"></span></button>
                            </div>
                            <div className="qa-vote-count qa-vote-count-net">
                            <span className="qa-netvote-count">
                            <span className="qa-netvote-count-data">0</span><span className="qa-netvote-count-pad"> votes</span>
                            </span>
                            </div>
                            <div className="qa-vote-clear clearfix">
                            </div>
                          </div>
                          <span className="qa-a-count">
                        <span className="qa-a-count-data">1</span><span className="qa-a-count-pad"> answer</span>
                        </span>
                        </div>
                        <div className="qa-q-item-main">
                          <div className="qa-q-item-title">
                            <a href="./index.php?qa=1&amp;qa_1=this-is-an-question-to-ask">this is an question to
                              ask</a>
                          </div>
                          <span className="qa-q-item-avatar-meta">
                          <span className="qa-q-item-meta">
                          <span className="qa-q-item-what">asked</span>
                          <span className="qa-q-item-when">
                          <span className="qa-q-item-when-data">4 days</span><span className="qa-q-item-when-pad"> ago</span>
                          </span>
                          <span className="qa-q-item-who">
                          <span className="qa-q-item-who-pad">by </span>
                          <span className="qa-q-item-who-data">anonymous</span>
                          </span>
                          </span>
                          </span>
                          <div className="qa-q-item-tags clearfix">
                            <ul className="qa-q-item-tag-list">
                              <li className="qa-q-item-tag-item"><a href="./index.php?qa=tag&amp;qa_1=javad-ali-d"
                                                                    className="qa-tag-link">javad-ali-d</a></li>
                            </ul>
                          </div>
                        </div>
                        <div className="qa-q-item-clear clearfix">
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
                <div className="qa-suggest-next col-xs-12 text-center clearfix alert">
                  Help get things started by <a href="./index.php?qa=ask">asking a question</a>.
                </div>
              </div>
              <div className="qa-sidepanel col-md-3 col-xs-12 pull-right">
                <div className="side-search-bar hidden-xs">
                  <form className="search-form" role="form" method="get" action="./index.php?qa=search">
                    <input type="hidden" name="qa"  value="search"/>
                    <div className="input-group">
                      <input type="text" name="q" value="" className="qa-search-field form-control"
                             placeholder="Search"/>
                      <span className="input-group-btn">
                    <button type="submit" value="" className="btn qa-search-button"><span className="fa fa-search"></span></button>
                    </span>
                    </div>
                  </form>
                </div>
                <div className="qa-sidebar">
                  Welcome to Localhost Q&amp;A, where you can ask questions and receive answers from other members of
                  the community.
                </div>
                <div className="qa-feed">
                  <a href="./index.php?qa=feed&amp;qa_1=questions.rss" className="qa-feed-link"> <span
                    className="icon-wrapper"> <span className="qa-feed-icon"><span
                    className="fa fa-rss"></span>  </span></span>Recent questions</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </AUX>
    )
  }
}

export default Question