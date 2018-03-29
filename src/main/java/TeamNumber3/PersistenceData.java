// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

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
	private static List<persistenceFile> listOfFiles = new ArrayList<persistenceFile>();
	
	public static List<persistenceFile> getListOfFiles() {
		return listOfFiles;
	}
	
	static int getNumFilesIndexed() {
		return filesIndexed;
	}
	
	static void updateNumFilesIndexed() {
		filesIndexed = getListOfFiles().size();
	}
	
	public static void addToListOfFiles(persistenceFile pf) {
		pf.fileNumber = getNumFilesIndexed();
		listOfFiles.add(pf);
		updateNumFilesIndexed();
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
	
	static void readIndexFromFile () {
		// Checking if a file already exists in the index
		if (getFiles() != null) {
			// Looping through the dataFiles list
			for(int i = 0; i < dataFiles.size(); i++) {
				JSONObject file = (JSONObject) dataFiles.get(i);
				String filePath = (String) file.get("path");
				long fileModified = (long) file.get("modified");
				// Creating a new persistenceFile object for the file
				persistenceFile pf = new persistenceFile();
				pf.filepath = filePath;
				pf.dateModified = fileModified;
				addToListOfFiles(pf);
			}
		}
	}
	
	static void writeIndexToFile () {
		
	}
	
}
