package pl.plauszta.component.button;

import javax.swing.*;

public class Button extends JButton {
    public Button(ButtonIcon buttonIcon, String name) {
        super(buttonIcon);
        this.setName(name);
        this.setSize(20, 20);
    }
}
