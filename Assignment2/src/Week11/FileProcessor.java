package Week11;

/**
 * Interface defining file processing operations for media.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public interface FileProcessor 
{

    /**
     * Saves the file to a specified path.
     * 
     * @param filePath The path where the file will be saved.
     */
	
    void save(final String filePath);

    /**
     * Deletes the file from a specified path.
     * 
     * @param filePath The path from which the file will be deleted.
     */
    
    void delete(final String filePath);
}
