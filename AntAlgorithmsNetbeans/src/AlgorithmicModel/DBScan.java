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

    /*
     *DBSCAN(D, eps, MinPts)
        C = 0
        for each unvisited point P in dataset D
           mark P as visited
           NeighborPts = regionQuery(P, eps)
           if sizeof(NeighborPts) < MinPts
              mark P as NOISE
           else
              C = next cluster
              expandCluster(P, NeighborPts, C, eps, MinPts)
     */
    public Cluster scanGrid() {
        Cluster cluster = new Cluster();

        return cluster;
    }

    /*
     *expandCluster(P, NeighborPts, C, eps, MinPts)
        add P to cluster C
        for each point P' in NeighborPts 
           if P' is not visited
              mark P' as visited
              NeighborPts' = regionQuery(P', eps)
              if sizeof(NeighborPts') >= MinPts
                 NeighborPts = NeighborPts joined with NeighborPts'
           if P' is not yet member of any cluster
              add P' to cluster C
     */
    public Cluster expandCluster(Cluster C) {
        return C;
    }

    /*   NEIGHBOURS
     *   (X+1,Y+1) | (X,Y+1) | (X+1,Y+1)
     *   --------------------------------
     *     (X-1,Y) |  (X,Y)  | (X+1,Y)
     *   --------------------------------
     *   (X-1,Y-1) | (X,Y-1) | (X+1,Y-1)
     */
    /*
     * regionQuery(P, eps)
         return all points within P's eps-neighborhood
     */
    public Item[] getItemNeighbours(Item item) {
        ArrayList<Item> neighbours = new ArrayList();
        int[] xOffsets = {-1, 0, 1};
        int[] yOffsets = {-1, 0, 1};

        for (int i = 0; i < xOffsets.length; i++) {
            for (int j = 0; j < yOffsets.length; j++) {
                Item neighbour = getNeighbour(item.getX(), item.getY(), xOffsets[i], yOffsets[j]);
                if (neighbour != null) {
                    neighbours.add(neighbour);
                }
            }
        }

        if (neighbours.size() >= clusterMin - 1) {
            return neighbours.toArray(new Item[0]);
        } else {
            return null;
        }
    }

    public Item getNeighbour(int x, int y, int xOffset, int yOffset) {
        try {
            GridObject gridObject = (GridObject) grid.getGrid()[x + xOffset][y + yOffset];
            if (gridObject.getObjectType().equals("I")) {
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
