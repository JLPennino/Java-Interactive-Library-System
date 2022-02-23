package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu booksMenu;
    private JMenu membersMenu;

    private JMenuItem adminExit;

    private JMenuItem booksView;
    private JMenuItem booksSelect;
    private JMenuItem booksGet;
    private JMenuItem booksAdd;
    private JMenuItem booksDel;	
    private JMenuItem booksIssue;
    private JMenuItem booksReturn;

    private JMenuItem memView;
    private JMenuItem memSelect;
    private JMenuItem memGet;
    private JMenuItem memAdd;
    private JMenuItem memDel;

    private Library library;

    public MainWindow(Library library) {

        initialize();
        this.library = library; //Initializing library
    } 
    
    public Library getLibrary() {
        return library;
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Library Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding booksMenu menu and menu items
        booksMenu = new JMenu("Books");
        menuBar.add(booksMenu);

        booksView = new JMenuItem("View");
        booksSelect = new JMenuItem("Select");
        booksGet = new JMenuItem("Get");
        booksAdd = new JMenuItem("Add");
        booksDel = new JMenuItem("Delete");
        booksIssue = new JMenuItem("Issue");
        booksReturn = new JMenuItem("Return");
        booksMenu.add(booksView);
        booksMenu.add(booksSelect);
        booksMenu.add(booksGet);
        booksMenu.add(booksAdd);
        booksMenu.add(booksDel);
        booksMenu.add(booksIssue);
        booksMenu.add(booksReturn);
        for (int i = 0; i < booksMenu.getItemCount(); i++) {
            booksMenu.getItem(i).addActionListener(this);
        }

        // adding membersMenu menu and menu items
        membersMenu = new JMenu("Members");
        menuBar.add(membersMenu);

        memView = new JMenuItem("View");
        memSelect = new JMenuItem("Select");
        memAdd = new JMenuItem("Add");
        memGet = new JMenuItem("Get");
        memDel = new JMenuItem("Delete");

        membersMenu.add(memView);
        membersMenu.add(memSelect);
        membersMenu.add(memGet);
        membersMenu.add(memAdd);
        membersMenu.add(memDel); //Creating main window

        memView.addActionListener(this);
        memSelect.addActionListener(this);
        memAdd.addActionListener(this);
        memGet.addActionListener(this);
        memDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

    }	

