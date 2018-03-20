package TeamNumber3;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class PersistenceData {
	private static int filesIndexed = 0;
	private static JSONObject index = null;
	private static String indexFileName = "FSSIndex.json";
	private static String indexPath = System.getProperty("user.home") + 
			File.pathSeparator + indexFileName;
	private static File indexFile = new File(indexPath);
	
	public static JSONObject getIndex() {
		JSONObject retObj = null;
		
		if (index == null) {
			if (indexFile.exists()) {
				System.out.println("Index file exists!");
				JSONParser parser = new JSONParser();
				// Reading from the index file
				try (FileReader read = new FileReader(indexPath)) {
					Object obj = parser.parse(read);
					index = (JSONObject) obj;
					retObj = index;
				}
			} else {
				// Index file doesn't exist
				// The code that calls on this function should check if 
				//   the returned JSONOject is null before doing stuff 
				System.out.println("Index file not found.");
			}
		} else {
			retObj = index;
		}
		return retObj;
	}
	
	static int getNumFilesIndexed() {
		return filesIndexed;
	}
	
	static void addFileToIndex (persistenceFile pf) {
		pf.fileNumber = filesIndexed;
		filesIndexed++;
		
		// ...
	}
	
	void removeFileFromIndex () {
		
	}
	
	void checkFilesStatuses () {
		
	}
	
	void searchIndex () {
		
	}
	
	void readIndexFromFile () {
		
	}
	
	void writeIndexToFile () {
		
	}
}
