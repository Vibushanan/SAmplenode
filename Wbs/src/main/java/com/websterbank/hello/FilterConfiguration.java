package com.websterbank.hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
public class FilterConfiguration {

    public static final String LOGGER_NAME = "com.websterbank.services.RequestResponseLoggingFilter";
    public static final String REQUEST_ID  = "_REQUEST_ID_";

    @Bean
    FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean ret = new FilterRegistrationBean();

        ret.setFilter(new RequestResponseLoggingFilter());
        ret.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return ret;
    }

    class RequestResponseLoggingFilter extends OncePerRequestFilter {
        private final Logger logger = LoggerFactory.getLogger(LOGGER_NAME);

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            boolean debug = logger.isDebugEnabled();

            String uuid = UUID.randomUUID().toString();
            request.setAttribute(REQUEST_ID, uuid);

            if (debug) {
                logger.debug(String.format("Before Request: %s", new StringBuilder().append("uri=").append(request.getRequestURI())
                        .append(StringUtils.isEmpty(request.getQueryString()) ? "" : String.format("?%s", request.getQueryString()))
                        .append("; method=").append(request.getMethod())
                        .append("; client=").append(request.getRemoteAddr())
                        .append("; headers=").append(Collections.list(request.getHeaderNames())
                                .stream()
                                .map(name -> String.format("%s=%s", name, request.getHeader(name)))
                                .collect(Collectors.toList()))
                        .append("; requestId=").append(uuid)));
            }
            try {
                filterChain.doFilter(request, response);
            } finally {
                if (debug) {
                    logger.debug(String.format("After Request: %s", new StringBuilder()
                            .append("uri=").append(request.getRequestURI())
                            .append("; status=").append(response.getStatus())
                            .append("; headers=").append(response.getHeaderNames()
                                    .stream()
                                    .map(name -> String.format("%s=%s", name, response.getHeader(name)))
                                    .collect(Collectors.toList()))
                            .append("; requestId=").append(uuid)));
                }
            }
        }
    }

}