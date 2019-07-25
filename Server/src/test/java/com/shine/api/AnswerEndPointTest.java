package com.shine.api;

import com.jayway.jsonpath.JsonPath;
import com.shine.api.dto.AnswerRequest;
import com.shine.api.dto.AnswerResponse;
import com.shine.api.dto.ErrorResponse;
import com.shine.test.helper.SpringBootTestAPIHelper;
import com.shine.test.helper.TestJSONMapper;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
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
                    put("body", "sample answer updated");
                }})
                .questionId(-1L)
                .build();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/answer")
                        .content(TestJSONMapper.createJSONFromObject(answerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        final Long responseId = JsonPath
                .parse(responseContentAsString(mvcResult))
                .read("id", Long.class);

        AnswerResponse expectedResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
                .withId(responseId)
                .withBody(
                        new HashMap<String, Object>() {{
                            put("body", "sample answer");
                        }}
                )
                .withVote(0L)
                .build();


        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContentAsString(mvcResult), true);

    }

    @Test
    public void testCreateNewAnswerWhenBodyIsEmpty() throws Exception {
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{

                }})
                .questionId(-1L)
                .build();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/answer")
                        .content(TestJSONMapper.createJSONFromObject(answerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Post body is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContentAsString(mvcResult), true);
    }

    @Test
    public void testCreateNewAnswerWhenQuestionIdIsInvalid() throws Exception {
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated");
                }})
                .questionId(Long.MIN_VALUE)
                .build();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/answer")
                        .content(TestJSONMapper.createJSONFromObject(answerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Question id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContentAsString(mvcResult), true);
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

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/answer")
                        .content(TestJSONMapper.createJSONFromObject(answerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final Long answerId = JsonPath
                .parse(responseContentAsString(mvcResult))
                .read("id", Long.class);

        // update answer
        AnswerRequest updateAnswerRequest = AnswerRequest.builder()
                .body(new HashMap<String, Object>() {{
                    put("body", "sample answer updated!!!");
                }})
                .questionId(-1L)
                .id(answerId)
                .build();

        mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/answer")
                        .content(TestJSONMapper.createJSONFromObject(updateAnswerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


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

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContentAsString(mvcResult), true);
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

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/answer")
                        .content(TestJSONMapper.createJSONFromObject(updateAnswerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        ErrorResponse expectedResponse = ErrorResponse.builder()
                .httpStatus(400)
                .messages(Arrays.asList("Answer id is invalid"))
                .build();

        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContentAsString(mvcResult), true);
    }


    private static String responseContentAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

}
