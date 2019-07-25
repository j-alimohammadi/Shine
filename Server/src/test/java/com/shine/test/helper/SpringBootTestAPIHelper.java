package com.shine.test.helper;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class SpringBootTestAPIHelper {

    @Resource
    protected MockMvc mockMvc;


    public String performPostRequest(final String path, final Object content, int expectedStatus) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(path)
                        .content(TestJSONMapper.createJSONFromObject(content))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);
    }

    public String performPutRequest(final String path, final Object content, int expectedStatus) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put(path)
                        .content(TestJSONMapper.createJSONFromObject(content))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);
    }



    private String responseContentAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

}
