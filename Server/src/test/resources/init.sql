-- SAMPLE USER

INSERT INTO SHINE_USER(ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUTATION)
VALUES (-1, true, 'smith', 'smith@domain.com', '$2a$10$wgsBKRLSrt0JdEM1gRK0xuhwSflI3O/B9S6uiPs2z7VIsjZnqKz4q',
        CURRENT_TIMESTAMP(), 0);

-- ANONYMOUS USER
INSERT INTO SHINE_USER(ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUTATION)
VALUES (-2, true, 'anonymous_user', 'anonymous@domain.com', 'not_use', CURRENT_TIMESTAMP(), 0);


-- DEFAULT ROLES
INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-1, 'anonymous_role', 'anonymous role', true, 30);

INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-2, 'registered_user_role', 'registered user role', false, 0);

INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-3, 'administration', 'administration role', false, 10);


-- USER <-------> ROLES
INSERT INTO SHINE_USER_ROLE_XREF(ID, SHINE_USER_ID, SHINE_ROLE_ID)
VALUES (-1, -2, -1);

INSERT INTO SHINE_USER_ROLE_XREF(ID, SHINE_USER_ID, SHINE_ROLE_ID)
VALUES (-2, -1, -2);

-- PERMISSION
INSERT INTO SHINE_PERMISSION(ID, PERMISSION_TYPE, TARGET, VALUE, ADDITIONAL_CONDITION, SHINE_ROLE)
VALUES (-1, 40, 'specific_vote_question', 1, '0', -2);



-- --------------------------------------------------------------------------------------------------------
-- QUESTION POST
INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
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
}', DATEADD('SECOND', 0, CURRENT_TIMESTAMP()), DATEADD('SECOND', 0, CURRENT_TIMESTAMP()), 'Sample Title 1', 0, 1, 1,
        'sample-title-1');

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
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
}', DATEADD('SECOND', 1, CURRENT_TIMESTAMP()), DATEADD('SECOND', 1, CURRENT_TIMESTAMP()), 'Sample Title 2', 0, 2, 1,
        'sample-title-2');

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
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
}', DATEADD('SECOND', 2, CURRENT_TIMESTAMP()), DATEADD('SECOND', 2, CURRENT_TIMESTAMP()), 'Sample Title 3', 0, 0, 3,
        'sample-title-3');

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
VALUES (-4, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 4",
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
}', DATEADD('SECOND', 3, CURRENT_TIMESTAMP()), DATEADD('SECOND', 3, CURRENT_TIMESTAMP()), 'Sample Title 4', 0, 0, 0,
        'sample-title-4');

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
VALUES (-5, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 5",
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
}', DATEADD('SECOND', 4, CURRENT_TIMESTAMP()), DATEADD('SECOND', 4, CURRENT_TIMESTAMP()), 'Sample Title 5', 0, 0, 0,
        'sample-title-5');

INSERT INTO SHINE_POST (ID, POST_TYPE, BODY, CREATED_TIMESTAMP, EDITED_TIMESTAMP, TITLE, VIEW_COUNT, VOTE, ANSWER_COUNT,
                        URL_ADDRESS)
VALUES (-6, 'QUESTION', '{
  "blocks" : [ {
    "key" : "98upg",
    "text" : "Sample question body 6",
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
}', DATEADD('SECOND', 5, CURRENT_TIMESTAMP()), DATEADD('SECOND', 5, CURRENT_TIMESTAMP()), 'Sample Title 6', 0, 0, 0,
        'sample-title-6');


-- ANSWER POST
INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP, VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
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
}', CURRENT_TIMESTAMP() + 0, 0, 0, FALSE, -1);

INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP, VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '	{
  "blocks" : [ {
    "key" : "bn3pf",
    "text" : "sample answer body 1 for question -3 ",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 36,
      "style" : "color-rgb(51,51,51)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontsize-18"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontfamily-sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 1, 0, 0, FALSE, -3);

INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP, VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '	{
  "blocks" : [ {
    "key" : "bn3pf",
    "text" : "sample answer body 2 for question -3 ",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 36,
      "style" : "color-rgb(51,51,51)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontsize-18"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontfamily-sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 2, 0, 0, FALSE, -3);

INSERT INTO SHINE_POST (POST_TYPE, BODY, CREATED_TIMESTAMP, VIEW_COUNT, VOTE, IS_ACCEPTED, QUESTION_ID)
VALUES ('ANSWER', '	{
  "blocks" : [ {
    "key" : "bn3pf",
    "text" : "sample answer body 3 for question -3 ",
    "type" : "unstyled",
    "depth" : 0,
    "inlineStyleRanges" : [ {
      "offset" : 0,
      "length" : 36,
      "style" : "color-rgb(51,51,51)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "bgcolor-rgb(255,255,255)"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontsize-18"
    }, {
      "offset" : 0,
      "length" : 36,
      "style" : "fontfamily-sans-serif"
    } ],
    "entityRanges" : [ ],
    "data" : { }
  } ],
  "entityMap" : { }
}', CURRENT_TIMESTAMP() + 3, 0, 0, TRUE, -3);

-- TAG
INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-1, 'java', 0);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-2, 'programming', 0);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-3, 'html', 5);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-4, 'C++', 10);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-5, 'object-Oriented', 10);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-6, 'JavaScript', 10);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-7, 'Database', 6);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-8, 'sql', 7);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-9, 'groovy', 8);

INSERT INTO SHINE_TAG (ID, NAME, USED_COUNT)
VALUES (-10, 'Scala', 10);


-- QUESTION_TAG
INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID)
VALUES (-3, -4);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID)
VALUES (-3, -3);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID)
VALUES (-3, -2);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID)
VALUES (-2, -1);

INSERT INTO SHINE_QUESTION_TAG (QUESTION_ID, TAG_ID)
VALUES (-3, -1);

-- SHINE SEARCH FIELD
INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (1, 'POST', 'body', 'q', 'Post Body');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (2, 'POST', 'editedTimeStamp', 'recent', 'Recent Post');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (3, 'POST', 'postType', 'postType', 'Post Type');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (4, 'POST', 'vote', 'vote', 'Post Vote');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (5, 'Question', 'answerCount', 'answerCount', 'Answer Count');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (6, 'Question', 'tagList.name', 'tag', 'Tag');

INSERT INTO SHINE_SEARCH_FIELD (ID, ENTITY_TYPE, PROPERTY_NAME, ABBREVIATION, FRIENDLY_NAME)
VALUES (7, 'Tag', 'usedCount', 'usedCount', 'Used Count');










