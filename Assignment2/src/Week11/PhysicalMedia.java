package Week11;

/**
 * Abstract class representing physical media, extending MusicMedia.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public abstract class PhysicalMedia extends MusicMedia 
{

    /**
     * Default constructor.
     */
	
    public PhysicalMedia() 
    {
        super();
    }

    /**
     * Constructor to initialize PhysicalMedia with common attributes.
     * 
     * @param sku    SKU of the media.
     * @param title  Title of the media.
     * @param artist Artist of the media.
     * @param year   Year of the media.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    
    public PhysicalMedia(final String sku, 
    		             final String title, 
    		             final String artist, 
    		             final int year) 
    {
        super(sku, title, artist, year);
    }
}
