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

    AntAlgorithm(int gridSize, int numItems, int numAnts, int antMemorySize, int typeOfA, int speed) {
        grid = new Grid(gridSize);
        ants = new Ant[numAnts];
        for (int i = 0; i < numAnts; ++i) {
            ants[i] = new Ant(grid, antMemorySize);
        }
        items = new Item[numItems];
        for (int i = 0; i < numItems; ++i) {
            items[i] = new Item(grid);
        }
        placeAnts(numAnts);
        placeItems(numItems);
    }

    private void placeAnts(int numAnts) throws NullPointerException {
        for (int i = 0; i < numAnts; i++) {
            int xCoord = (int) (Math.random() * grid.getGridSize());
            int yCoord = (int) (Math.random() * grid.getGridSize());
            if (grid.getObjectType(xCoord, yCoord).equals("E")) {
                grid.getGrid()[xCoord][yCoord] = ants[i];
                ants[i].setX(xCoord);
                ants[i].setY(yCoord);
            } else {
                i--;
            }
        }
    }

    private void placeItems(int numItems) throws NullPointerException {
        for (int i = 0; i < numItems; i++) {
            int xCoord = (int) (Math.random() * grid.getGridSize());
            int yCoord = (int) (Math.random() * grid.getGridSize());
            if (grid.getObjectType(xCoord, yCoord).equals("E")) {
                grid.getGrid()[xCoord][yCoord] = items[i];
                items[i].setX(xCoord);
                items[i].setY(yCoord);
            } else {
                i--;
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void run(int iterations, int resolution) {
        // for each iteration
        for (int i = 0; i <= iterations; i++) {
            // for each ant
            for (int j = 0; j < ants.length; j++) {
                int[] pos = ants[j].getValidMove();
                ants[j].move(pos);
            }
        }
    }
}
