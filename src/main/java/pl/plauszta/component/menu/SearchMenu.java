package pl.plauszta.component.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.search.SearchAction;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class SearchMenu extends JMenu {

    private static final Logger log = LoggerFactory.getLogger(SearchMenu.class);

    public SearchMenu(JRadioButtonMenuItem regexItem, JCheckBox checkBox, SearchAction searchAction) {
        super("Search");
        this.setName("MenuSearch");

        regexItem.setName("MenuUseRegExp");
        regexItem.addItemListener(e -> {
            checkBox.setSelected(e.getStateChange() == ItemEvent.SELECTED);
        });

        JMenuItem startItem = new JMenuItem("Start search");
        startItem.setName("MenuStartSearch");
        startItem.addActionListener(actionEvent -> {
            log.info("Searching...");
            searchAction.findFirst();
        });

        JMenuItem nextItem = new JMenuItem("Next match");
        nextItem.setName("MenuNextMatch");
        nextItem.addActionListener(actionEvent -> {
            log.info("Searching next...");
            searchAction.findNext();
        });

        JMenuItem prevItem = new JMenuItem("Previous match");
        prevItem.setName("MenuPreviousMatch");
        prevItem.addActionListener(actionEvent -> {
            log.info("Searching previous...");
            searchAction.findPrevious();
        });

        this.add(startItem);
        this.add(prevItem);
        this.add(nextItem);
        this.add(new JSeparator());
        this.add(regexItem);
    }
}
