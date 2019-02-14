package net.michalfoksa.demos.workshop.workstation.http.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger2.web.Swagger2Controller;

@Component
public class ApiDocsCorsFilter implements javax.servlet.Filter {

    // Default to Swagger2Controller.DEFAULT_URL
    @Value("${springfox.documentation.swagger.v2.path:" + Swagger2Controller.DEFAULT_URL + "}")
    private String apiDocsPath;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        if (apiDocsPath.equals(((HttpServletRequest) request).getServletPath())) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.addHeader("Access-Control-Allow-Origin", "*");
            // res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE,
            // PUT");
            res.addHeader("Access-Control-Allow-Methods", "GET");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
