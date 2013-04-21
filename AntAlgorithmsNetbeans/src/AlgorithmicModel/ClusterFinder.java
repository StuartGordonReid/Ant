/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author stuart
 */
public class ClusterFinder {

    Grid grid;
    int minSize;

    ClusterFinder(Grid g, int min) {
        grid = g;
        minSize = min;
    }

    public ClusterF scanGrid() {

        //Find all items in the grid
        ArrayList<Item> allItems = new ArrayList();
        for (int x = 0; x < grid.getGridSize(); x++) {
            for (int y = 0; y < grid.getGridSize(); y++) {
                GridObject obj = (GridObject) grid.getGrid()[x][y];
                if (obj.getObjectType().equals("I")) {
                    allItems.add((Item) grid.getGrid()[x][y]);
                }
            }
        }

        ClusterF clusters = new ClusterF();

        //Start with the first item
        Item item;
        int scanIndex = nextItemScan(allItems);
        while (scanIndex != -1) {
            item = allItems.get(scanIndex);

            ArrayList<Item> Neighbours = findUnvisitedNeighbors(item);
            ArrayList<Item> Neighbours2 = new ArrayList();
            ArrayList<Item> Neighbours3 = new ArrayList();
            ArrayList<Item> Neighbours4 = new ArrayList();
            ArrayList<Item> Neighbours5 = new ArrayList();
            ArrayList<Item> Neighbours6 = new ArrayList();
            ArrayList<Item> Neighbours7 = new ArrayList();
            ArrayList<Item> Neighbours8 = new ArrayList();
            ArrayList<Item> Neighbours9 = new ArrayList();

            for (int i = 0; i < Neighbours.size(); i++) {
                Neighbours2.addAll(findUnvisitedNeighbors(Neighbours.get(i)));
            }
            for (int j = 0; j < Neighbours2.size(); j++) {
                Neighbours3.addAll(findUnvisitedNeighbors(Neighbours2.get(j)));
            }
            for (int k = 0; k < Neighbours3.size(); k++) {
                Neighbours4.addAll(findUnvisitedNeighbors(Neighbours3.get(k)));
            }
            for (int l = 0; l < Neighbours4.size(); l++) {
                Neighbours5.addAll(findUnvisitedNeighbors(Neighbours4.get(l)));
            }
            for (int m = 0; m < Neighbours5.size(); m++) {
                Neighbours6.addAll(findUnvisitedNeighbors(Neighbours5.get(m)));
            }
            for (int n = 0; n < Neighbours6.size(); n++) {
                Neighbours7.addAll(findUnvisitedNeighbors(Neighbours6.get(n)));
            }
            for (int o = 0; o < Neighbours7.size(); o++) {
                Neighbours8.addAll(findUnvisitedNeighbors(Neighbours7.get(o)));
            }
            for (int p = 0; p < Neighbours8.size(); p++) {
                Neighbours9 = findUnvisitedNeighbors(Neighbours8.get(p));
            }

            Neighbours.addAll(Neighbours2);
            Neighbours.addAll(Neighbours3);
            Neighbours.addAll(Neighbours4);
            Neighbours.addAll(Neighbours5);
            Neighbours.addAll(Neighbours6);
            Neighbours.addAll(Neighbours7);
            Neighbours.addAll(Neighbours8);
            Neighbours.addAll(Neighbours9);

            if (Neighbours.size() >= minSize) {
                clusters.add(Neighbours);
            }

            scanIndex = nextItemScan(allItems);
        }

        return clusters;
    }

    private int nextItemScan(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = (Item) grid.getGrid()[items.get(i).getX()][items.get(i).getY()];
            if (item.status != 1) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Item> findUnvisitedNeighbors(Item point) {
        ArrayList<Item> neighbours = new ArrayList();

        int[] xOffsets = {-1, 0, 1};
        int[] yOffsets = {-1, 0, 1};

        for (int i = 0; i < xOffsets.length; i++) {
            for (int j = 0; j < yOffsets.length; j++) {
                Item neighbour = getNeighbour(point.getX(), point.getY(), xOffsets[i], yOffsets[j]);
                if (neighbour != null) {
                    neighbours.add(neighbour);
                    Item gridItem = (Item) grid.getGrid()[neighbour.getX()][neighbour.getY()];
                    gridItem.status = 1;
                }
            }
        }

        return neighbours;
    }

    public Item getNeighbour(int x, int y, int xOffset, int yOffset) {
        try {
            GridObject gridObject = (GridObject) grid.getGrid()[x + xOffset][y + yOffset];
            if (gridObject.getObjectType().equals("I") && gridObject.status != 1) {
                return (Item) grid.getGrid()[x + xOffset][y + yOffset];
            } else {
                return null;
            }
        } catch (Exception err) {
            return null;
        }
    }
}
