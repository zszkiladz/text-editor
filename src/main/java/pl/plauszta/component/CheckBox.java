package pl.plauszta.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.plauszta.search.SearchAction;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class CheckBox extends JCheckBox {

    public static final Logger log = LoggerFactory.getLogger(CheckBox.class);

    public CheckBox(JRadioButtonMenuItem regexItem, SearchAction searchAction) {
        super("Use regex");
        setName("UseRegExCheckbox");
        addItemListener(e -> {
            regexItem.setSelected(e.getStateChange() == ItemEvent.SELECTED);
            log.info("Changing to {}", isSelected() ? "regex searching" : "simple searching");
        });
        addItemListener(e -> searchAction.changeSearch(e.getStateChange() == ItemEvent.SELECTED));
    }
}
