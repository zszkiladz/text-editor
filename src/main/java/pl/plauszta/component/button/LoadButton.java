package pl.plauszta.component.button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.TextEditor;

import javax.swing.*;
import java.io.File;

public class LoadButton extends Button {
    private static final Logger log = LoggerFactory.getLogger(LoadButton.class);
    public LoadButton(JFileChooser chooser, JTextArea textArea, String name) {
        super(new ButtonIcon(LoadButton.class.getClassLoader().getResource("browse.png")), name);
        this.addActionListener(actionEvent -> {
            log.info("Open FileChooser");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = chooser.getSelectedFile();
                log.info("Opening file: {}", selectedFile.getName());
                TextEditor.loadFile(selectedFile, textArea);
            }
        });
    }
}
