package Week11;

import java.util.Objects;

/**
 * Represents an audio file as a type of DigitalMedia.
 * Includes additional fields for file name and resolution.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class AudioFile extends DigitalMedia 
{

    private String fileName;
    private int    fileResolution;

    public static final int DEFAULT_RESOLUTION = 192;
    public static final int MIN_RESOLUTION     = 32;
    public static final int MAX_RESOLUTION     = 320;

    /**
     * Default constructor.
     */
    
    public AudioFile() {
        super();
    }

    /**
     * Constructor to initialize all attributes of an AudioFile.
     * 
     * @param sku           SKU of the media.
     * @param title         Title of the media.
     * @param artist        Artist of the media.
     * @param year          Year of the media.
     * @param fileName      Name of the file.
     * @param fileResolution Resolution of the file.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public AudioFile(final String sku, 
    		         final String title, 
    		         final String artist, 
    		         final int    year, 
    		         final String fileName, 
    		         final int    fileResolution) 
    {
        super(sku, title, artist, year);
        
        setFileName(fileName);
        setFileResolution(fileResolution);
    }

    /**
     * Constructor with default resolution.
     * 
     * @param sku       SKU of the media.
     * @param title     Title of the media.
     * @param artist    Artist of the media.
     * @param year      Year of the media.
     * @param fileName  Name of the file.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public AudioFile(final String sku, 
    		         final String title, 
    		         final String artist, 
    		         final int    year, 
    		         final String fileName) 
    {
        this(sku, title, artist, year, fileName, DEFAULT_RESOLUTION);
    }

    /**
     * Gets the file name.
     * 
     * @return the fileName.
     */
    
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     * 
     * @param fileName the fileName to set.
     * @throws IllegalArgumentException if fileName is null or empty.
     */
    
    public void setFileName(final String fileName) 
    {
        if (!validateString(fileName)) 
        {
            throw new IllegalArgumentException("Invalid FileName: FileName Cannot Be Null Or Empty.");
        }
        this.fileName = fileName;
    }

    /**
     * Gets the file resolution.
     * 
     * @return the fileResolution.
     */
    
    public int getFileResolution() 
    {
        return fileResolution;
    }

    /**
     * Sets the file resolution.
     * 
     * @param fileResolution the fileResolution to set.
     * @throws IllegalArgumentException if fileResolution is out of valid range.
     */
    
    public void setFileResolution(final int fileResolution) 
    {
        if (fileResolution < MIN_RESOLUTION || fileResolution > MAX_RESOLUTION) 
        {
            throw new IllegalArgumentException(
                    "Invalid fileResolution: must be between " + MIN_RESOLUTION + " and " + MAX_RESOLUTION + ".");
        }
        
        this.fileResolution = fileResolution;
    }

    /**
     * Plays the audio file by displaying a message.
     */
    
    @Override
    public void play() 
    {
        System.out.println("Playing audio file: " + fileName);
    }

    /**
     * Deletes the file from a specified path.
     * 
     * @param path The path where the file will be deleted.
     * @throws IllegalArgumentException if the path is invalid.
     */
    
    @Override
    public void delete(String path) 
    {
        if (!validateString(path)) 
        {
            throw new IllegalArgumentException("Invalid path: path cannot be null or empty.");
        }
        
        System.out.println("Deleting audio file: \"" + fileName + "\" from path: \"" + path + "\"");
    }

    /**
     * Hash code based on all fields.
     * 
     * @return the hash code.
     */
    
    @Override
    public int hashCode() 
    {
        return Objects.hash(super.hashCode(), fileName, fileResolution);
    }

    /**
     * Equality check for AudioFile objects based on all fields.
     * 
     * @param obj The object to compare.
     * @return true if objects are equal, false otherwise.
     */
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        
        if (!super.equals(obj)) 
        {
            return false;
        }
        
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        
        AudioFile other = (AudioFile) obj;
        return Objects.equals(fileName, other.fileName) && fileResolution == other.fileResolution;
    }

    @Override
    public String toString() 
    {
        return "AudioFile [fileName=" + fileName + ", fileResolution=" + fileResolution + ", toString()="
                + super.toString() + "]";
    }
}
