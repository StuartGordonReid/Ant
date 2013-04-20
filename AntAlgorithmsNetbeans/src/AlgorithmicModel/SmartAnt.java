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
            Item closest = memory.get(0);
            for (int i = 1; i < memory.size(); i++) {
                closest = closer(closest,memory.get(i));
            }
            return move;
        } else {
            move = super.getValidMove();
            return move;
        }
    }

    //Euclidian distance of two objects in memory
    public Item closer(Item one, Item two) {
        int x1 = one.getX();
        int y1 = one.getY();
        double diff1 = Math.sqrt(Math.pow((x1 - x), 2.0) + Math.pow((y1 - y), 2.0));

        int y2 = two.getY();
        int x2 = two.getX();
        double diff2 = Math.sqrt(Math.pow((x2 - x), 2.0) + Math.pow((y2 - y), 2.0));

        if (diff1 > diff2) {
            return two;
        } else {
            return one;
        }
    }
}
