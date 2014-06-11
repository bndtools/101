package blog.consumer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.api.Blog;
import aQute.bnd.annotation.component.*;
import aQute.lib.io.IO;

@Component(
		properties = {
				"alias=/blog"
		})
public class BlogConsumer extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private Blog blog;
	
	@Reference
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/plain");
		PrintWriter writer = resp.getWriter();
		
		try {
			for (String entry : blog.listEntries()) {
				writer.println(entry);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("END");
		writer.flush();
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = IO.collect(req.getInputStream());
		try {
			blog.addEntry(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
