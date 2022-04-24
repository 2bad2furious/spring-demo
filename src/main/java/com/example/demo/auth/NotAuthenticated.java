package com.example.demo.auth;

import javax.servlet.ServletException;

public class NotAuthenticated extends ServletException {
    public NotAuthenticated() {
        super("Not Authenticated");
    }
}
