package com.an.rozhnov.draw.sub_panels;

import com.an.rozhnov.Init;
import com.an.rozhnov.file.save.FileChooserDialog;
import com.an.rozhnov.windows.SetupImageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UpperPanel extends JPanel implements ActionListener {

    private ToolPanel toolPanel;

    public UpperPanel() {
        setLayout(new BorderLayout());

        this.toolPanel = new ToolPanel();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.darkGray);
        menuBar.setForeground(Color.WHITE);
        menuBar.setBorder(null);
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
//        menuBar.add(createViewMenu());

        add(menuBar, BorderLayout.NORTH);
        add(toolPanel, BorderLayout.CENTER);
    }

    private JMenu createFileMenu () {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);

        JMenuItem createNew = new JMenuItem("New map...");
        createNew.addActionListener(this);
        createNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));

        JMenuItem loadExisting = new JMenuItem("Load");
        loadExisting.addActionListener(this);
        loadExisting.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));

        JMenuItem saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(this);
        saveAs.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.SHIFT_MASK));

        JMenuItem exitMainMenu = new JMenuItem("Back to main menu");
        exitMainMenu.addActionListener(this);
        exitMainMenu.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        fileMenu.add(createNew);
        fileMenu.add(loadExisting);
        fileMenu.addSeparator();
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(exitMainMenu);
        fileMenu.add(exit);

        return fileMenu;
    }

    private JMenu createEditMenu () {
        JMenu editMenu = new JMenu("Edit");
        editMenu.setForeground(Color.WHITE);

        JMenuItem brushTool = new JMenuItem("Brush tool");
        brushTool.addActionListener(this);
        brushTool.setAccelerator(KeyStroke.getKeyStroke('b'));

        JMenuItem dragTool = new JMenuItem("Drag tool");
        dragTool.addActionListener(this);
        dragTool.setAccelerator(KeyStroke.getKeyStroke('d'));

        JMenuItem eraserTool = new JMenuItem("Eraser");
        eraserTool.addActionListener(this);
        eraserTool.setAccelerator(KeyStroke.getKeyStroke('e'));

        editMenu.add(brushTool);
        editMenu.add(dragTool);
        editMenu.add(eraserTool);

        return editMenu;
    }

//    private JMenu createViewMenu () {
//        JMenu viewMenu = new JMenu("View");
//        viewMenu.setForeground(Color.WHITE);
//
//        JMenuItem zoomIn = new JMenuItem("Zoom In");
//        zoomIn.addActionListener(this);
//        zoomIn.setAccelerator(KeyStroke.getKeyStroke('Z', '+'));
//
//        JMenuItem zoomOut = new JMenuItem("Zoom Out");
//        zoomOut.addActionListener(this);
//        zoomOut.setAccelerator(KeyStroke.getKeyStroke('Z', '-'));
//
//        viewMenu.add(zoomIn);
//        viewMenu.add(zoomOut);
//
//        return viewMenu;
//    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem button = (JMenuItem) e.getSource();

        if (button.getActionCommand().equals("New map...")) {
            SetupImageDialog setup = new SetupImageDialog ();
            setup.setVisible(true);

        } else if (button.getActionCommand().equals("Load")) {
            FileChooserDialog f = new FileChooserDialog ("Open");

            if (f.getOperationResult() == JFileChooser.APPROVE_OPTION && f.getFile() != null) {
                Init.setBuildPanel(FileChooserDialog.path);
                Init.mapBuildingPanel.getDrawingModePanel().pushButton(1);
                Init.mapBuildingPanel.setDragMode();
            }

        } else if (button.getActionCommand().equals("Save")) {
            FileChooserDialog f = new FileChooserDialog ("Save");

        } else if (button.getActionCommand().equals("Save as")) {
            FileChooserDialog f = new FileChooserDialog ("SaveAs");

        } else if (button.getActionCommand().equals("Back to main menu")) {
            Init.setMainPanel();

        } else if (button.getActionCommand().equals("Brush tool")) {
            Init.mapBuildingPanel.getDrawingModePanel().pushButton(0);
            Init.mapBuildingPanel.setBrushMode();

        } else if (button.getActionCommand().equals("Drag tool")) {
            Init.mapBuildingPanel.getDrawingModePanel().pushButton(1);
            Init.mapBuildingPanel.setDragMode();

        } else if (button.getActionCommand().equals("Earaser")) {
            Init.mapBuildingPanel.getDrawingModePanel().pushButton(2);
            Init.mapBuildingPanel.setEraseMode();

        } else if (button.getActionCommand().equals("Exit"))
            System.exit(0);

    }
}
