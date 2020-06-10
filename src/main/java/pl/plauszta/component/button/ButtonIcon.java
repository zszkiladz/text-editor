package pl.plauszta.component.button;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ButtonIcon extends ImageIcon {
    public ButtonIcon(URL imagePath) {
        super(imagePath);
        Image image = this.getImage(); // transform it
        Image newimg = image.getScaledInstance(19, 19, Image.SCALE_SMOOTH); // scale it the smooth way
        this.setImage(newimg);
    }
}
