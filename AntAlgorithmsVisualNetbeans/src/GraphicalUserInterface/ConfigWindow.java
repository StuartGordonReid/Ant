/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author stuart
 */
public class ConfigWindow extends JFrame implements ActionListener {

    private JLabel gridSize_, iterations, numitems, numants, resolution;
    private int gridSize_val, iterations_val, numitems_val, numants_val, resolution_val;

    public ConfigWindow() {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
