package pl.plauszta.component.menu;

import pl.plauszta.TextEditor;

import javax.swing.*;

public class FileMenu extends JMenu {
    public FileMenu(JFileChooser chooser, JTextArea textArea) {
        super("File");
        this.setName("MenuFile");

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setName("MenuOpen");
        loadItem.addActionListener(actionEvent -> {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                TextEditor.loadFile(chooser.getSelectedFile(), textArea);
            }
        });

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setName("MenuSave");
        saveItem.addActionListener(actionEvent -> {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                TextEditor.saveFile(chooser.getSelectedFile(), textArea);
            }
        });

        this.add(saveItem);
        this.add(loadItem);
        this.add(new JSeparator());
    }
}
