package com.an.rozhnov.file.save;

import com.an.rozhnov.draw.MapBuildingPanel;
import com.an.rozhnov.entity.Tile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static com.an.rozhnov.draw.MapBuildingPanel.map;


public class FileChooserDialog extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final JFileChooser fileChooser;
	private File file;
	
	private int operationResult;
	public static String path = "";
	
	
	public FileChooserDialog (String mode) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter 
				("The Knights game files", "kni", "txt");
		fileChooser = new JFileChooser ();
		fileChooser.setFileFilter(filter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		try {
			switch (mode) {
				case "Save":
					saveFile();
					break;
				case "SaveAs":
					saveFileAs();
					break;
				case "Open":
					openNewFile();
					break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}

	private void openNewFile() throws IOException {
		operationResult = fileChooser.showOpenDialog(FileChooserDialog.this);
		
		if (operationResult == JFileChooser.APPROVE_OPTION) {			
			file = fileChooser.getSelectedFile();
			path = file.getPath();
		}
	}

	private void saveFile () throws IOException {
		if (MapBuildingPanel.path != null) {
			file = new File(MapBuildingPanel.path);
			save();

		} else
			saveFileAs();
	}

	private void saveFileAs () throws IOException {
		operationResult = fileChooser.showSaveDialog(FileChooserDialog.this);
		
		if (operationResult == JFileChooser.APPROVE_OPTION) {
			file = new File(fileChooser.getSelectedFile().getPath() + ".kni");
			save();
			MapBuildingPanel.path = file.getPath();
		}
	}

	private void save () throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(map.getWidth() + " " + map.getHeight() + "\n");

			Tile[] tiles = map.getTiles();
			Tile t;

			for (int i = 0; i < map.getMapLength(); i++) {
				 t = tiles[i];
				bw.write(t + "\n");
			}

			bw.flush();
		}
	}

	public File getFile() {
		return file;
	}

	public int getOperationResult() {
		return operationResult;
	}
}
