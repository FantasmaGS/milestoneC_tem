import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class animeList extends JFrame implements ActionListener, ListSelectionListener {
    // Components
    private JButton btnBack, btnProfile, btnSort, btnNew, btnDelete, btnHelp, btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel myAnime, nack;
    private JTextField tfSearch;
    final JPopupMenu menu;
    private JMenuItem name;
    private JMenuItem date;
    private JMenuItem rating;

    public static void main(String[] args) {
        new animeList();
    }

    public animeList() {
        model = new DefaultTableModel(new String[]{"Anime", "Date", "Rating"}, 0);

        String[] animes = {"Naruto Shipudden", "One Piece", "Bleach", "Fairy Tail", "Neon Genesis Evangelion", "My Hero Academia", "Classroom of the Elite", "Sword Art Online", "Attack on Titans", "Code Geass", "Hunter x Hunter", "Spriggan", "Cowboy Bebop", "Ocean Waves", "The Wind Rises"};
        int[] dates = {2007, 1997, 2001, 2006, 1995, 2014, 2017, 2014, 2013, 2006, 2011, 2022, 1998, 1993, 2013};
        double[] ratingList = {8.5, 8.8, 8.6, 7.77, 8.4, 8.2, 7.78, 7.35, 8.8, 8.8, 9, 6.8, 8.8, 6.5, 7.9};
        for (int i = 0; i < animes.length; i++) {
            model.addRow(new Object[]{animes[i], dates[i], ratingList[i]});
        }

        // Button initialization
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);

        btnProfile = new JButton("Profile");
        btnProfile.addActionListener(this);

        btnSort = new JButton("Sort");
        btnSort.addActionListener(this);

        btnNew = new JButton("New");
        btnNew.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);

        btnHelp = new JButton("Help");
        btnHelp.addActionListener(this);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);

        // Search text field
        tfSearch = new JTextField();
        tfSearch.setBounds(160, 35, 310, 25);
        add(tfSearch);

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this);

        // Labels initialization
        myAnime = new JLabel("Anime List");
        myAnime.setForeground(Color.WHITE);
        myAnime.setFont(new Font("Courier", Font.BOLD, 25));
        ImageIcon img = new ImageIcon("backgroundAnimeList.jpg");
        nack = new JLabel("", img, JLabel.CENTER);

        JScrollPane listScroller = new JScrollPane(table);
        listScroller.setPreferredSize(new Dimension(400, 200));
        listScroller.setBounds(160, 70, 400, 220);
        listScroller.setOpaque(false);
        listScroller.getViewport().setOpaque(false);

        menu = new JPopupMenu("Menu");
        name = new JMenuItem("Name");
        name.addActionListener(this);
        rating = new JMenuItem("Rating");
        rating.addActionListener(this);
        date = new JMenuItem("Date");
        date.addActionListener(this);

        // Setting bounds
        myAnime.setBounds(270, 0, 210, 30);
        btnBack.setBounds(8, 300, 90, 50);
        btnProfile.setBounds(128, 300, 90, 50);
        btnSort.setBounds(248, 300, 90, 50);
        btnNew.setBounds(368, 300, 90, 50);
        btnDelete.setBounds(488, 300, 90, 50);
        btnHelp.setBounds(608, 300, 90, 50);
        nack.setBounds(0, 0, 720, 405);
        btnSearch.setBounds(470, 35, 90, 25);

        // Adding components to JFrame container
        add(myAnime);
        add(btnBack);
        add(btnProfile);
        add(btnSort);
        add(btnNew);
        add(btnDelete);
        add(btnHelp);
        add(listScroller);
        add(btnSearch);
        add(nack);
        menu.add(name);
        menu.add(rating);
        menu.add(date);

        // Setting JFrame's title, window size and making it visible
        setTitle("Anime List");
        setSize(720, 405);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Action listener for buttons
    public void actionPerformed(ActionEvent e) {
        menu.show(btnSort, btnSort.getWidth() / 2, btnSort.getHeight() / 2);
        Object source = e.getSource();

        if (source == btnBack) {
            System.out.println("Back button clicked");
            this.dispose();
            Menu app = new Menu();
        } else if (source == btnProfile) {
            System.out.println("Profile button clicked");
        } else if (source == btnSort) {
            System.out.println("Sort button clicked");
        } else if (source == btnNew) {
            System.out.println("New button clicked");
            // Prompt the user to enter new anime details
            String anime = JOptionPane.showInputDialog(this, "Enter Anime Name:");
            String ratingString = JOptionPane.showInputDialog(this, "Enter Rating:");
            String date = JOptionPane.showInputDialog(this, "Enter Date:");

            // Validate user input
            if (anime != null && ratingString != null && date != null) {
                try {
                    int rating = Integer.parseInt(ratingString);
                    // Add the new row to the table
                    model.addRow(new Object[]{anime, date, rating});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid rating! Please enter a numeric value.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid values.");
            }
        } else if (source == btnDelete) {
            System.out.println("Delete button clicked");
            // Check if any row is selected
            int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
            if (selectedRow != -1) {
                // Delete the selected row
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "No row selected! Please select a row to delete.");
            }
        } else if (source == btnHelp) {
            System.out.println("Help button clicked");
        } else if (source == name) {
            table.getRowSorter().toggleSortOrder(0);
        } else if (source == date) {
            table.getRowSorter().toggleSortOrder(1);
        } else if (source == rating) {
            table.getRowSorter().toggleSortOrder(2);
        } else if (source == btnSearch) {
            System.out.println("Search button clicked");
            String searchTerm = tfSearch.getText().toLowerCase();
            if (!searchTerm.isEmpty()) {
                TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();
                RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchTerm);
                sorter.setRowFilter(rowFilter);
            } else {
                // If the search term is empty, remove the row filter
                TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();
                sorter.setRowFilter(null);
            }
        }
    }

    // List selection listener for table row selection
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String animeName = (String) table.getValueAt(selectedRow, 0);
                System.out.println("Selected Anime: " + animeName);
                // Open new GUI or perform any action with the selected anime
                /*
                if (animeName == "Naruto Shipudden") {
                  narutoShipuddenGUI app = new narutoShipuddenGUI();
                } else if (animeName == "One Piece") {
                  onePieceGUI app = new onePieceGUI();
                } else if (animeName == "Bleach") {
                  bleachGUI app = new bleachGUI();
                } else if (animeName == "Fairy Tail") {
                  fairyTailGUI app = new fairyTailGUI();
                } else if (animeName == "Neon Genesis Evangelion") {
                  neonGenesisEvangelionGUI app = new neonGenesisEvangelionGUI();
                } else if (animeName == "My Hero Academia") {
                  myHeroAcademiaGUI app = new myHeroAcademiaGUI();
                } else if (animeName == "Classroom of the Elite") {
                  classroomOfTheEliteGUI app = new classroomOfTheEliteGUI();
                } else if (animeName == "Sword Art Online") {
                  swordArtOnlineGUI app = new swordArtOnlineGUI();
                } else if (animeName == "Attack on Titans") {
                  attackOnTitansGUI app = new attackOnTitansGUI();
                } else if (animeName == "Code Geass") {
                  codeGeassGUI app = new codeGeassGUI();
                } else if (animeName == "Hunter x Hunter") {
                  hunterXhunterGUI app = new hunterXhunterGUI();
                } else if (animeName == "Spriggan") {
                  sprigganGUI app = new sprigganGUI();
                } else if (animeName == "Cowboy Bebop") {
                  cowboyBebopGUI app = new cowboyBebopGUI();
                } else if (animeName == "Ocean Waves") {
                  oceanWavesGUI app = new oceanWavesGUI();
                } else if (animeName == "The Wind Rises") {
                  theWindRisesGUI app = new theWindRisesGUI();
                }*/
            }
        }
    }
}
