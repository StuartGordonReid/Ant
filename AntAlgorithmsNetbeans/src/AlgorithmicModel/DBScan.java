/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author stuart
 */
public class DBScan {

    private Grid grid;
    private int clusterMin;
    ArrayList<Cluster> clusters;
    int epsilon;

    DBScan(Grid g, int c, int epsilon) {
        this.grid = g;
        this.clusterMin = c;
        this.epsilon = epsilon;
    }

    /* PSEUDO CODE
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
        Cluster c;
        
        // for every position that is unvisited
        for (int i = 0; i < grid.getGridSize(); ++i){
            for (int j = 0; j < grid.getGridSize(); ++j){

                // skip if already visited
                object = ((GridObject) grid.getGrid()[i][j]);
                if (object.status == 1) {
                    break;
                }
                object.status = 1; // mark as visited

                if (object.objectType.equals("I")) {
                    ArrayList<Item> neighbors = getItemNeighbours((Item) object);

                    //System.out.println(object.x + "," + object.y + ": " + neighbors);
                    
                    // check neighborhood items size
                    if (neighbors.size() < clusterMin - 1){
                        // mark P as noise
                        object.status = 2;
                    } else {
                        c = new Cluster(neighbors);
                        expandCluster(object, neighbors, c);
                        clusters.add(c);
                    }
                }
            }
        }
        
        return clusters;
    }

    /* PSEUDO CODE
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
    public Cluster expandCluster(GridObject object, ArrayList<Item> unsafe_neighbors, Cluster c) {
        // add item to cluster
        c.add((Item)object);
        
        // create intermediate list
        CopyOnWriteArrayList<Item> neighbors = new CopyOnWriteArrayList<Item>(unsafe_neighbors);
        // for each neighbor point, 'item'
        for (Item item : neighbors) {
            if (item.status != 1){
                // mark visited
                item.status = 1;
                // get neighbors of neighbor
                ArrayList<Item> new_neighbors = getItemNeighbours(item);
                
                // if sizeof(NeighborPts') >= MinPts
                if (new_neighbors.size() >= clusterMin - 1){
                    neighbors.removeAll(new_neighbors);
                    neighbors.addAll(new_neighbors);
                }
            }
            // if item is not yet a member of any cluster
            if (!isInACluster(item)){
                // add item to cluster
                c.add(item);
            }
        }
        
        // restore unsafe_neighbors reference
        unsafe_neighbors.clear();
        for (Item i : neighbors)
            unsafe_neighbors.add(i);
        
        return c;
    }

    /*   NEIGHBOURS for epsilon == 1
     *   (X+1,Y+1) | (X,Y+1) | (X+1,Y+1)
     *   --------------------------------
     *     (X-1,Y) |  (X,Y)  | (X+1,Y)
     *   --------------------------------
     *   (X-1,Y-1) | (X,Y-1) | (X+1,Y-1)
     */
    /* PSEUDO CODE
     * regionQuery(P, eps)
         return all points within P's eps-neighborhood
     */
    public ArrayList<Item> getItemNeighbours(Item item) {
        ArrayList<Item> neighbours = new ArrayList();
        int size = (epsilon * 2) + 1;
        int[] xOffsets = new int[size];
        int[] yOffsets = new int[size];
        for (int i = 0, j=-epsilon; i < size; ++i, ++j){
            xOffsets[i] = j;
            yOffsets[i] = j;
        }

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
    
    Cluster(ArrayList<Item> list){
        this.list = list;
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
