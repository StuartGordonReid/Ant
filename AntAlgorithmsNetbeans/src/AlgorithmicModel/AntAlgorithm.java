/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class AntAlgorithm {

    private Grid grid;
    private Ant[] ants;
    private Item[] items;

    AntAlgorithm(int gridSize, int numItems, int numAnts) {
        grid = new Grid(gridSize);
        ants = new Ant[numAnts];
        for (int i = 0; i < numAnts; i++) {
            ants[i] = new Ant(grid);
        }
        items = new Item[numItems];
        for (int i = 0; i < numItems; i++) {
            items[i] = new Item(grid);
        }
        placeAnts(numAnts);
        placeItems(numItems);
    }

    public void placeAnts(int numAnts) throws NullPointerException {
        for (int i = 0; i < numAnts; i++) {
            int xCoord = (int) (Math.random() * grid.getGridSize());
            int yCoord = (int) (Math.random() * grid.getGridSize());
            if (grid.getObjectType(xCoord, yCoord).equals("E")) {
                grid.getGrid()[xCoord][yCoord] = ants[i];
                ants[i].setLocationX(xCoord);
                ants[i].setLocationY(yCoord);
            } else {
                i--;
            }
        }
    }

    public void placeItems(int numItems) throws NullPointerException {
        for (int i = 0; i < numItems; i++) {
            int xCoord = (int) (Math.random() * grid.getGridSize());
            int yCoord = (int) (Math.random() * grid.getGridSize());
            if (grid.getObjectType(xCoord, yCoord).equals("E")) {
                grid.getGrid()[xCoord][yCoord] = items[i];
                items[i].setLocationX(xCoord);
                items[i].setLocationY(yCoord);
            } else {
                i--;
            }
        }
    }

    public void moveAnts() {
        for (int i = 0; i < ants.length; i++) {
            ants[i].move();
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}