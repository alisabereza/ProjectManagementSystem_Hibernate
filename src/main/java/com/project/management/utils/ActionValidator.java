package com.project.management.utils;

import javax.servlet.http.HttpServletRequest;

public class ActionValidator {
    public static String getAction (HttpServletRequest req){
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
