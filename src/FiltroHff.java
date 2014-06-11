

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltroHff extends FileFilter {

	@Override
	public boolean accept(File file) {
		 if (file.isDirectory()) {
		      return true;
		    } else {
		      String path = file.getAbsolutePath().toLowerCase();
		        String extension = "hff";
		        if ((path.endsWith(extension) && (path.charAt(path.length() 
		                  - extension.length() - 1)) == '.')) {
		          return true;
		        
		      }
		    }
		    return false;
	}

	@Override
	public String getDescription() {
		return "Archivos de codificación Huffman (.hff)";
	}

}
