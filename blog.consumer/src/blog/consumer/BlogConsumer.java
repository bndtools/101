package blog.consumer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aQute.bnd.annotation.component.*;

@Component(
		properties = {
				"alias=/blog"
		})
public class BlogConsumer extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/plain");
		PrintWriter writer = resp.getWriter();
		writer.println("Hello there");
		writer.flush();
		writer.close();
	}

}
