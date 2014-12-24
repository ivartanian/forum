package com.core.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by i.vartanian on 24.12.2014.
 */
public class FilterCharSet implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
