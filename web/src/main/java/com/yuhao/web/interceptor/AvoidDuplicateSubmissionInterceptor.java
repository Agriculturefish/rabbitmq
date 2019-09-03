package com.yuhao.web.interceptor;

import com.yuhao.web.token.TokenProcessor;
import com.yuhao.web.annotation.SubToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AvoidDuplicateSubmissionInterceptor  extends
        HandlerInterceptorAdapter {

    public AvoidDuplicateSubmissionInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SubToken annotation = method
                    .getAnnotation(SubToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.saveToken();
                if (needSaveSession) {
                    request.getSession()
                            .setAttribute(
                                    "subToken",
                                    TokenProcessor.getInstance().generateToken(
                                            request));
                }

                boolean needRemoveSession = annotation.removeToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute("subToken");
                }
            }
        }
        return true;
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(
                "subToken");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("subToken");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
