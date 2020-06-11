package pl.plauszta;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.component.CheckBox;
import pl.plauszta.component.SearchField;
import pl.plauszta.component.SearchPanel;
import pl.plauszta.component.menu.FileMenu;
import pl.plauszta.component.menu.SearchMenu;
import pl.plauszta.search.SearchAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class TextEditor extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(TextEditor.class);
    private final SearchAction searchAction;

    public TextEditor() {
        initWindow();
        final JTextField textSearch = new SearchField();
        final JTextArea textArea = new JTextArea();
        searchAction = new SearchAction(textSearch, textArea);
        JRadioButtonMenuItem regexItem = new JRadioButtonMenuItem("Use regular expressions");
        JCheckBox checkBox = new CheckBox(regexItem, searchAction);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFileChooser chooser = initChooser();
        JPanel panel = new SearchPanel(chooser, textArea, textSearch, checkBox, searchAction);

        textArea.setName("TextArea");
        textArea.setLineWrap(true);
        scrollPane.setName("ScrollPane");
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        initMenuBar(textArea, chooser, checkBox, regexItem);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initWindow() {
        LafManager.install(new DarculaTheme());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setTitle("Text Editor");
        setIconImage(new ImageIcon(Objects.requireNonNull(TextEditor.class.getClassLoader().getResource("icon.png"))).getImage());
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point(displaySize.width / 2 - 250, displaySize.height / 2 - 150));
    }

    private JFileChooser initChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setName("FileChooser");
        add(chooser);
        return chooser;
    }

    private void initMenuBar(JTextArea textArea, JFileChooser chooser, JCheckBox checkBox, JRadioButtonMenuItem regexItem) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new FileMenu(chooser, textArea);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setName("MenuExit");
        exitItem.addActionListener(actionEvent -> this.dispose());

        JMenu searchMenu = new SearchMenu(regexItem, checkBox, searchAction);

        menu.add(exitItem);
        menuBar.add(menu);
        menuBar.add(searchMenu);
        this.setJMenuBar(menuBar);
    }

    public static void loadFile(File file, JTextArea textArea) {
        textArea.setText(null);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            textArea.setText(new String(fileInputStream.readAllBytes()));
            log.info("File opened. File: {}", file.getName());
        } catch (IOException ex) {
            log.error("Cannot load file: {}", file.getName(), ex);
        }
    }

    public static void saveFile(File file, JTextArea textArea) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(textArea.getText().getBytes());
            log.info("File saved. File: {}", file.getName());
        } catch (IOException ex) {
            log.error("Cannot save file: {}", file.getName(), ex);
        }
    }
}
