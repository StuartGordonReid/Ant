/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.ArrayList;

/**
 *
 * @author stuart
 */
public class DBScan {

    private Grid grid;
    private int clusterMin;
    ArrayList<Cluster> clusters;

    DBScan(Grid g, int c) {
        grid = g;
        clusterMin = c;
    }

    public Cluster scanGrid() {
        Cluster cluster = new Cluster();

        return cluster;
    }

    public Cluster expandCluster(Cluster C) {
        return C;
    }

    public Item[] getItemNeighbours(Item item) {
        ArrayList<Item> neighbours = new ArrayList();

        /*   NEIGHBOURS
         *   (X+1,Y+1) | (X,Y+1) | (X+1,Y+1)
         *   --------------------------------
         *     (X-1,Y) |  (X,Y)  | (X+1,Y)
         *   --------------------------------
         *   (X-1,Y-1) | (X,Y-1) | (X+1,Y-1)
         */

        int xCoord = item.getLocationX();
        int yCoord = item.getLocationY();

        if (neighbours.size() >= clusterMin - 1) {
            return neighbours.toArray(new Item[0]);
        } else {
            return null;
        }
    }

    public Item getNeighbour(int x, int y, int xOffset, int yOffset) {
        try {
            if (grid.getObjectType(x + xOffset, y + yOffset).equals("I")) {
                return (Item) grid.getGrid()[x + xOffset][y + yOffset];
            } else {
                return null;
            }
        } catch (Exception err) {
            return null;
        }
    }
}

class Cluster {

    ArrayList<Item> cluster;

    Cluster() {
        cluster = new ArrayList();
    }

    public void add(Item x) {
        cluster.add(x);
    }
}
