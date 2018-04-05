// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.io.File;

class persistenceFile {
	private String fileName;
	private String filepath;
	private long dateModified;
	private int fileNumber;
	public static int highestFileNum;
	
	public persistenceFile() {
		
	}
	public persistenceFile(String fileName, String filePath, long dateModified) {
		this.fileName = fileName;
		this.filepath = filePath;
		this.dateModified = dateModified;
		this.fileNumber = highestFileNum + 1;
		highestFileNum++;
	}

	public String getFileStatus() {
		String unmodified = "Indexed";
		String modified = "File changed";
		String missing = "File not found";
		File discFile = new File(filepath);
		if (discFile.exists()) {
			if (dateModified == discFile.lastModified())
				return unmodified;
			else
				return modified;
		} else
			return missing;
	}
}
