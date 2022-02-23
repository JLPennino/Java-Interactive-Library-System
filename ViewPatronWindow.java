package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class ViewPatronWindow extends JFrame {

    private MainWindow mw;
    private int bookId;

    public ViewPatronWindow(MainWindow mw, int bookId) {
        this.mw = mw;
        this.bookId = bookId;
    	initialize(); //Linking to main window and inserted bookid
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

        setTitle("Patron Details");

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
    
    public void displayViewPatron() {
    	try {
    		Book book = this.mw.getLibrary().getBookByID(bookId);
    		ArrayList<Patron> patrons = new ArrayList<>();
    		
    		if (book.getLoan() == null) {
    			JOptionPane.showMessageDialog(this, "Book has not been loaned to the patron!", "Loan information notification", JOptionPane.INFORMATION_MESSAGE);
    			return; //checking to see if the book was loaned to patron
    		}
    		
    		patrons.add(book.getLoan().getPatron());
    		
    		String[] columns = new String[]{"Book ID","Patron ID", "Name", "Phone", "Email"};
	        
	        Object[][] data = new Object[patrons.size()][6];
    		
    		for (int i = 0; i < patrons.size(); i++) {
    			// headers for the books details associated with the specific patron
    	        Patron patron = patrons.get(i);
    	        data[i][0] = book.getId();
    			data[i][1] = patron.getId();
    	        data[i][2] = patron.getName();
    	        data[i][3] = patron.getPhone();
    	        data[i][4] = patron.getEmail();
    		}
    		
    		JTable table = new JTable(data, columns);
	        this.getContentPane().removeAll();
	        this.getContentPane().add(new JScrollPane(table));
	        this.revalidate();
    	} catch (LibraryException e) {
    		e.printStackTrace();
    	}
    }
}
