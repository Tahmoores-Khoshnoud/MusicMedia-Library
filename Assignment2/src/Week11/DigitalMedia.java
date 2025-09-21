package Week11;

/**
 * Abstract class representing digital media, extending MusicMedia.
 * Implements the FileProcessor interface for save and delete operations.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public abstract class DigitalMedia extends MusicMedia implements FileProcessor 
{

    private String filePath;

    /**
     * Default constructor.
     */
    
    public DigitalMedia() 
    {
        super();
    }

    /**
     * Constructor to initialize DigitalMedia with common attributes.
     * 
     * @param sku    SKU of the media.
     * @param title  Title of the media.
     * @param artist Artist of the media.
     * @param year   Year of the media.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public DigitalMedia(final String sku, 
    		            final String title, 
    		            final String artist, 
    		            final int    year) 
    {
        super(sku, title, artist, year);
    }

    /**
     * Saves the file to a specified path.
     * 
     * @param filePath The path where the file will be saved.
     * @throws IllegalArgumentException if filePath is invalid.
     */
    
    @Override
    public void save(final String filePath) 
    {
        if (!validateString(filePath)) 
        {
            throw new IllegalArgumentException("Invalid FilePath Provided.");
        }
        
        this.filePath = filePath;
        System.out.println("Saving The File To " + filePath);
    }

    /**
     * Deletes the file from a specified path.
     * 
     * @param filePath The path from which the file will be deleted.
     * @throws IllegalArgumentException if filePath is invalid.
     */
    
    @Override
    public void delete(final String filePath) 
    {
        if (!validateString(filePath)) 
        {
            throw new IllegalArgumentException("Invalid FilePath Provided.");
        }
        System.out.println("Deleting The File From " + filePath);
    }

    @Override
    public String toString() {
        return "DigitalMedia [filePath=" + filePath + ", toString()=" + super.toString() + "]";
    }
}
