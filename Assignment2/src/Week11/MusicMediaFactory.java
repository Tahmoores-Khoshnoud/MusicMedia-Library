package Week11;

/**
 * Factory class for creating instances of MusicMedia objects.
 * Dynamically creates objects based on the SKU prefix in the data.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class MusicMediaFactory {
	
	private static final int WEIGHT_GRAM = 100;
	private static final int PART_ZERO   = 0;
	private static final int PART_ONE    = 1;
	private static final int PART_TWO    = 2;
	private static final int PART_THREE  = 3;
	private static final int PART_FOUR   = 4;
	private static final int PART_FIVE   = 5;
	private static final int PART_SIX    = 6;
	private static final int ONE_HUNDRED = 100;

    /**
     * Creates a MusicMedia object based on the provided data.
     * 
     * @param data An array of strings representing the object's attributes.
     * @return A MusicMedia object.
     * @throws IllegalArgumentException if the data format is invalid.
     */
	
    public static MusicMedia create(final String[] data) 
    {
        String prefix = data[PART_ZERO].substring(PART_ZERO, PART_TWO).toLowerCase();;

        switch (prefix) 
        {
        
        case "vr":
            int sizeInInches = Integer.parseInt(data[PART_FIVE]);
            int weightInGrams = Integer.parseInt(data[PART_SIX]);

            if (sizeInInches == VinylRecord.DEFAULT_SIZE && weightInGrams != VinylRecord.DEFAULT_WEIGHT) {
                weightInGrams = VinylRecord.DEFAULT_WEIGHT;
            } else if (sizeInInches == VinylRecord.EP_SIZE && weightInGrams != WEIGHT_GRAM) {
                weightInGrams = ONE_HUNDRED;
            } else if (sizeInInches == VinylRecord.LP_SIZE &&
                       (weightInGrams < VinylRecord.MIN_LP_WEIGHT || weightInGrams > VinylRecord.MAX_LP_WEIGHT)) {
                weightInGrams = VinylRecord.MIN_LP_WEIGHT;
            }

            return new VinylRecord(
                data[PART_ZERO], data[PART_ONE], data[PART_TWO], Integer.parseInt(data[PART_THREE]),
                Integer.parseInt(data[PART_FOUR]), sizeInInches, weightInGrams
            );
            
            case "af":
                return new AudioFile(
                    data[PART_ZERO],
                    data[PART_ONE],
                    data[PART_TWO],
                    Integer.parseInt(data[PART_THREE]),
                    data[PART_FOUR],
                    Integer.parseInt(data[PART_FIVE]) 
                );
                
            case "cd":
                return new CompactDisc(
                    data[PART_ZERO],
                    data[PART_ONE],
                    data[PART_TWO],
                    Integer.parseInt(data[PART_THREE]),
                    Integer.parseInt(data[PART_FOUR])
                );
                
            default:
                throw new IllegalArgumentException("Invalid SKU Prefix: " + prefix);
        }
    }
}
