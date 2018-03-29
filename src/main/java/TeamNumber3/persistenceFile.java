// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.io.File;

class persistenceFile {
	public String filepath;
	public long dateModified;
	public int fileNumber;
	
	public persistenceFile() {
		
	}
	
	public String getFileStatus() {
		String success = "Indexed";
		String failure = "File changed";
		File discFile = new File(filepath);
		if(dateModified == discFile.lastModified())
			return success;
		
		else
			return failure;
	}
}