/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, LibraryException {
//        Library library = LibraryData.load();
//        new MainWindow(library);			
//    }



    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            System.exit(0);
        } else if (ae.getSource() == booksView) {
            displayBooks();
            
        } else if (ae.getSource() == booksSelect) {   
        	new SelectBookWindow(this);
        	
        } else if (ae.getSource() == booksGet) {	
        	new BookWindow(this);
        	
        } else if (ae.getSource() == booksAdd) {
            new AddBookWindow(this);
            
        } else if (ae.getSource() == booksDel) {
        	new DeleteBookWindow(this);
            
        } else if (ae.getSource() == booksIssue) {
            
            
        } else if (ae.getSource() == booksReturn) {
            
            
        } else if (ae.getSource() == memView) {
            displayPatrons();
        
        } else if (ae.getSource() == memSelect) {    
        	new SelectPatronWindow(this);
        	
        } else if (ae.getSource() == memGet) {
        	new PatronWindow(this);
        } else if (ae.getSource() == memAdd) {
        	new AddPatronWindow(this);
            
        } else if (ae.getSource() == memDel) { //Menu display within main window
        	new DeletePatronWindow(this);
            
        }
    }

    public void displayBooks() {
        List<Book> booksList = library.getBooks().stream()
        		.filter(b -> b.isDeleted() == false || b.isDeleted() == false)
        		.collect(Collectors.toList());
        
        // headers for the  display books table
        String[] columns = new String[]{"Title", "Author", "Pub Date", "Status", "Due Date (If Applicable)", "Is deleted"};

        Object[][] data = new Object[booksList.size()][6];
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getPublicationYear();
            data[i][3] = book.getStatus();
            data[i][4] = book.getDueDate();
            data[i][5] = book.isDeleted();
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }	
    
    public void displayPatrons() {
    	List<Book> booksList = library.getBooks();
        List<Patron> patronslist = library.getPatrons().stream()
        		.filter(p -> p.isDeleted() == false || p.isDeleted() == false)
        		.collect(Collectors.toList());
        // headers for the display patrons table
        String[] columns = new String[]{"ID", "Name", "Phone", "Email", "Number of Books", "Books", "Is deleted"};
        
        Object[][] data = new Object[patronslist.size()][7];
        for (int i = 0; i < patronslist.size(); i++) {
            Patron patron = patronslist.get(i);
            data[i][0] = patron.getId();
            data[i][1] = patron.getName();
            data[i][2] = patron.getPhone();
            data[i][3] = patron.getEmail();
            data[i][4] = patron.getBooks(library).size();
            data[i][5] = String.join(", ", patron.getBooks(library).stream().map(b -> b.getTitle()).collect(Collectors.toList()));
            data[i][6] = patron.isDeleted();
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }	
    
    public void displaySelectPatron(int patronId) { //Displaying specific patron within gui
        List<Patron> patronsList = library.getPatrons();
        
        patronsList = patronsList
        		.stream()
        		.filter(
        				p -> p.getId() == patronId
        		)
        		.collect(Collectors.toList());
        
        // headers for the table
        String[] columns = new String[]{"ID", "Name", "Phone", "Email", "Number of books", "Books" };
        
        Object[][] data = new Object[patronsList.size()][6];
        for (int i = 0; i < patronsList.size(); i++) {
            Patron patron = patronsList.get(i);
            data[i][0] = patron.getId();
            data[i][1] = patron.getName();
            data[i][2] = patron.getPhone();
            data[i][3] = patron.getEmail();
            data[i][4] = patron.getBooks(library).size();
            data[i][5] = String.join(", ", patron.getBooks(library).stream().map(b -> b.getTitle()).collect(Collectors.toList()));
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }       
        public void displayViewBook(int patronId) { //Displaying specific patron details alongside book loans to them within gui
            List<Patron> patronsList = library.getPatrons();
            
            patronsList = patronsList
            		.stream()
            		.filter(
            				p -> p.getId() == patronId
            		)
            		.collect(Collectors.toList());
            
            // headers for the patron details table
            String[] columns = new String[]{"ID", "Name", "Phone", "Email", "Number of books", "Books" };
            
            Object[][] data = new Object[patronsList.size()][6];
            for (int i = 0; i < patronsList.size(); i++) {
                Patron patron = patronsList.get(i);
                data[i][0] = patron.getId();
                data[i][1] = patron.getName();
                data[i][2] = patron.getPhone();
                data[i][3] = patron.getEmail(); //Format for displaying patron details within table
                data[i][4] = patron.getBooks(library).size();
                data[i][5] = String.join(", ", patron.getBooks(library).stream().map(b -> b.getTitle()).collect(Collectors.toList()));
            }

            JTable table = new JTable(data, columns);
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(table));
            this.revalidate();
        }
            
        public void displayViewPatron(int bookId) { //Displaying specific book details alongside it's associated patron id to them within gui
            List<Book> booksList = library.getBooks();
            
            booksList = booksList
            		.stream()
            		.filter(
            				p -> p.getId() == bookId
            		)
            		.collect(Collectors.toList());
            
            // headers for the book details table
            String[] columns = new String[]{"ID", "Title", "Author", "Publication Year", "Publisher", "Status", "Due Date (If Applicable)"};
            
            Object[][] data = new Object[booksList.size()][7];
            for (int i = 0; i < booksList.size(); i++) {
                Book book = booksList.get(i);
                data[i][0] = book.getId();
                data[i][1] = book.getTitle();
                data[i][2] = book.getAuthor();
                data[i][3] = book.getPublicationYear();
                data[i][4] = book.getPublisher();
                data[i][5] = book.getStatus();
                data[i][6] = book.getDueDate();//Format for displaying book details within table
            }

            JTable table = new JTable(data, columns);
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(table));
            this.revalidate();
        }	
}

