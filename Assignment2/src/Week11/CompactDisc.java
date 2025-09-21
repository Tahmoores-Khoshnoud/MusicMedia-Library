package Week11;

import java.util.Objects;

/**
 * Represents a compact disc as a type of PhysicalMedia.
 * Includes additional fields for the number of tracks.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class CompactDisc extends PhysicalMedia 
{

    private int numberOfTracks;
    
    public static final int DEFAULT_TRACKS = 1;
    public static final int MAX_TRACKS     = 100;

    /**
     * Default constructor.
     */
    
    public CompactDisc() {
        super();
    }

    /**
     * Constructor to initialize all attributes of a CompactDisc.
     * 
     * @param sku           The unique SKU identifier for the media.
     * @param title         The title of the media.
     * @param artist        The artist of the media.
     * @param year          The release year of the media.
     * @param numberOfTracks The number of tracks on the compact disc.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    
    public CompactDisc(final String sku, 
    		           final String title, 
    		           final String artist, 
    		           final int    year, 
    		           final int    numberOfTracks) 
    {
        super(sku, title, artist, year);
        setNumberOfTracks(numberOfTracks);
    }

    /**
     * Gets the number of tracks on the compact disc.
     * 
     * @return The number of tracks.
     */
    
    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    /**
     * Sets the number of tracks on the compact disc.
     * 
     * @param numberOfTracks The number of tracks to set.
     * @throws IllegalArgumentException if the number of tracks is invalid.
     */
    
    public void setNumberOfTracks(final int numberOfTracks) 
    {
        if (numberOfTracks < DEFAULT_TRACKS || numberOfTracks > MAX_TRACKS) 
        {
            throw new IllegalArgumentException("Invalid Number Of Tracks: Must Be Between " + DEFAULT_TRACKS +
                    " and " + MAX_TRACKS + ".");
        }
        
        this.numberOfTracks = numberOfTracks;
    }

    /**
     * Plays the compact disc by displaying a message.
     */
    
    @Override
    public void play() 
    {
        System.out.println("Playing Compact Disc: " + getTitle());
    }

    /**
     * Hash code based on all fields.
     * 
     * @return The hash code.
     */
    
    @Override
    public int hashCode() 
    {
        return Objects.hash(super.hashCode(), numberOfTracks);
    }

    /**
     * Equality check for CompactDisc objects based on all fields.
     * 
     * @param obj The object to compare.
     * @return true if objects are equal, false otherwise.
     */
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
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
        CompactDisc other = (CompactDisc) obj;
        return numberOfTracks == other.numberOfTracks;
    }

    @Override
    public String toString() 
    {
        return "CompactDisc [numberOfTracks=" + numberOfTracks + ", toString()=" + super.toString() + "]";
    }
}
