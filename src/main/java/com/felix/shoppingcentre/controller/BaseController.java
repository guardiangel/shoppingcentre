package com.felix.shoppingcentre.controller;

import javax.servlet.http.HttpSession;

public class BaseController {

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return String.valueOf(session.getAttribute("username").toString());
    }

}
