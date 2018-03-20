// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.io.File;

class persistenceFile {
	public String name;
	public String filepath;
	public long dateModified;
	public int fileNumber;
	
	public persistenceFile() {
		
	}
	
	public boolean isUpToDate() {
		File discFile = new File(filepath);
		if(dateModified >= discFile.lastModified())
			return true;
		
		else
			return false;
	}
}
