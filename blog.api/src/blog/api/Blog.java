package blog.api;

import java.util.List;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Blog {

	List<String> listEntries() throws Exception;
	
	void addEntry(String content) throws Exception;

}
