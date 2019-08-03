package com.shine.test.helper;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URI;

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


    public String performPostRequest(HttpStatus expectedStatus, final String path, final Object content) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(path)
                        .content(TestJSONMapper.createJSONFromObject(content))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);
    }

    public String performPutRequest(HttpStatus expectedStatus, final String path,
                                    final Object content, Object... parameters) throws Exception {

        UriTemplate uriTemplate = new UriTemplate(path);
        URI expand = uriTemplate.expand(parameters);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put(expand)
                        .content(TestJSONMapper.createJSONFromObject(content))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);
    }


    public String performDeleteRequest(HttpStatus expectedStatus, final String path, Object... parameters) throws Exception {

        UriTemplate uriTemplate = new UriTemplate(path);
        URI expandedURL = uriTemplate.expand(parameters);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete(expandedURL)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);
    }

    public String performGetRequest(HttpStatus expectedStatus, final String path, Object... parameters) throws Exception {
        UriTemplate uriTemplate = new UriTemplate(path);
        URI expandedURL = uriTemplate.expand(parameters);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(expandedURL)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return responseContentAsString(mvcResult);

    }


    private String responseContentAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

}
