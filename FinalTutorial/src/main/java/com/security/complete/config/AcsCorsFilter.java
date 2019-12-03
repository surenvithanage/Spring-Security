package com.security.complete.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 3:22 PM
 */
@Component
public class AcsCorsFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(AcsCorsFilter.class);

    public AcsCorsFilter() {
        logger.info("Cors Filter Initializer");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IllegalStateException {
        logger.info("---------------------- Cross Origin Filter -----------------------");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
