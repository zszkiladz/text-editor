package pl.plauszta.component.button;

import pl.plauszta.TextEditor;

import javax.swing.*;

public class SaveButton extends Button {
    public SaveButton(JFileChooser chooser, JTextArea textArea, String name) {
        super(new ButtonIcon(SaveButton.class.getClassLoader().getResource("save.png")), name);
        this.addActionListener(actionEvent -> {
            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                TextEditor.saveFile(chooser.getSelectedFile(), textArea);
            }
        });
    }
}
