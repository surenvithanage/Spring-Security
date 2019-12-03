package com.security.complete.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project complete
 * User : suren_v
 * Date : 12/3/2019
 * Time : 10:51 AM
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        // You can create your own repsonse here to handle method level access denied reponses..
        // Follow similar method to the bad credentials handler above.
        System.out.println("------------------ TESTING -------------------");
    }

}
