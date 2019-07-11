package com.shine.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnswerEndPointTest {

    @Resource
    private MockMvc mockMvc;


    @Test
    public void testCreateNewAnswer() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/singer/1")
                        .content()
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("John"))
                .andDo(MockMvcResultHandlers.print());
    }

}
