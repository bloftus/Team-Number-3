// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.util.ArrayList;
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
}
