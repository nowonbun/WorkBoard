package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebFilter implements Filter {

  private List<String> passUrl = null;
  private String contextPath;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    contextPath = filterConfig.getServletContext().getContextPath();
    passUrl = new ArrayList<String>();
    String[] ignoredPaths = filterConfig.getInitParameter("passPage").split(",");
    for (String ignoredPath : ignoredPaths) {
      passUrl.add(contextPath + ignoredPath.trim());
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    if (session.getAttribute(SessionName.USER) != null) {
      chain.doFilter(req, res);
      return;
    }
    String url = req.getRequestURI();
    for (String buf : passUrl) {
      if (buf.equals(url)) {
        chain.doFilter(req, res);
        return;
      }
    }
    res.sendRedirect(contextPath + "/login.html");
  }
}
