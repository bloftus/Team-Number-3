// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.lang.ref.Cleaner.Cleanable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexSearch {
	
	//Map<String, List<Pair>> wordMap;
	
	/*public void fillWordMap() {
		
		//test data for now
		Pair p1 = new Pair(1, 4);
		Pair p2 = new Pair(2, 5);
		Pair p3 = new Pair(1, 7);
		List<Pair> testlist = new ArrayList<>();
		testlist.add(p1);
		testlist.add(p2);
		testlist.add(p3);
		wordMap.put("java", testlist);
		wordMap.put("is", testlist);
		wordMap.put("fun", testlist);
		
	} */
	
	public static Set<Integer> orSearch(String userInput, Map<String, List<Pair>> wordMap) {
		
		String[ ] words = cleanInput(userInput).split(" ");
		
		Set<Integer> foundFiles = new HashSet<>();
		
		for(String word : words) {
			if (wordMap.containsKey(word)) {
				//File found!
				for(Pair wordLoc : wordMap.get(word)) {
					foundFiles.add(wordLoc.getFileNum());
					System.out.println(userInput + " was found in file " + wordLoc.getFileNum() + "!");
				}
			}
		}
		
		//if(foundFiles.size() == 0) {
			// display no files found to the user in some way...
		//} else {
			// display the files in the foundFiles array to the user in some way...
		//}
		return foundFiles;
	}
	
	public static Set<Integer> andSearch(String userInput, Map<String, List<Pair>> wordMap) {
		
		String[ ] words = cleanInput(userInput).split(" ");
		
		Set<Integer> foundFiles = new HashSet<>(Arrays.asList(1, 2, 3, 4));
		
		for(String word : words) {
			if (wordMap.containsKey(word)) {
				boolean found = false;
			
				for(int fileNum : foundFiles) {
					for(Pair wordLoc : wordMap.get(word)) {
						if(wordLoc.getFileNum() == fileNum) {
							found = true;
							System.out.println(userInput + " was found in file " + wordLoc.getFileNum() + "!");
						}
					}
					if(!found) {
						foundFiles.remove(fileNum);
					}
				}
			
			} else {
				// word not found in any file so search returns nothing.
				foundFiles.clear();
			}
		}
		
		//if(foundFiles.size() == 0) {
			// display no files found to the user in some way...
		//} else {
			// display the files in the foundFiles array to the user in some way...
		//}
		return foundFiles;
	}
	
	public static Set<Integer> phraseSearch(String userInput, Map<String, List<Pair>> wordMap) {
		
		String[ ] words = cleanInput(userInput).split(" ");
		
		Set<Pair> newPossibleSets = new HashSet<>();
		
		if(wordMap.containsKey(words[0])) {
			for(Pair wordLoc : wordMap.get(words[0])) {
				newPossibleSets.add(wordLoc);
			}
		}
		
		for(int i = 1; i < words.length; ++i) {
			
			Set<Pair> oldPossibleSets = new HashSet<>();
			oldPossibleSets.addAll(newPossibleSets);
			newPossibleSets.clear();
			
			if(wordMap.containsKey(words[i])) {
				for(Pair possibleSet : oldPossibleSets) {
					
					for(Pair wordLoc : wordMap.get(words[i])) {
						if(wordLoc.getFileNum() == possibleSet.getFileNum() && wordLoc.getPositionNum() == possibleSet.getPositionNum() + 1) {
							newPossibleSets.add(wordLoc);
							break;
						}
					}
					
				}
			} else {
				newPossibleSets.clear();
			}
		}
		
		//if(newPossibleSets.size() == 0) {
			// display no files found to the user in some way...
		//} else {
			// display the files in the foundFiles array to the user in some way...
		//}
		Set<Integer> foundFiles = new HashSet<>();
		for( Pair foundPair : newPossibleSets) {
			if (!foundFiles.contains(foundPair.getFileNum()))
				foundFiles.add(foundPair.getFileNum());
		}
		return foundFiles;
	}
	
	private static String cleanInput(String userInput) {
		String result = "";
		
		result = userInput.toLowerCase();
		result = userInput.replaceAll("[^A-Za-z ]", "");
		
		return result;
	}
}
 