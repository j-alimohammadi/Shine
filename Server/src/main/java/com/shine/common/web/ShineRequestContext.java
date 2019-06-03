package com.shine.common.web;

import com.shine.common.utils.ThreadLocalManager;

import java.util.UUID;

/**
 * Convenient holder class for various objects to be automatically available on thread local without invoking the various
 * services yourself
 * <p>
 * <b>Note:</b> Adapted from BroadleafCommerce: https://github.com/BroadleafCommerce/BroadleafCommerce which is under
 * "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt
 */

public class ShineRequestContext {


    private final static ThreadLocal<ShineRequestContext> SHINE_REQUEST_CONTEXT = ThreadLocalManager.createThreadLocal(ShineRequestContext.class);

    public static ShineRequestContext getShineRequestContext() {
        return SHINE_REQUEST_CONTEXT.get();
    }

    public static void setShineRequestContext(ShineRequestContext shineRequestContext) {
        SHINE_REQUEST_CONTEXT.set(shineRequestContext);
    }

    private UUID sessionId;

    private String IpAddress;

    public String setIpAddress(String ipAddress) {
        return this.IpAddress = ipAddress;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

}
