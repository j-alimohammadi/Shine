package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.common.rest.api.wrapper.APIUnWrapper;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.common.utils.JSONMapper;
import com.shine.core.domain.Answer;
import com.shine.core.domain.Post;
import com.shine.core.domain.PostType;
import com.shine.core.domain.Question;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Component("PostWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostWrapper extends BaseWrapper implements APIUnWrapper<Post>, APIWrapper<Post> {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("body")
    private Map<String, Object> body;

    @JsonProperty("vote")
    private Long vote;

    @JsonProperty("post_type")
    private String postType;


    ///////////////////////////////////////
    //       Answer property
    ///////////////////////////////////////
    @JsonProperty("question_id")
    private Long questionId;


    @JsonProperty("is_answer_accept")
    private Boolean isAnswerAccept;

    ///////////////////////////////////////
    //       Question property
    ///////////////////////////////////////
    @JsonProperty("title")
    private String title;

    @JsonProperty(value = "answer_count")
    private Long answerCount;

    @JsonProperty(value = "question_url")
    private String questionURL;

    @JsonProperty(value = "tag_names")
    private List<String> tagNames = new ArrayList<>();


    @Override
    public Post unwrap(HttpServletRequest request, ApplicationContext context) {
        return null;
    }


    @Override
    public void wrap(Post model, HttpServletRequest request) {
        this.id = model.getId();
        this.body = JSONMapper.createHashMapFromJSON(model.getBody());
        this.vote = model.getVote();
        this.postType = model.getPostType().typeName;

        if (model.getPostType() == PostType.ANSWER) {
            Answer answer = (Answer) model;
            this.isAnswerAccept = answer.getAccepted();
        } else if (model.getPostType() == PostType.POST) {
            Question question = (Question) model;
            

        }
    }
}
