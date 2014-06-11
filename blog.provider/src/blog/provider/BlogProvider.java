package blog.provider;

import java.util.List;

import blog.api.Blog;
import aQute.bnd.annotation.component.*;

@Component
public class BlogProvider implements Blog {

	@Override
	public List<String> listEntries() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEntry(String content) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
