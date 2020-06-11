package pl.plauszta.component.button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.TextEditor;

import javax.swing.*;
import java.io.File;

public class SaveButton extends Button {

    private static final Logger log = LoggerFactory.getLogger(SaveButton.class);

    public SaveButton(JFileChooser chooser, JTextArea textArea, String name) {
        super(new ButtonIcon(SaveButton.class.getClassLoader().getResource("save.png")), name);
        this.addActionListener(actionEvent -> {
            log.info("Open FileChooser");
            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = chooser.getSelectedFile();
                log.info("Saving file: {}", selectedFile.getName());
                TextEditor.saveFile(selectedFile, textArea);
            }
        });
    }
}
