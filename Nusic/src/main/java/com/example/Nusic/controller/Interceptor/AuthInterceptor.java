package com.example.Nusic.controller.Interceptor;

import com.example.Nusic.exception.UnAuthorizedUserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private boolean isValidSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("SESSION_ID") != null) {
            String sessionId = session.getAttribute("SESSION_ID").toString();
            if ("SESSION_ID".equals(sessionId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!isValidSession(request)) {
            throw new UnAuthorizedUserException("Un Authorized Session");
        }
        return true;
    }
}
