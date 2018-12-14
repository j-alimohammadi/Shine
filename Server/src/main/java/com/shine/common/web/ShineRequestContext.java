package com.shine.common.web;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineRequestContext {

    ShineRequestContext getShineRequestContext();

    void setShineRequestContext(ShineRequestContext shineRequestContext);

    String setIpAddress(String ipAddress);

    String getIpAddress();
}
