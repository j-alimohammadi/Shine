package com.shine.test.helper;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTestAPIHelper {

    @Resource
    protected MockMvc mockMvc;


    public void performGetRequest() {

    }


}
