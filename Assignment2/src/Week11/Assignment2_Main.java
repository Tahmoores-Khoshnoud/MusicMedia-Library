package Week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * Main application class for managing the Music Library.
 * Provides GUI, sorting, and file I/O functionality.
 * 
 * @author Seyed Tahmoore Khoshnoud Moghadam
 */

public class Assignment2_Main extends JFrame 
{
	
	private static final String DATA_FILE = "music_data.txt";
    private MusicLibrary        library;
    
    private  static final int   WEIGHT_GRAM       = 100;
    private  static final int   SET_SIZE_1        = 500;
    private  static final int   SET_SIZE_2        = 400;
    private  static final int   SET_FONT          = 16;
    private  static final int   GRID_LAYOUT_1     = 6;
    private  static final int   GRID_LAYOUT_2     = 1;
    private  static final int   EXIT_NUMBER       = 0;
    private  static final int   OPTION_NUMBER     = 0;
    private  static final int   PART_ZERO         = 0;
    private  static final int   PART_FIVE         = 5;
    private  static final int   PART_SIX          = 6;
    private  static final long  serialVersionUID  = 1L;
    private  static final int   FIRST_YEAR        = 1800;
    private  static final int   LAST_YEAR         = 2024;
    private  static final int   TYPE_NUM          = 0;
    private  static final int   MIN_RES           = 50;
    private  static final int   MAX_RES           = 500;
    private  static final int   MIN_TRA           = 1;
    private  static final int   MAX_TRA           = 300;
    private  static final int   MIN_INCH          = 1;
    private  static final int   MAX_ICH           = 30;
    private  static final int   MIN_GRA           = 1;
    private  static final int   MAX_GRA           = 500;
    private  static final int   VIN_NUM           = 1;

    /**
     * Constructs the main application frame and initializes the music library.
     */
    
