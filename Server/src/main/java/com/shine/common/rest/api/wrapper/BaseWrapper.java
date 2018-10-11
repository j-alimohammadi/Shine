package com.shine.common.rest.api.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public abstract class BaseWrapper implements ApplicationContextAware {
    @JsonIgnore
    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
