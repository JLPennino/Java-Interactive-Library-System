package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PatronWindow extends JFrame implements ActionListener {

	private MainWindow mw;
    private JTextField idText = new JTextField();

    private JButton selectBtn = new JButton("Select");
    private JButton cancelBtn = new JButton("Cancel");

    public PatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize(); //Linking to main window
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 

        setTitle("Get a patron");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("ID : "));
        topPanel.add(idText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(selectBtn);
        bottomPanel.add(cancelBtn);

        selectBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw); //Creating patron get window 

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectBtn) {
            showPatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void showPatron() {
        try {
        	String idStr = idText.getText();
            int id = Integer.parseInt(idStr);
            ViewBookWindow vw = new ViewBookWindow(this.mw, id);
            
            // create and execute the ShowPatron Command
            Command showPatron = new ShowPatron(id);
            showPatron.execute(vw.getLibrary(), LocalDate.now());
            // refresh the view with the list of books associated with the patron
            vw.displayViewBook(); //calling this display in viewbookwindow class
            // hide (close) the PatrongetWindow
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Library getLibrary() {
    	return this.mw.getLibrary();
    }

}
