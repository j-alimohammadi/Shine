package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.common.rest.api.wrapper.APIUnWrapper;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.common.utils.JSONMapper;
import com.shine.core.qa.domain.Question;
import com.shine.core.qa.domain.Tag;
import com.shine.core.qa.service.QuestionService;
import com.shine.core.qa.service.TagService;
import com.shine.core.qa.service.TagServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("QuestionWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class QuestionWrapper extends BaseWrapper implements APIUnWrapper<Question>, APIWrapper<Question> {
    @JsonIgnore
    @Resource
    private TagService tagService;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("question_title")
    private String questionTitle;

    @JsonProperty("body")
    private Map<String, Object> body;

    @JsonProperty("vote")
    private Long vote;

    @JsonProperty(value = "answer_count")
    private Long answerCount;

    @JsonProperty(value = "question_url")
    private String questionURL;

    @JsonProperty(value = "view_count")
    private Long viewCount;

    @JsonProperty(value = "tag_names")
    private List<String> tagNames = new ArrayList<>();

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(ArrayList<String> tagNames) {
        this.tagNames = tagNames;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    public String getQuestionURL() {
        return questionURL;
    }

    public void setQuestionURL(String questionURL) {
        this.questionURL = questionURL;
    }


    @Override
    public Question unwrap(HttpServletRequest request, ApplicationContext context) {
        final TagService tagService = context.getBean(TagServiceImpl.class);
        final QuestionService questionService = context.getBean(QuestionService.class);
        tagService.createNotExistTags(this.tagNames);

        List<Tag> tagList = tagService.findTagsByName(this.tagNames);

        Question question = questionService.createQuestionFromId(this.id);
        question.setBody(JSONMapper.createJSON(body));
        question.setTitle(questionTitle);
        question.setVote(ObjectUtils.defaultIfNull(vote, 0L));
        question.setTagList(tagList);

        return question;
    }

    @Override
    public void wrap(Question model, HttpServletRequest request) {
        List<Tag> tagList = tagService.findTagsForQuestion(model);

        this.id = model.getId();
        this.questionTitle = model.getTitle();
        this.body = JSONMapper.createHashMapFromJSON(model.getBody());
        this.vote = model.getVote();
        this.questionURL = model.getQuestionAddress();
        this.answerCount = model.getAnswerCount();
        this.viewCount = model.getViewCount();
        this.tagNames = tagList
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

}
