package pl.plauszta.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.component.button.Button;
import pl.plauszta.component.button.ButtonIcon;
import pl.plauszta.component.button.LoadButton;
import pl.plauszta.component.button.SaveButton;
import pl.plauszta.search.SearchAction;
import pl.plauszta.search.SearchMethod;
import pl.plauszta.search.SearchWorker;

import javax.swing.*;

public class SearchPanel extends JPanel {

    private static final Logger log = LoggerFactory.getLogger(SearchPanel.class);

    public SearchPanel(JFileChooser chooser, JTextArea textArea, JTextField textSearch, JCheckBox checkBox, SearchAction searchAction) {
        super();
        textSearch.setName("SearchField");

        JButton searchButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("search.png")), "StartSearchButton");
        searchButton.addActionListener(actionEvent -> {
            log.info("Searching: {}", textSearch.getText());
            new SearchWorker(searchAction, SearchMethod.FIRST).execute();
        });

        JButton nextButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("right-arrow.png")), "NextMatchButton");
        nextButton.addActionListener(actionEvent -> {
            log.info("Searching next: {}", textSearch.getText());
            new SearchWorker(searchAction, SearchMethod.NEXT).execute();
        });

        JButton prevButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("left-arrow.png")), "PreviousMatchButton");
        prevButton.addActionListener(actionEvent -> {
            log.info("Searching previous: {}", textSearch.getText());
            new SearchWorker(searchAction, SearchMethod.PREVIOUS).execute();
        });

        this.add(new SaveButton(chooser, textArea, "SaveButton"));
        this.add(new LoadButton(chooser, textArea, "OpenButton"));
        this.add(textSearch);
        this.add(searchButton);
        this.add(prevButton);
        this.add(nextButton);
        this.add(checkBox);
    }
}
