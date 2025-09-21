package Week11;

import java.util.HashMap;
import java.util.*;
import javax.swing.*;

/**
 * MusicLibrary class for managing a collection of MusicMedia items.
 * Provides sorting, updating, and file persistence functionality.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class MusicLibrary 
{
    public static final String SORT_BY_TYPE   = "Type";
    public static final String SORT_BY_ARTIST = "Artist";
    public static final String SORT_BY_TITLE  = "Title";
    public static final String SORT_BY_YEAR   = "Year";
    
    private HashMap<String, MusicMedia> library;

    {
        library = new HashMap<>();
    }

    /**
     * Default constructor for MusicLibrary.
     */
    
    public MusicLibrary() {}

    /**
     * Adds a MusicMedia item to the library.
     * 
     * @param selection The MusicMedia item to add.
     * @throws IllegalArgumentException if the selection is null.
     */
    
    public void addMusic(final MusicMedia selection) 
    {
        if (selection == null) {
            throw new IllegalArgumentException("MusicMedia Selection Cannot Be Null.");
        }
        
        library.put(selection.getSku(), selection);
    }

    /**
     * Checks if a given SKU exists in the library.
     * 
     * @param sku The SKU to check.
     * @return true if the SKU exists, false otherwise.
     */
    
    public boolean containsSku(final String sku) 
    {
        return library.containsKey(sku);
    }

    /**
     * Updates an existing entry in the library.
     * 
     * @param sku The SKU of the item to update.
     * @param updatedItem The updated MusicMedia object.
     */
    
    public void updateEntry(final String     sku,
    		                final MusicMedia updatedItem) 
    {
        if (!library.containsKey(sku)) 
        {
            throw new IllegalArgumentException("No Item Found With SKU: " + sku);
        }
        
        library.put(sku, updatedItem);
    }

    /**
     * Deletes an entry from the library.
     * 
     * @param sku The SKU of the item to delete.
     * @throws IllegalArgumentException if the SKU does not exist.
     */
    
    public void deleteEntry(final String sku) 
    {
        if (library.remove(sku) == null) 
        {
            throw new IllegalArgumentException("No Item Found With SKU: " + sku);
        }
    }

    /**
     * Sorts the library based on the specified criteria and displays the results.
     * 
     * @param criteria The sorting criteria (Type, Artist, Title, Year).
     */
    
    public void sortAndDisplay(final String criteria) 
    {
    	final List<MusicMedia> sortedList = new ArrayList<>(library.values());
    	
        switch (criteria) 
        {
            case SORT_BY_TYPE:
                sortedList.sort(Comparator.comparing(item -> item.getClass().getSimpleName()));
                break;
                
            case SORT_BY_ARTIST:
                sortedList.sort(Comparator.comparing(MusicMedia::getArtist));
                break;
                
            case SORT_BY_TITLE:
                sortedList.sort(Comparator.comparing(MusicMedia::getTitle));
                break;
                
            case SORT_BY_YEAR:
                sortedList.sort(Comparator.comparingInt(MusicMedia::getYear));
                break;
                
            default:
                throw new IllegalArgumentException("Invalid Sorting Criteria.");
        }
        
        displaySortedLibrary(sortedList, criteria);
    }

    /**
     * Displays the sorted library in a dialog.
     * 
     * @param sortedList The sorted list of MusicMedia items.
     * @param criteria   The sorting criteria.
     */
    
    private void displaySortedLibrary(final List<MusicMedia> sortedList, 
    		                          final String criteria) 
    {
    	final StringBuilder sb = new StringBuilder("Sorted By " + criteria + ":\n");
        for (final MusicMedia item : sortedList) 
        {
            sb.append(item).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, sb.toString(), "Sorted Library", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays all items in the library.
     */
    
    public void displayLibrary() 
    {
        if (library.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Library is empty.");
        } else 
        {
        	final StringBuilder sb = new StringBuilder();
            for (final MusicMedia item : library.values()) 
            {
                sb.append(item).append("\n");
            }
            
            JOptionPane.showMessageDialog(null, sb.toString(), "Library Contents", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Retrieves all MusicMedia items as a collection.
     * 
     * @return A collection of MusicMedia items.
     */
    
    public Collection<MusicMedia> getAll() 
    {
        return library.values();
    }
}
