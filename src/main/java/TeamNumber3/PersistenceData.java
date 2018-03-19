package TeamNumber3;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class PersistenceData {
	private static int filesIndexed = 0;
	
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
