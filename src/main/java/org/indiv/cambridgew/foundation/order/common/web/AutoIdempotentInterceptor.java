package org.indiv.cambridgew.foundation.order.common.web;

import org.indiv.cambridgew.foundation.order.manager.common.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author cambridge.w
 * @since 2021/8/27
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Resource
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AutoIdempotent methodAnnotation = handlerMethod.getMethod().getAnnotation(AutoIdempotent.class);
        if (null != methodAnnotation) {
            tokenManager.assertTokenAffective(request);
        }
        return true;
    }

}
