package Week11;

import java.util.Objects;

/**
 * Abstract class used to store common fields for music media subtypes.
 * Provides validation and shared functionality.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public abstract class MusicMedia {

    private String sku;
    private String title;
    private String artist;
    private int    year;
    
    public static final int CURRENT_YEAR = 2024;
    public static final int FIRST_YEAR   = 1860;

    /**
     * Default constructor.
     */
    
    public MusicMedia() {
        super();
    }

    /**
     * Constructor for setting basic attributes of a MusicMedia object.
     * 
     * @param sku    SKU of the media.
     * @param title  Title of the media.
     * @param artist Artist of the media.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public MusicMedia(final String sku, 
    		          final String title, 
    		          final String artist) 
    {
        this(sku, title, artist, CURRENT_YEAR);
    }

    /**
     * Constructor for setting all attributes of a MusicMedia object.
     * 
     * @param sku    SKU of the media.
     * @param title  Title of the media.
     * @param artist Artist of the media.
     * @param year   Year of the media.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public MusicMedia(final String sku, 
    		          final String title, 
    		          final String artist, 
    		          final int    year) 
    {
        if (!validateString(sku) || !validateString(title) || !validateString(artist)) 
        {
            throw new IllegalArgumentException("Invalid String Parameters.");
        }
        
        if (!validateYear(year)) 
        {
            throw new IllegalArgumentException("Year Must Be Between " + FIRST_YEAR + " And " + CURRENT_YEAR + ".");
        }
        
        this.sku    = sku;
        this.title  = title;
        this.artist = artist;
        this.year   = year;
    }

    /**
     * Validates a string to ensure it is not null or empty.
     * 
     * @param value String to validate.
     * @return true if valid, false otherwise.
     */
    
    protected boolean validateString(final String value) 
    {
        return value != null && !value.isBlank();
    }

    /**
     * Validates a year to ensure it falls within the allowed range.
     * 
     * @param  year Year to validate.
     * @return true if valid, false otherwise.
     */
    
    private boolean validateYear(final int year) 
    {
        return year >= FIRST_YEAR && year <= CURRENT_YEAR;
    }

    /**
     * Gets the SKU of the media.
     * 
     * @return The SKU as a string.
     */
    
    public String getSku() 
    {
        return sku;
    }

    /**
     * Gets the title of the media.
     * 
     * @return The title as a string.
     */
    
    public String getTitle() 
    {
        return title;
    }

    /**
     * Sets the title of the media.
     * 
     * @param title The title to set.
     * @throws IllegalArgumentException if the title is invalid.
     */

    
    public void setTitle(final String title) 
    {
        if (!validateString(title)) {
            throw new IllegalArgumentException("Invalid title.");
        }
        
        this.title = title;
    }

    /**
     * Gets the artist of the media.
     * 
     * @return The artist as a string.
     */
    
    public String getArtist() 
    {
        return artist;
    }

    /**
     * Sets the artist of the media.
     * 
     * @param artist The artist to set.
     * @throws IllegalArgumentException if the artist is invalid.
     */
    
    public void setArtist(final String artist) 
    {
        if (!validateString(artist)) 
        {
            throw new IllegalArgumentException("Invalid Artist.");
        }
        
        this.artist = artist;
    }

    /**
     * Gets the release year of the media.
     * 
     * @return The release year as an integer.
     */
    
    public int getYear() 
    {
        return year;
    }

    /**
     * Sets the release year of the media.
     * 
     * @param year The release year to set.
     * @throws IllegalArgumentException if the year is invalid.
     */
    
    public void setYear(final int year) 
    {
        if (!validateYear(year)) 
        {
            throw new IllegalArgumentException("Invalid Year.");
        }
        
        this.year = year;
    }

    /**
     * Abstract method to be implemented by subclasses for playing the media.
     */
    
    public abstract void play();

    @Override
    public String toString() 
    {
        return "MusicMedia [sku=" + sku + ", title=" + title + ", artist=" + artist + ", year=" + year + "]";
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(artist, sku, title, year);
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        MusicMedia other = (MusicMedia) obj;
        return Objects.equals(artist, other.artist) && Objects.equals(sku, other.sku)
                && Objects.equals(title, other.title) && year == other.year;
    }
}
