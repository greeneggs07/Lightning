/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.test.TestUtil;

/**
 *
 * @author Owner
 */
public class EntryDialog extends JDialog {
    private CommandDispatcher commandDispatcher;
    private JPanel contentPanel;
    private JPanel existingPanel;
    private JTextField existingFileField;
    private JButton browseButton;
    
    private JPanel newPanel;
    private JTextField newFileField;
    private JButton createButton;
    
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton switchButton;
    private JButton continueButton;
    
    private String path;
    private boolean switchUp;
    private boolean existing;
    
    public EntryDialog(CommandDispatcher commandDispatcher){
        super();
        this.commandDispatcher = commandDispatcher;
        initComponents();
    }

    private void initComponents() {
        this.setSize(new Dimension(410, 200));
        switchUp = true;
        contentPanel = new JPanel();
        existingPanel = new JPanel();
        newPanel = new JPanel();
        buttonPanel = new JPanel();
        JLabel selectLabel = new JLabel("Select an existing agenda to work with:");
        JLabel createLabel = new JLabel("Create a new task list:");
        existingFileField = new JTextField(50);
        newFileField = new JTextField(50);
        createBrowseButton();
        createCreateButton();
        createSwitchButton();
        createCancelButton();
        createContinueButton();
        
        GroupLayout existingLayout = new GroupLayout(existingPanel);
        existingPanel.setLayout(existingLayout);
        
        existingLayout.setHorizontalGroup(
                existingLayout.createSequentialGroup()
                .addGroup(existingLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectLabel)
                    .addGroup(existingLayout.createSequentialGroup()
                        .addComponent(existingFileField)
                        .addComponent(browseButton))));
        existingLayout.setVerticalGroup(
                existingLayout.createSequentialGroup()
                    .addComponent(selectLabel)
                    .addGroup(existingLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(existingFileField)
                        .addComponent(browseButton)));
        
        GroupLayout newLayout = new GroupLayout(newPanel);
        newPanel.setLayout(newLayout);
        
        newLayout.setHorizontalGroup(
                newLayout.createSequentialGroup()
                .addGroup(newLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(createLabel)
                    .addGroup(newLayout.createSequentialGroup()
                        .addComponent(newFileField)
                        .addComponent(createButton))));
        newLayout.setVerticalGroup(newLayout.createSequentialGroup()
                    .addComponent(createLabel)
                    .addGroup(newLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newFileField)
                        .addComponent(createButton)));
        
        GroupLayout buttonLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonLayout);
        
        buttonLayout.setHorizontalGroup(
                buttonLayout.createSequentialGroup()
                    .addComponent(cancelButton)
                    .addComponent(switchButton)
                    .addComponent(continueButton));
        buttonLayout.setVerticalGroup(
                buttonLayout.createSequentialGroup()
                .addGroup(buttonLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(switchButton)
                    .addComponent(continueButton)));
        
        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(existingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(newPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE));
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(contentPanelLayout.createSequentialGroup()
                    .addComponent(existingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(newPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)));
        this.setContentPane(contentPanel);
        this.setTitle("Let's Fucking Rock");
    }

    private void createBrowseButton() {
        browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                browseButtonActionPerformed(e);
            }
        });
        
    }
    
    private void browseButtonActionPerformed(ActionEvent e){
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("XML file", "xml"));
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            path = fc.getSelectedFile().getPath();
            switchButton.setEnabled(false);
            continueButton.setEnabled(true);
            existingFileField.setText(path);
            existing = true;
        }
        
        
    }

    private void createCreateButton() {
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonActionPerformed(e);
            }
        });
        createButton.setEnabled(false);
        newFileField.setEnabled(false);
    }
    
    private void createButtonActionPerformed(ActionEvent e){
        final JFileChooser fc = new JFileChooser();
        if(!newFileField.getText().isEmpty()){
            //System.out.println(fc.getFileSystemView().getDefaultDirectory()+
              //      "\\"+newFileField.getText()+".xml");
            path = fc.getFileSystemView().getDefaultDirectory()+
                    "\\"+newFileField.getText()+".xml";
            File file = new File(path);
            if(file.exists())
                JOptionPane.showMessageDialog(null, "File already exists.");
            else {
                try {
                    file.createNewFile();
                    createButton.setEnabled(false);
                    newFileField.setEditable(false);
                    switchButton.setEnabled(false);
                    continueButton.setEnabled(true);
                    existing = false;
                } catch (IOException ex) {
                    Logger.getLogger(EntryDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                        }
//        int returnVal = fc.showOpenDialog(this);
//        if(returnVal == JFileChooser.APPROVE_OPTION){
//            path = fc.getSelectedFile().getPath();
//            switchButton.setEnabled(false);
//            continueButton.setEnabled(true);
//            existingFileField.setText(path);
//            existing = true;
//        }
        }
    }

    private void createSwitchButton() {
        switchButton = new JButton("Swith Up");
        switchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchButtonActionPerformed(e);
            }
        });
    }
    
    private void switchButtonActionPerformed(ActionEvent e){
        if(switchUp){
            switchUp = false;
            existingFileField.setEnabled(false);
            browseButton.setEnabled(false);
            newFileField.setEnabled(true);
            createButton.setEnabled(true);
        }
        else {
            switchUp = true;
            existingFileField.setEnabled(true);
            browseButton.setEnabled(true);
            newFileField.setEnabled(false);
            createButton.setEnabled(false);
        }
    }
    
        private void createCancelButton() {
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelButtonActionPerformed(e);
                }
            });
        }
    
    
    private void cancelButtonActionPerformed(ActionEvent e){
        setVisible(false);
        commandDispatcher.dispatch(CommandDispatcher.Command.EXIT_ON_ENTRY);
        dispose();
    }
    
    private void createContinueButton() {
        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueButtonActionPerformed(e);
            }
        });
        continueButton.setEnabled(false);
    }
        
    private void continueButtonActionPerformed(ActionEvent e){
        setVisible(false);
        if(existing)
        
        commandDispatcher.dispatch(CommandDispatcher.Command.START_UP_EXISTING, path);
//        TestUtil test = new TestUtil();
//        commandDispatcher.dispatch(CommandDispatcher.Command.START_UP, test.getTestArray());
        else 
            commandDispatcher.dispatch(CommandDispatcher.Command.START_UP_NEW, path);
        
        dispose();
    }
}
