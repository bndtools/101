package blog.provider;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import blog.api.Blog;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Component(properties = {"osgi.command.scope=blog", "osgi.command.function=listEntries|addEntry"}, immediate = true)
public class BlogProvider implements Blog {
	
	private DB db;
	private MessageDigest digest;

	@Activate
	void activate() throws Exception {
		MongoClient client = new MongoClient("localhost");
		db = client.getDB("blog");
		
		digest = MessageDigest.getInstance("SHA-256");
	}

	@Override
	public List<String> listEntries() throws Exception {
		DBCollection entries = db.getCollection("entries");
		List<String> result = new ArrayList<String>((int) entries.count());
		for (DBObject obj : entries.find()) {
			result.add((String) obj.get("text"));
		}
		return result;
	}

	@Override
	public void addEntry(String content) throws Exception {
		DBCollection entries = db.getCollection("entries");
		String hash = new String(digest.digest(content.getBytes()));
		BasicDBObject obj = new BasicDBObject("hash", hash).append("text", content);
		entries.insert(obj);
	}

}
