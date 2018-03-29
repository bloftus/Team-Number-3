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
