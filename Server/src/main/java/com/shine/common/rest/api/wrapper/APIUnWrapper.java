package com.shine.common.rest.api.wrapper;

import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * This interface is the super interface for all classes that will provide a JAXB unwrapper
 * around classes.  Any class that will be exposed via JAXB annotations to the JAXRS API
 * may implement this as a convenience to provide a standard method to unwrap data objects.
 * <p>
 * <b>Note:</b> Adapted from BroadleafCommerce: https://github.com/BroadleafCommerce/BroadleafCommerce
 */

public interface APIUnWrapper<T> {
    T unwrap(HttpServletRequest request, ApplicationContext context);

}
