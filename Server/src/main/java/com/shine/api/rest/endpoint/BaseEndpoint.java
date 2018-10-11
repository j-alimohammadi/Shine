package com.shine.api.rest.endpoint;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public abstract class BaseEndpoint implements ApplicationContextAware {
    protected ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
