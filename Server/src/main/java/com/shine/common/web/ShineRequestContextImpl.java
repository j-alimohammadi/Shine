package com.shine.common.web;

import com.shine.common.utils.ThreadLocalManager;
import org.springframework.stereotype.Service;

/**
 * Convenient holder class for various objects to be automatically available on thread local without invoking the various
 * services yourself
 * <p>
 * <b>Note:</b> Adapted from BroadleafCommerce: https://github.com/BroadleafCommerce/BroadleafCommerce which is under
 * "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt
 */
@Service("ShineRequestContext")
public class ShineRequestContextImpl implements ShineRequestContext {


    private final ThreadLocal<ShineRequestContext> SHINE_REQUEST_CONTEXT = ThreadLocalManager.createThreadLocal(ShineRequestContext.class);

    @Override
    public ShineRequestContext getShineRequestContext() {
        return SHINE_REQUEST_CONTEXT.get();
    }

    @Override
    public void setShineRequestContext(ShineRequestContext shineRequestContext) {
        SHINE_REQUEST_CONTEXT.set(shineRequestContext);
    }


    private String IpAddress;

    @Override
    public String setIpAddress(String ipAddress) {
        return this.IpAddress = ipAddress;
    }

    @Override
    public String getIpAddress() {
        return IpAddress;
    }
}
