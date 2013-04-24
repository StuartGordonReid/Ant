/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalUserInterface;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author stuart
 */
public class ConfigItem extends JPanel {

    JLabel configLabel;
    JTextArea userInput;
    int configValue;

    ConfigItem(String field) {
        configLabel = new JLabel(field);
        userInput = new JTextArea();
        configValue = 0;
    }
}

class ConfigItem_layout implements LayoutManager{

    public ConfigItem_layout() {
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 640 + insets.left + insets.right;
        dim.height = 360 + insets.top + insets.bottom;

        return dim;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        int num1 = 0;
        int w = parent.getWidth();
        int h = parent.getHeight();

        Component c;

        c = parent.getComponent(0);
        if (c.isVisible()) {
            c.setBounds(insets.left + 10, insets.top + num1, (int) (w / 2 - 20), (int) (40));
        }
    }
}