    public Assignment2_Main() 
    {
        library = new MusicLibrary();
        loadLibrary();

        setTitle("Music Library Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SET_SIZE_1, SET_SIZE_2);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Music Library Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, SET_FONT));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(GRID_LAYOUT_1, GRID_LAYOUT_2));
        addButton(buttonPanel, "Display Library", e -> library.displayLibrary());
        addButton(buttonPanel, "Sort Library", e -> showSortOptions());
        addButton(buttonPanel, "Update Library", e -> showUpdateOptions());
        addButton(buttonPanel, "Save Library", e -> saveLibrary());
        addButton(buttonPanel, "Help", e -> showHelp());
        addButton(buttonPanel, "Exit", e -> System.exit(EXIT_NUMBER));
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Adds a button to the specified panel.
     * 
     * @param panel  The panel to add the button to.
     * @param text   The text of the button.
     * @param action The action listener triggered when the button is clicked.
     */
    
    private void addButton(JPanel panel, String text, ActionListener action) 
    {
        JButton button = new JButton(text);
        button.addActionListener(action);
        panel.add(button);
    }

    /**
     * Displays a dialog for selecting sorting criteria and sorts the library accordingly.
     */
    
    private void showSortOptions() 
    {
        String[] options = { MusicLibrary.SORT_BY_TYPE, MusicLibrary.SORT_BY_ARTIST,
                MusicLibrary.SORT_BY_TITLE, MusicLibrary.SORT_BY_YEAR };
        String choice = (String) JOptionPane.showInputDialog(this, "Select sorting criteria:",
                "Sort Library", JOptionPane.QUESTION_MESSAGE, null, options, options[OPTION_NUMBER]);
        if (choice != null) 
        {
            library.sortAndDisplay(choice);
        }
    }

    /**
     * Displays a dialog for selecting an update operation (add, update, or delete)
     * and performs the selected operation.
     */
    
    private void showUpdateOptions() 
    {
        String[] options = { "Add Entry", "Update Entry", "Delete Entry" };
        String choice = (String) JOptionPane.showInputDialog(this, "Select update operation:",
                "Update Library", JOptionPane.QUESTION_MESSAGE, null, options, options[OPTION_NUMBER]);
        if (choice != null) 
        {
            switch (choice) 
            {
                case "Add Entry":
                    addEntry();
                    break;
                    
                case "Update Entry":
                    updateEntry();
                    break;
                    
                case "Delete Entry":
                    deleteEntry();
                    break;
            }
        }
    }
    
    private void showHelp() 
    {
        JOptionPane.showMessageDialog(this, 
            "Name: Seyed Tahmoores Khoshnoud Moghadam\nID: A01394291", 
            "Help", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Adds a new music entry to the library by prompting the user for details.
     */
    
    private void addEntry() 
    {
        try {

            String sku = JOptionPane.showInputDialog(this, "Enter SKU:");
            if (sku == null || sku.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "SKU cannot be empty. Please enter a valid SKU.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!sku.matches("(vr|af|cd)-[a-zA-Z0-9]{1,30}")) 
            {
                JOptionPane.showMessageDialog(this, "Invalid SKU. It must start with 'vr', 'af', or 'cd', followed by a dash and up to 30 alphanumeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String title = JOptionPane.showInputDialog(this, "Enter Title:");
            if (title == null || title.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Title cannot be empty. Please enter a valid title.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String artist = JOptionPane.showInputDialog(this, "Enter Artist:");
            if (artist == null || artist.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Artist cannot be empty. Please enter a valid artist.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String yearStr = JOptionPane.showInputDialog(this, "Enter Year:");
            if (yearStr == null || yearStr.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Year cannot be empty. Please enter a valid year.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            int year;
            try 
            {
                year = Integer.parseInt(yearStr);
                if (year < FIRST_YEAR || year > LAST_YEAR) 
                {
                    throw new IllegalArgumentException("Year must be between 1800 and 2024.");
                }
            } catch (NumberFormatException e) 
            {
                JOptionPane.showMessageDialog(this, "Year must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (IllegalArgumentException e) 
            {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] types = { "AudioFile", "CompactDisc", "VinylRecord" };
            String type = (String) JOptionPane.showInputDialog(this, "Select Type:", "Media Type",
                    JOptionPane.QUESTION_MESSAGE, null, types, types[TYPE_NUM]);
            
            if (type == null) 
            {
                JOptionPane.showMessageDialog(this, "Media type selection is required. Entry creation canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            MusicMedia media;

            switch (type) 
            {
            
                case "AudioFile":
                    String fileName = JOptionPane.showInputDialog(this, "Enter File Name:");
                    if (fileName == null || fileName.isBlank()) 
                    {
                        JOptionPane.showMessageDialog(this, "File name cannot be empty. Please enter a valid file name.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    String resolutionStr = JOptionPane.showInputDialog(this, "Enter Resolution:");
                    if (resolutionStr == null || resolutionStr.isBlank()) 
                    {
                        JOptionPane.showMessageDialog(this, "Resolution cannot be empty. Please enter a valid resolution.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    int resolution;
                    try {
                        resolution = Integer.parseInt(resolutionStr);
                        if (resolution < MIN_RES || resolution > MAX_RES) 
                        {
                            throw new IllegalArgumentException("Resolution must be between 50 and 500.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Resolution must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException e) 
                    {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    media = new AudioFile(sku, title, artist, year, fileName, resolution);
                    break;

                case "CompactDisc":
                	
                    String tracksStr = JOptionPane.showInputDialog(this, "Enter Number of Tracks:");
                    if (tracksStr == null || tracksStr.isBlank()) 
                    {
                        JOptionPane.showMessageDialog(this, "Number of tracks cannot be empty. Please enter a valid number.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    int tracks;
                    try {
                        tracks = Integer.parseInt(tracksStr);
                        if (tracks < MIN_TRA || tracks > MAX_TRA) 
                        {
                            throw new IllegalArgumentException("Number of tracks must be between 1 and 300.");
                        }
                    } catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(this, "Number of tracks must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException e) 
                    {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    media = new CompactDisc(sku, title, artist, year, tracks);
                    break;

                case "VinylRecord":
                    String sizeStr = JOptionPane.showInputDialog(this, "Enter Size in Inches:");
                    if (sizeStr == null || sizeStr.isBlank()) 
                    {
                        JOptionPane.showMessageDialog(this, "Size in inches cannot be empty. Please enter a valid size.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    int size;
                    try {
                        size = Integer.parseInt(sizeStr);
                        if (size < MIN_INCH || size > MAX_ICH) 
                        {
                            throw new IllegalArgumentException("Size in inches must be between 1 and 30.");
                        }
                    } catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(this, "Size in inches must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String weightStr = JOptionPane.showInputDialog(this, "Enter Weight in Grams:");
                    if (weightStr == null || weightStr.isBlank()) 
                    {
                        JOptionPane.showMessageDialog(this, "Weight in grams cannot be empty. Please enter a valid weight.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    int weight;
                    try 
                    {
                        weight = Integer.parseInt(weightStr);
                        if (weight < MIN_GRA || weight > MAX_GRA) 
                        {
                            throw new IllegalArgumentException("Weight in grams must be between 1 and 500.");
                        }
                    } catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(this, "Weight in grams must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException e) 
                    {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    media = new VinylRecord(sku, title, artist, year, VIN_NUM, size, weight);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Invalid media type selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            library.addMusic(media);
            JOptionPane.showMessageDialog(this, "Entry added successfully.");
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Error adding entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates an existing music entry in the library by prompting the user for new details.
     */
    
    private void updateEntry() 
    {
        try {
            String sku = JOptionPane.showInputDialog(this, "Enter SKU to Update:");
            if (sku == null || sku.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "SKU Cannot Be Empty. Please Enter a Valid SKU.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!library.containsSku(sku)) 
            {
                JOptionPane.showMessageDialog(this, "No entry found with SKU: " + sku, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MusicMedia existing = library.getAll().stream()
                    .filter(media -> media.getSku().equals(sku))
                    .findFirst()
                    .orElse(null);

            String title = JOptionPane.showInputDialog(this, "Enter New Title:", existing.getTitle());
            if (title == null || title.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Title Cannot be Empty. Please Enter a Valid Title.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String artist = JOptionPane.showInputDialog(this, "Enter New Artist:", existing.getArtist());
            if (artist == null || artist.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Artist Cannot Be Empty. Please Enter a Valid Artist.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String yearStr = JOptionPane.showInputDialog(this, "Enter New Year:", existing.getYear());
            if (yearStr == null || yearStr.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "Year Cannot Be Empty. Please Enter a Valid Year.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int year = Integer.parseInt(yearStr);

            existing.setTitle(title);
            existing.setArtist(artist);
            existing.setYear(year);

            JOptionPane.showMessageDialog(this, "Entry updated successfully.");
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Error updating entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Deletes a music entry from the library by prompting the user for the SKU.
     */
    
    private void deleteEntry() 
    {
        try 
        {
            String sku = JOptionPane.showInputDialog(this, "Enter SKU to Delete:");
            if (sku == null || sku.isBlank()) 
            {
                JOptionPane.showMessageDialog(this, "SKU Cannot Be Empty. Please Enter a Valid SKU.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!library.containsSku(sku)) 
            {
                JOptionPane.showMessageDialog(this, "No Entry Found With SKU: " + sku, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            library.deleteEntry(sku);
            JOptionPane.showMessageDialog(this, "Entry Deleted Successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Deleting Entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads the music library from the data file.
     * Displays an error message if the file is missing or invalid.
     */
    
    private void loadLibrary() 
    {
        try (Scanner scanner = new Scanner(new File(DATA_FILE))) 
        {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                try {
                    if (parts[PART_ZERO].startsWith("vr")) 
                    {
                        int sizeInInches = Integer.parseInt(parts[PART_FIVE]);
                        int weightInGrams = Integer.parseInt(parts[PART_SIX]);

                        if (sizeInInches == VinylRecord.DEFAULT_SIZE) 
                        {
                            if (weightInGrams != VinylRecord.DEFAULT_WEIGHT) 
                            {
                                parts[PART_SIX] = String.valueOf(VinylRecord.DEFAULT_WEIGHT);
                            }
                        } else if (sizeInInches == VinylRecord.EP_SIZE) 
                        {
                            if (weightInGrams != WEIGHT_GRAM) 
                            {
                                parts[PART_SIX] = "100"; 
                            }
                        } else if (sizeInInches == VinylRecord.LP_SIZE) 
                        {
                            if (weightInGrams < VinylRecord.MIN_LP_WEIGHT || weightInGrams > VinylRecord.MAX_LP_WEIGHT) 
                            {
                                parts[PART_SIX] = String.valueOf(VinylRecord.MIN_LP_WEIGHT);
                            }
                        } else 
                        {
                            parts[PART_FIVE] = String.valueOf(VinylRecord.DEFAULT_SIZE);
                            parts[PART_SIX] = String.valueOf(VinylRecord.DEFAULT_WEIGHT);
                        }
                    }

                    if (parts[PART_ZERO].startsWith("af")) 
                    {
                        int resolution = Integer.parseInt(parts[PART_FIVE]);
                        if (resolution < AudioFile.MIN_RESOLUTION || resolution > AudioFile.MAX_RESOLUTION) {
                            parts[PART_FIVE] = String.valueOf(AudioFile.DEFAULT_RESOLUTION);
                        }
                    }

                    library.addMusic(MusicMediaFactory.create(parts));
                } catch (IllegalArgumentException e) 
                {
                    JOptionPane.showMessageDialog(this,
                        "Error loading entry: " + e.getMessage() + "\nEntry: " + line,
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(this,
                "Error loading library: File not found (" + DATA_FILE + ").",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading library: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Saves the current state of the music library to the data file.
     */
    
    private void saveLibrary() 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) 
        {
            for (final MusicMedia item : library.getAll()) 
            {
                writer.println(item.toString());
            }
            JOptionPane.showMessageDialog(this, "Library Saved Successfully.");
        } catch (IOException e) 
        {
            JOptionPane.showMessageDialog(this, "Error Saving Library: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to launch the application.
     * 
     * @param args Command-line arguments.
     */
    
    public static void main(final String[] args) 
    {
        SwingUtilities.invokeLater(Assignment2_Main::new);
    }
}
