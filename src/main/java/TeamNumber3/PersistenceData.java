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
			File.separator + indexFileName;
	private static File indexFile = new File(indexPath);
	private static JSONArray dataFiles = null;
	private static JSONArray dataWords = null;
	
	public static void setFilesIndexed(long num) {
		filesIndexed = (int) num;
	}
	
	public static JSONObject getIndex() {
		JSONObject retObj = null;
		
		if (index == null) {
			// Checking if the index file exists
			if (indexFile.exists()) {
				System.out.println("Index file exists!");
				JSONParser parser = new JSONParser();
				// Reading from the index file
				try (FileReader read = new FileReader(indexPath)) {
					Object obj = parser.parse(read);
					index = (JSONObject) obj;
					retObj = index;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// Index file doesn't exist
				// The code that calls on this function should check if 
				//   the returned JSONObject is null before doing stuff 
				System.out.println(indexPath + " was not found.");
			}
		} else {
			retObj = index;
		}
		return retObj;
	}
	
	public static JSONArray getFiles() {
		JSONArray retArr = new JSONArray();
		if (getIndex() != null) {
			try {
				// Getting the files list
				dataFiles = (JSONArray) ((JSONObject) (PersistenceData.getIndex().get("data"))).get("files");
				retArr = dataFiles;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Cannot get files; index doesn't exist.");
		}
		return retArr;
	}
	
	public static JSONArray getWords() {
		JSONArray retArr = new JSONArray();
		if (getIndex() != null) {
			try {
				// Getting the words list
				dataWords = (JSONArray) ((JSONObject) (PersistenceData.getIndex().get("data"))).get("words");
				retArr = dataWords;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Cannot get words; index doesn't exist.");
		}
		return retArr;
	}
	
	static int getNumFilesIndexed() {
		return filesIndexed;
	}
	
	static JSONObject getFileData(String path) {
		// Checking if a file already exists in the index
		JSONObject retObj = null;
		if (getFiles() != null) {
			JSONObject file = null;
			// Looping through the dataFiles list
			for(int i = 0; i < dataFiles.size(); i++) {
				String filePath = (String) ((JSONObject) dataFiles.get(i)).get("path");
				// Checking if the file is in the index
				if (filePath.equals(path)) {
					file = (JSONObject) dataFiles.get(i);
					retObj = file;
				}
			}
			System.out.println(path + " is indexed?: " + (file != null));
		}
		return retObj;
	}
	
	static void addFileToIndex (persistenceFile pf) {
		pf.fileNumber = filesIndexed;
		filesIndexed++;

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
