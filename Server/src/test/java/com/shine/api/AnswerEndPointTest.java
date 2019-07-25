package com.shine.api;

import com.shine.api.dto.AnswerRequest;
import com.shine.api.dto.AnswerResponse;
import com.shine.api.dto.ErrorResponse;
import com.shine.test.helper.JSONPathUtility;
import com.shine.test.helper.SpringBootTestAPIHelper;
import com.shine.test.helper.TestJSONMapper;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class AnswerEndPointTest extends SpringBootTestAPIHelper {

    @Test
    public void testCreateNewAnswerHappyPath() throws Exception {

        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer");
                }})
                .questionId(-1L)
                .build();

        String responseBody = performPostRequest("/answer", answerRequest, HttpStatus.OK.value());

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

        String responseBody = performPostRequest("/answer", answerRequest, HttpStatus.BAD_REQUEST.value());


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

        String responseBody = performPostRequest("/answer", answerRequest, HttpStatus.BAD_REQUEST.value());


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Question id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBody, true);
    }

    @Test
    public void testUpdateAnswerHappyPath() throws Exception {

        // create new answer
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated");
                }})
                .questionId(-1L)
                .build();

        String responseBodyForCreateAnswer = performPostRequest("/answer", answerRequest, HttpStatus.OK.value());

        final Long answerId = JSONPathUtility.read(responseBodyForCreateAnswer, "id", Long.class);

        // update answer
        AnswerRequest updateAnswerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated!!!");
                }})
                .questionId(-1L)
                .id(answerId)
                .build();

        String responseBodyForUpdateAnswer = performPutRequest("/answer", updateAnswerRequest, HttpStatus.OK.value());


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

        String responseBodyForUpdateAnswer = performPutRequest("/answer", updateAnswerRequest, HttpStatus.BAD_REQUEST.value());


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Answer id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseBodyForUpdateAnswer, true);
    }


}
