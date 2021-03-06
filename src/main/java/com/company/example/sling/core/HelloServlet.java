package com.company.example.sling.core;

import java.io.IOException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

/**
 * @link http://[host]:[port]/content/example.hello.json
 */
@Component(service = Servlet.class)
@SlingServletResourceTypes(
  resourceTypes = "example/hello",
  extensions = "json",
  selectors = "hello",
  methods = "GET"
)
public class HelloServlet extends SlingAllMethodsServlet {

  @Override
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws IOException {
    final String text = request.getParameter("text");
    if (text == null || text.isEmpty()) {
      respond(response, "Text cannot be empty!");
      return;
    }

    respond(response, text);
  }

  private void respond(SlingHttpServletResponse response, Object object) throws IOException {
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().write("{\"result\": \"" + object.toString() + "\"}");
  }
}
