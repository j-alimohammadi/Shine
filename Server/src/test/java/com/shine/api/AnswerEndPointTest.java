package com.shine.api;

import com.shine.api.dto.AnswerRequest;
import com.shine.api.dto.AnswerResponse;
import com.shine.api.dto.ErrorResponse;
import com.shine.test.helper.JSONPathUtility;
import com.shine.test.helper.TestJSONMapper;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class AnswerEndPointTest extends BasePostControllerTest {

    @Test
    public void testCreateNewAnswerHappyPath() throws Exception {

        HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
            put("body", "sample answer");
        }};

        String responseBody = createNewAnswer(hashMap, -1L);

        final Long answerId = JSONPathUtility.read(responseBody, "id", Long.class);

        AnswerResponse expectedResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
                .withId(answerId)
                .withQuestionId(-1L)
                .withBody(
                        new HashMap<String, Object>() {{
                            put("body", "sample answer");
                        }}
                )
                .withVote(0L)
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBody, true);
    }

    @Test
    public void testCreateNewAnswerWhenBodyIsEmpty() throws Exception {
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{

                }})
                .questionId(-1L)
                .build();

        String responseBody = performPostRequest(HttpStatus.BAD_REQUEST, "/answer", answerRequest);


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Post body is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBody, true);
    }

    @Test
    public void testCreateNewAnswerWhenQuestionIdIsInvalid() throws Exception {
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated");
                }})
                .questionId(Long.MIN_VALUE)
                .build();

        String responseBody = performPostRequest(HttpStatus.BAD_REQUEST, "/answer", answerRequest);


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Question id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBody, true);
    }

    @Test
    public void testUpdateAnswerHappyPath() throws Exception {

        ///////////////////////////////////
        ////        Create Answer    /////
        ///////////////////////////////////
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated");
                }})
                .questionId(-1L)
                .build();

        String actualResponse = performPostRequest(HttpStatus.OK, "/answer", answerRequest);

        final Long answerId = JSONPathUtility.read(actualResponse, "id", Long.class);


        ///////////////////////////////////
        ////        Update Answer      ////
        ///////////////////////////////////
        AnswerRequest updateAnswerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated!!!");
                }})
                .questionId(-1L)
                .id(answerId)
                .build();

        String responseBodyForUpdateAnswer = performPutRequest(HttpStatus.OK, "/answer", updateAnswerRequest);


        AnswerResponse expectedResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
                .withId(answerId)
                .withQuestionId(-1L)
                .withBody(
                        new HashMap<String, Object>() {{
                            put("body", "sample answer updated!!!");
                        }}
                )
                .withVote(0L)
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBodyForUpdateAnswer, true);
    }


    @Test
    public void testUpdateAnswerWhenAnswerNotExist() throws Exception {
        AnswerRequest updateAnswerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated!!!");
                }})
                .questionId(-1L)
                .id(Long.MIN_VALUE)
                .build();

        String responseBodyForUpdateAnswer = performPutRequest(HttpStatus.BAD_REQUEST, "/answer", updateAnswerRequest);


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Answer id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBodyForUpdateAnswer, true);
    }

    @Test
    public void testDeleteAnswerHappyPath() throws Exception {
        ///////////////////////////////////
        ////        Create Answer     /////
        ///////////////////////////////////
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample new answer");
                }})
                .questionId(-1L)
                .build();

        String responseBodyForCreateAnswer = performPostRequest(HttpStatus.OK, "/answer", answerRequest);
        final Long answerId = JSONPathUtility.read(responseBodyForCreateAnswer, "id", Long.class);

        ///////////////////////////////////
        ////        Delete Answer     /////
        ///////////////////////////////////
        String actualResponse = performDeleteRequest(HttpStatus.OK, "/answer/{answer_id}", answerId);

        AnswerResponse expectedAnswerResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
                .withId(answerId)
                .withBody(new HashMap<String, Object>() {{

                    put("body", "sample new answer");
                }})
                .withQuestionId(-1L)
                .withVote(0L)
                .withIsAccepted(false)
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedAnswerResponse), actualResponse, true);

    }

    @Test
    public void testDeleteAnswerWhenAnswerIdIsInvalid() throws Exception {

        ///////////////////////////////////
        ////        Delete Answer     /////
        ///////////////////////////////////
        String actualResponse = performDeleteRequest(HttpStatus.BAD_REQUEST, "/answer/{answerId}", Long.MIN_VALUE);

        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Answer id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), actualResponse, true);

    }


    //todo : complete after writing test for question webservice
    @Test
    public void testFindAnswersForQuestionHappyPath() throws Exception {
        ///////////////////////////////////
        ////        Create Answer     /////
        ///////////////////////////////////
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample test1");
                }})
                .questionId(-1L)
                .build();


        ///////////////////////////////////
        ////        Find Answer      /////
        ///////////////////////////////////
        String actualResponse = performGetRequest(HttpStatus.OK, "/answer/question/{questionId}", -1L);
        final Long answerId = JSONPathUtility.read(actualResponse, "$[0].id", Long.class);
        AnswerResponse expectedResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
                .withId(answerId)
                .withBody(new HashMap<String, Object>() {{
                    put("body", "sample new answer 1");
                }})
                .withQuestionId(-1L)
                .withVote(0L)
                .withIsAccepted(false)
                .build();


        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(Arrays.asList(expectedResponse)), actualResponse, true);
    }

    @Test
    public void testIncrementVote() throws Exception {

    }


}
