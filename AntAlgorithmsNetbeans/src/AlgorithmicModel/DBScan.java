/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.ArrayList;
import java.util.Iterator;

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
    public ArrayList<Cluster> scanGrid() {
        clusters = new ArrayList<Cluster>();
        GridObject object;

        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < grid.getGridSize(); j++) {

                // skip if already visited
                object = ((GridObject) grid.getGrid()[i][j]);
                if (object.status == 1) {
                    break;
                }
                object.status = 1; // mark as visited

                if (object.objectType.equals("I")) {
                    ArrayList<Item> neighbors = getItemNeighbours((Item) object);

                    if (neighbors.size() > 0) {
                        object.status = 2;
                    } else {
                        Cluster c = new Cluster();
                        c.list = neighbors;
                        clusters.add(c);
                    }
                }
            }
        }
        return clusters;
    }

    public Cluster expandCluster(ArrayList<Item> neighbours, Cluster cluster) {


        return cluster;
    }

    public ArrayList<Item> getItemNeighbours(Item item) {
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

        //remove self from list
        neighbours.remove(item);
        return neighbours;
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

    private boolean isInACluster(Item item) {
        boolean found = false;

        for (Cluster c : clusters) {
            if (c.list.contains(item)) {
                found = true;
            }
        }
        return found;
    }
}

class Cluster {

    ArrayList<Item> list;
    // item.status == 0 => unvisited
    // item.status == 1 => visited
    // item.status == 2 => noise

    Cluster() {
        list = new ArrayList();
    }

    public void add(Item x) {
        list.add(x);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        try {
            String s = "";
            for (int i = 0; i < list.size() - 1; ++i) {
                s += list.get(i).toString() + "; ";
            }
            s += list.get(list.size() - 1).toString();
            return s + "\n";
        } catch (Exception e) {
            return "";
        }
    }
}
//                if (object.objectType.equals("I")){
//                    Cluster c = new Cluster();
//                    c.add(object);
//                    clusters.add(c);
//                }
// remove clusters of size 0
//        Iterator<Cluster> iter = clusters.iterator();
//        while (iter.hasNext()){
//            if (iter.next().isEmpty())
//                iter.remove();
//        }
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
/*public Cluster expandCluster(GridObject object, ArrayList<Item> neighbors, Cluster c) {
 System.out.println("expand Cluster");
 // add item to cluster
 c.add((Item)object);

 Iterator iter = neighbors.iterator();
 while (iter.hasNext()) {
 Item item = (Item) iter.next();

 // if item is not visited
 if (item.status != 1) {
 // mark visited
 item.status = 1;
 // get neighbors of neighbor
 ArrayList<Item> new_neighbors = getItemNeighbours(item);

 // if sizeof(NeighborPts') >= MinPts
 if (new_neighbors.size() >= clusterMin) {
 // perform a safe removal of elements using iter.remove
 Iterator iter2 = neighbors.iterator();
 while (iter2.hasNext()) {
 }
 neighbors.removeAll(new_neighbors);
 neighbors.addAll(new_neighbors);
 }
 }
 // if item is not yet a member of any cluster
 if (isInACluster(item)) {
 // add item to cluster
 c.add(item);
 }

 }
 return c;
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