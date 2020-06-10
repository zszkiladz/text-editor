package pl.plauszta.component;

import pl.plauszta.search.SearchAction;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class CheckBox extends JCheckBox {
    public CheckBox(JRadioButtonMenuItem regexItem, SearchAction searchAction) {
        super("Use regex");
        setName("UseRegExCheckbox");
        addItemListener(e -> regexItem.setSelected(e.getStateChange() == ItemEvent.SELECTED));
        addItemListener(e -> searchAction.changeSearch(e.getStateChange() == ItemEvent.SELECTED));
    }
}
