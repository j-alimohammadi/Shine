package com.shine.api;

import com.shine.config.ApplicationConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
@Sql(scripts = "classpath:/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnswerEndPointTest {

}
