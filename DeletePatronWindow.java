package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.HidePatron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
/**
 * @author Jamye Pennino
 */
public class DeletePatronWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField idText = new JTextField();
    private JTextField hideText = new JTextField();

    private JButton addBtn = new JButton("Hide");
    private JButton cancelBtn = new JButton("Cancel");

    public DeletePatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize(); //Linking to mainwindow class
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 

        setTitle("Delete a Patron from view");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("ID : "));
        topPanel.add(idText);
        topPanel.add(new JLabel("Hide? (y/n) : "));
        topPanel.add(hideText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn); //Creating the window for delete patron

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            DeletePatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    /**
     * Making text inputs for required id and hide options then executing the hidepatron command to then display the result at displaypatrons class
     */
    private void DeletePatron() {
        try {
        	String id = idText.getText();
            int identity = Integer.parseInt(id);
            String hideStr = hideText.getText();
            boolean hide = hideStr.equalsIgnoreCase("y") ? true : false;
            
            System.out.println(hideStr);
            System.out.println(hide);
            
            // create and execute the DeleteBook Command
            Command hidePatron = new HidePatron(identity, hide, hide);
            hidePatron.execute(mw.getLibrary(), LocalDate.now());
            // refresh the view with the list of books
            mw.displayPatrons();
            // hide (close) the DeletePatronWindow
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
