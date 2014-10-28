package org.tessa.web.web;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LayoutInterceptor extends HandlerInterceptorAdapter {
    private static final String LAYOUTS_DIR = "layouts/";
    private static final String DEFAULT_LAYOUT = "default";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }
        String layout = getLayout(handler);
        modelAndView.setViewName(layout);
        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
    }

    private String getLayout(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
        String name = layout != null && layout.value() != null ? layout.value() : DEFAULT_LAYOUT;
        return LAYOUTS_DIR + name;
    }

    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }
}
