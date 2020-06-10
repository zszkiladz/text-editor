package pl.plauszta.component;

import pl.plauszta.component.button.Button;
import pl.plauszta.component.button.ButtonIcon;
import pl.plauszta.component.button.LoadButton;
import pl.plauszta.component.button.SaveButton;
import pl.plauszta.search.SearchAction;

import javax.swing.*;

public class SearchPanel extends JPanel {
    public SearchPanel(JFileChooser chooser, JTextArea textArea, JTextField textSearch, JCheckBox checkBox, SearchAction searchAction) {
        super();
        textSearch.setName("SearchField");

        JButton searchButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("search.png")), "StartSearchButton");
        searchButton.addActionListener(actionEvent -> searchAction.findFirst());

        JButton nextButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("right-arrow.png")), "NextMatchButton");
        nextButton.addActionListener(actionEvent -> searchAction.findNext());

        JButton prevButton = new Button(new ButtonIcon(SearchPanel.class.getClassLoader().getResource("left-arrow.png")), "PreviousMatchButton");
        prevButton.addActionListener(actionEvent -> searchAction.findPrevious());

        this.add(new SaveButton(chooser, textArea, "SaveButton"));
        this.add(new LoadButton(chooser, textArea, "OpenButton"));
        this.add(textSearch);
        this.add(searchButton);
        this.add(prevButton);
        this.add(nextButton);
        this.add(checkBox);
    }
}
