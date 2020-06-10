package pl.plauszta.component.button;

import pl.plauszta.TextEditor;

import javax.swing.*;

public class LoadButton extends Button {
    public LoadButton(JFileChooser chooser, JTextArea textArea, String name) {
        super(new ButtonIcon(LoadButton.class.getClassLoader().getResource("browse.png")), name);
        this.addActionListener(actionEvent -> {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                TextEditor.loadFile(chooser.getSelectedFile(), textArea);
            }
        });
    }
}
