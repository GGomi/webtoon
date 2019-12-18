package com.essri.webtoon.filter;

import com.essri.webtoon.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

import static com.essri.webtoon.exception.ErrorCode.HANDLE_ACCESS_DENIED;

@Slf4j
@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> headerNames = req.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if(name.equals("token")) {
                    log.info("token is {}", req.getHeader(name));
                    // TODO req token 이 유효한지 검사
                }
            }
        } else {
            throw new BusinessException(HANDLE_ACCESS_DENIED);
        }

        chain.doFilter(request, response);

    }
}
