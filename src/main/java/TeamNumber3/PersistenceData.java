// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;


import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class PersistenceData {
	private static int filesIndexed = 0;
	private static JSONObject index = null;
	private static String indexFileName = "FSSIndex.json";
	private static String indexPath = System.getProperty("user.home") + 
			File.separator + indexFileName;
	private static File indexFile = new File(getIndexPath());
	private static JSONArray dataFiles = null;
	private static JSONArray dataWords = null;
	private static List<persistenceFile> listOfFiles = new ArrayList<>();
	private static Map<String, List<Pair>> wordMap;
	
	public static List<persistenceFile> getListOfFiles() {
		return listOfFiles;
	}
	
	public static Map<String, List<Pair>> getWordMap() {
		return wordMap;
	}
	
	static int getNumFilesIndexed() {
		return filesIndexed;
	}
	
	static void updateNumFilesIndexed() {
		filesIndexed = getListOfFiles().size();
	}
	
	public static void addToListOfFiles(persistenceFile pf) {
		File file = new File(pf.filepath);
		pf.fileNumber = getNumFilesIndexed();
		listOfFiles.add(pf);
		updateNumFilesIndexed();
		
		// indexNewFile(pf.filepath, pf.fileNumber);
	}
	
	public static JSONObject getIndex() {
		JSONObject retObj = null;
		
		if (index == null) {
			// Checking if the index file exists
			if (indexFile.exists()) {
				System.out.println("Index file exists!");
				JSONParser parser = new JSONParser();
				// Reading from the index file
				try (FileReader read = new FileReader(getIndexPath())) {
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
				System.out.println(getIndexPath() + " was not found.");
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
	
	public static void indexNewFile(String filePath, int fileNumber) {
		// Reading a file
		Scanner scanner = null;
		File file = new File(filePath);
		int wordPosition = 0;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Reading a line from the file
		while (scanner.hasNextLine()) {
			Scanner scanner2 = new Scanner(scanner.nextLine());
			// Reading the next word from the file
			while (scanner2.hasNext()) {
				String word = scanner2.next();
				// Making a new pair for the word's position in the file
				Pair p = new Pair(fileNumber, wordPosition);
				List<Pair> wordList = new ArrayList<>();
				// Checking if the word is already indexed in other positions
				try {
					wordList = wordMap.get(word);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Adding the word to the index
				wordList.add(p);
				wordMap.put(word, wordList);
				wordPosition++;
			}
		}
	}
	
	static void writeIndexToFile () {
		
	}

	public static String getIndexPath() {
		return indexPath;
	}
	
}
