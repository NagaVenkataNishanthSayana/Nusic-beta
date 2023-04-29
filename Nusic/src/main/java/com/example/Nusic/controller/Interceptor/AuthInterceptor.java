package com.example.Nusic.controller.Interceptor;

import com.example.Nusic.exception.UnAuthorizedUserException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private boolean isValidSession(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        sessionId=request.getHeader("SESSION_ID");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName);
            // use the header value here
        }
        System.out.println(sessionId);
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("SESSION_ID")) {
//                    sessionId = cookie.getValue();
//                    System.out.println(sessionId);
//                    break;
//                }
//            }
//        }
        if (sessionId != null) {
            HttpSession session = request.getSession(false);
            if(session==null) System.out.println("Null");
            if (session != null && session.getId().equals(sessionId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!isValidSession(request)) {
            throw new UnAuthorizedUserException("UnAuthorized Session");
        }
        return true;
    }
}
