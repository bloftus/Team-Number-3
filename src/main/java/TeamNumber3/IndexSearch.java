// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexSearch {
	
	Map<String, List<Pair>> wordMap;
	
	public void fillWordMap() {
		
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
		
	}
	
	public void orSearch(String userInput) {
		
		String[ ] words = userInput.split(" ");
		
		Set<Integer> foundFiles = new HashSet<>();
		
		for(String word : words) {
			if (wordMap.containsKey(word)) {
				//File found!
				for(Pair wordLoc : wordMap.get(word)) {
					foundFiles.add(wordLoc.getFileNum());
				}
			}
		}
		
		if(foundFiles.size() == 0) {
			// display no files found to the user in some way...
		} else {
			// display the files in the foundFiles array to the user in some way...
		}
	}
	
	public void andSearch(String userInput) {
		
		String[ ] words = userInput.split(" ");
		
		Set<Integer> foundFiles = new HashSet<>(Arrays.asList(1, 2, 3, 4));
		
		for(String word : words) {
			if (wordMap.containsKey(word)) {
				boolean found = false;
			
				for(int fileNum : foundFiles) {
					for(Pair wordLoc : wordMap.get(word)) {
						if(wordLoc.getFileNum() == fileNum) {
							found = true;
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
		
		if(foundFiles.size() == 0) {
			// display no files found to the user in some way...
		} else {
			// display the files in the foundFiles array to the user in some way...
		}
	}
	
	public void phraseSearch(String userInput) {
		
		String[ ] words = userInput.split(" ");
		
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
		
		if(newPossibleSets.size() == 0) {
			// display no files found to the user in some way...
		} else {
			// display the files in the foundFiles array to the user in some way...
		}
	}
}
 