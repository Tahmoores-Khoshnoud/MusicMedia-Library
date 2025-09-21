package Week11;

import java.util.Objects;

/**
 * Represents a vinyl record as a type of PhysicalMedia.
 * Includes additional fields for size, weight, and the number of tracks.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class VinylRecord extends PhysicalMedia 
{

    private int numberOfTracks;
    private int sizeInInches;
    private int weightInGrams;

    public static final int DEFAULT_TRACKS = 1;
    public static final int DEFAULT_SIZE   = 7;
    public static final int DEFAULT_WEIGHT = 40;
    public static final int EP_SIZE        = 10;
    public static final int LP_SIZE        = 12;
    public static final int MIN_LP_WEIGHT  = 140;
    public static final int MAX_LP_WEIGHT  = 200;

    /**
     * Default constructor.
     */
    
    public VinylRecord() {
        super();
    }

    /**
     * Constructor to initialize all attributes of a VinylRecord.
     * 
     * @param sku           The unique SKU identifier for the media.
     * @param title         The title of the media.
     * @param artist        The artist of the media.
     * @param year          The release year of the media.
     * @param numberOfTracks The number of tracks on the vinyl record.
     * @param sizeInInches  The size of the vinyl record in inches.
     * @param weightInGrams The weight of the vinyl record in grams.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    
    public VinylRecord(final String sku, 
    		           final String title, 
    		           final String artist, 
    		           final int    year, 
    		           final int    numberOfTracks, 
    		           final int    sizeInInches,
    		           final int    weightInGrams) 
    {
    	
        super(sku, title, artist, year);
        setNumberOfTracks(numberOfTracks);
        setSizeInInches(sizeInInches);
        setWeightInGrams(weightInGrams);
    }

    /**
     * Gets the number of tracks on the vinyl record.
     * 
     * @return The number of tracks.
     */
    
    public int getNumberOfTracks() 
    {
        return numberOfTracks;
    }

    /**
     * Sets the number of tracks on the vinyl record.
     * 
     * @param numberOfTracks The number of tracks to set.
     * @throws IllegalArgumentException if the number of tracks is invalid.
     */
    
    public void setNumberOfTracks(final int numberOfTracks) 
    {
        if (numberOfTracks < DEFAULT_TRACKS) 
        {
            throw new IllegalArgumentException("Invalid number of tracks: must be at least " + DEFAULT_TRACKS + ".");
        }
        
        this.numberOfTracks = numberOfTracks;
    }

    /**
     * Gets the size of the vinyl record in inches.
     * 
     * @return The size in inches.
     */
    
    public int getSizeInInches() 
    {
        return sizeInInches;
    }

    /**
     * Sets the size of the vinyl record in inches.
     * 
     * @param sizeInInches The size to set.
     * @throws IllegalArgumentException if the size is invalid.
     */
    
    public void setSizeInInches(final int sizeInInches) 
    {
        if (sizeInInches != DEFAULT_SIZE && sizeInInches != EP_SIZE && sizeInInches != LP_SIZE) 
        {
            throw new IllegalArgumentException("Invalid Size In Inches: Must Be 7, 10, or 12.");
        }
        
        this.sizeInInches = sizeInInches;
    }

    /**
     * Gets the weight of the vinyl record in grams.
     * 
     * @return The weight in grams.
     */
    
    public int getWeightInGrams() 
    {
        return weightInGrams;
    }

    /**
     * Sets the weight of the vinyl record in grams.
     * 
     * @param weightInGrams The weight to set.
     * @throws IllegalArgumentException if the weight is invalid based on size.
     */
    
    public void setWeightInGrams(final int weightInGrams) 
    {
        if (sizeInInches == DEFAULT_SIZE && weightInGrams == DEFAULT_WEIGHT) 
        {
            this.weightInGrams = weightInGrams;
        } else if (sizeInInches == EP_SIZE && weightInGrams == 100) 
        {
            this.weightInGrams = weightInGrams;
        } else if (sizeInInches == LP_SIZE && weightInGrams >= MIN_LP_WEIGHT && weightInGrams <= MAX_LP_WEIGHT) 
        {
            this.weightInGrams = weightInGrams;
        } else 
        {
            throw new IllegalArgumentException("Invalid weight for size " + sizeInInches + " inches.");
        }
    }

    /**
     * Plays the vinyl record by displaying a message.
     */
    
    @Override
    public void play() {
        System.out.println("Playing vinyl record: " + getTitle());
    }

    /**
     * Hash code based on all fields.
     * 
     * @return The hash code.
     */
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfTracks, sizeInInches, weightInGrams);
    }

    /**
     * Equality check for VinylRecord objects based on all fields.
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
        
        VinylRecord other = (VinylRecord) obj;
        return numberOfTracks == other.numberOfTracks && sizeInInches == other.sizeInInches
                && weightInGrams == other.weightInGrams;
    }

    @Override
    public String toString() 
    {
        return "VinylRecord [numberOfTracks=" + numberOfTracks + ", sizeInInches=" + sizeInInches + ", weightInGrams="
                + weightInGrams + ", toString()=" + super.toString() + "]";
    }
}
