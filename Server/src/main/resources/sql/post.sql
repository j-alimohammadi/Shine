-- QUESTION POST
INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT)
VALUES (-1, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 1",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', DATEADD('SECOND',0, CURRENT_TIMESTAMP()), 'Sample Title 1', 0, 0, 1);

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT)
VALUES (-2, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 2",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', DATEADD('SECOND',1, CURRENT_TIMESTAMP()), 'Sample Title 2', 0, 0, 1);

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT)
VALUES (-3, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 3",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', DATEADD('SECOND',2, CURRENT_TIMESTAMP()), 'Sample Title 3', 0, 0, 2);


-- ANSWER POST
INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP,  VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample answer body 1",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 0,  0, 0, FALSE, -1);


INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP,  VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample answer body 2",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 1,  0, 0,FALSE , -2);





INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP,  VIEW_COUNT, VOTE, IS_ACCEPTED , QUESTION_ID)
VALUES ('ANSWER', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample answer body 1 for question -3",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 2,  0, 0, FALSE , -3);

INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP,  VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample answer body 2 for question -3",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 22,
      "style" : "color-rgb(0,0,0)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontsize-12"
    }, {
      "offset" : 0,
      "length" : 22,
      "style" : "fontfamily-Arial, sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 3,  0, 0,TRUE ,-3);


-- QUESTION_TAG
INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID )
VALUES (-3, -4);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID )
VALUES (-3, -3);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID )
VALUES (-3, -2);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID )
VALUES (-2, -1);


