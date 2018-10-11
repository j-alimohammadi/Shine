package com.shine.common.rest.api.wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * This interface is the super interface for all classes that will provide a JAXB wrapper
 * around classes.  Any class that will be exposed via JAXB annotations to the JAXRS API
 * may implement this as a convenience to provide a standard method to populate data objects.
 * <p>
 * <b>Note:</b> Adapted from BroadleafCommerce: https://github.com/BroadleafCommerce/BroadleafCommerce
 */


public interface APIWrapper<T> {
    void wrap(T model, HttpServletRequest request);
}
