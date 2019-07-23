package com.shine.api;

import com.jayway.jsonpath.JsonPath;
import com.shine.api.dto.AnswerRequest;
import com.shine.api.dto.AnswerResponse;
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
import java.util.HashMap;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class AnswerEndPointTest extends SpringBootTestAPIHelper {

    @Test
    public void testCreateNewAnswerHappyPath() throws Exception {

        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.body = new HashMap<String, Object>() {{
            put("body", "sample answer");
        }};

        answerRequest.questionId = "-1";

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
                .parse(responseContent(mvcResult))
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


        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContent(mvcResult), true);

    }

    @Test
    public void testCreateNewAnswerWhenBodyIsEmpty() throws Exception {
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.body = new HashMap<String, Object>() {{
        }};

        answerRequest.questionId = "-1";

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/answer")
                        .content(TestJSONMapper.createJSONFromObject(answerRequest))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        System.out.println(mvcResult);
//        final Long responseId = JsonPath
//                .parse(responseContent(mvcResult))
//                .read("id", Long.class);
//
//        AnswerResponse expectedResponse = AnswerResponse.AnswerResponseBuilder.anAnswerResponse()
//                .withId(responseId)
//                .withBody(
//                        new HashMap<String, Object>() {{
//                            put("body", "sample answer");
//                        }}
//                )
//                .withVote(0L)
//                .build();


//        JSONAssert.assertEquals(TestJSONMapper.createJSONFromObject(expectedResponse), responseContent(mvcResult), true);
    }


    private static String responseContent(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

}
