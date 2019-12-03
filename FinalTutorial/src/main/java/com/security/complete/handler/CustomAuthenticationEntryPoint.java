package com.security.complete.handler;

import com.google.gson.Gson;
import com.security.complete.dto.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * Project complete
 * User : suren_v
 * Date : 12/3/2019
 * Time : 9:24 AM
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) {
        CustomError error = new CustomError(HttpStatus.FORBIDDEN, "error", "Invalid credentials");
        Gson gson = new Gson();
        try {
            String json = gson.toJson(error);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(json);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
