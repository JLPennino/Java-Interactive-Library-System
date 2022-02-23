package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class ViewBookWindow extends JFrame {

    private MainWindow mw;
    private int patronId;

    public ViewBookWindow(MainWindow mw, int patronId) {
        this.mw = mw;
        this.patronId = patronId;
    	initialize(); //Linking to main window and inserted patronid
    }
    
    public Library getLibrary() {
    	return this.mw.getLibrary();
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {} 

        setTitle("Book Details");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true); //Creating the display window 

    }
    
    public void displayViewBook() {
    	try {
    		Patron patron = this.mw.getLibrary().getPatronByID(patronId);
    		List<Book> books = patron.getBooks(this.mw.getLibrary());
    		//Headers for this specific table
    		String[] columns = new String[]{"Patron Id","Book ID", "Title", "Author", "Publication Year", "Publisher", "Due Date (If applicable)"};
	        
	        Object[][] data = new Object[books.size()][7];
    		
    		for (int i = 0; i < books.size(); i++) {
    			// headers for the table
    	        Book book = books.get(i);
    	        data[i][0] = patron.getId();
    			data[i][1] = book.getId();
    	        data[i][2] = book.getTitle();
    	        data[i][3] = book.getAuthor();
    	        data[i][4] = book.getPublicationYear();
    	        data[i][5] = book.getPublisher();
    	        data[i][6] = book.getDueDate();
    		} //Format for all books on loan to specific patron 
    		
    		JTable table = new JTable(data, columns);
	        this.getContentPane().removeAll();
	        this.getContentPane().add(new JScrollPane(table));
	        this.revalidate();
    	} catch (LibraryException e) {
    		e.printStackTrace();
    	}
    }
}
