package com.shine.api;

import com.shine.api.dto.AnswerRequest;
import com.shine.test.helper.SpringBootTestAPIHelper;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public abstract class BasePostControllerTest extends SpringBootTestAPIHelper {
    protected String createNewAnswer(HashMap<String, Object> body, Long questionId) throws Exception {
        AnswerRequest answerRequest = AnswerRequest.builder()
                .body(body)
                .questionId(questionId)
                .build();

        return performPostRequest(HttpStatus.OK, "/answer", answerRequest);
    }

    protected String deleteAnswer() {
        throw new UnsupportedOperationException();
    }

}