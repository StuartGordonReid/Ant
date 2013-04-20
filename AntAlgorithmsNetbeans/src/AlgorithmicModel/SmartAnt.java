/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.LinkedList;

/**
 *
 * @author stuart
 */
public class SmartAnt extends Ant {
    
    SmartAnt(Grid g, int antMemorySize) {
        super(g, antMemorySize);
    }

    @Override
    public int[] getValidMove() {
        int new_x = 0;
        int new_y = 0;
        int[] move = new int[2];

        if (this.gotItem == true) {
            
            return move;
        } else {
            move = super.getValidMove();
            return move;
        }
    }
}
