package pl.plauszta.component.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.TextEditor;

import javax.swing.*;
import java.io.File;

public class FileMenu extends JMenu {
    private static final Logger log = LoggerFactory.getLogger(FileMenu.class);

    public FileMenu(JFileChooser chooser, JTextArea textArea) {
        super("File");
        this.setName("MenuFile");

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setName("MenuOpen");
        loadItem.addActionListener(actionEvent -> {
            log.info("Open filechooser");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = chooser.getSelectedFile();
                log.info("Opening file: {}", selectedFile.getName());
                TextEditor.loadFile(selectedFile, textArea);
            }
        });

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setName("MenuSave");
        saveItem.addActionListener(actionEvent -> {
            log.info("Open filechooser");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = chooser.getSelectedFile();
                log.info("Saving file: {}", selectedFile.getName());
                TextEditor.saveFile(selectedFile, textArea);
            }
        });

        this.add(saveItem);
        this.add(loadItem);
        this.add(new JSeparator());
    }
}
