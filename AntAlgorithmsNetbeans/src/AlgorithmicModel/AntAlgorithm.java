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

    private int speedOfAnts;
    private Grid grid;
    private Ant[] ants;
    private Item[] items;
    public int typeOfAnt;

    AntAlgorithm(int gridSize, int numItems, int numAnts, int antMemorySize, int typeOfA, int speed) {
        speedOfAnts = speed;
        typeOfAnt = typeOfA;
        grid = new Grid(gridSize);
        if (true) {
            if (typeOfA == 0) {
                ants = new SmartAnt[numAnts];
                for (int i = 0; i < numAnts; ++i) {
                    ants[i] = new SmartAnt(grid, antMemorySize);
                }
            } else {
                ants = new SmartLocationAnt[numAnts];
                for (int i = 0; i < numAnts; ++i) {
                    ants[i] = new SmartLocationAnt(grid, antMemorySize);
                }
            }
        } else {
            ants = new Ant[numAnts];
            for (int i = 0; i < numAnts; ++i) {
                ants[i] = new Ant(grid, antMemorySize);
            }
        }
        items = new Item[numItems];
        for (int i = 0; i < numItems; ++i) {
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
                ants[i].setX(xCoord);
                ants[i].setY(yCoord);
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

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void run(int iterations) {
        //grid.printGrid();
        //int resolution = (int) iterations / 10;

        // for each iteration
        for (int i = 0; i < iterations; i++) {
            // for each ant
            for (int j = 0; j < ants.length; j++) {
                // get valid move position
                int[] pos = ants[j].getValidMove();
                ants[j].move(pos);
            }
        }
    }

    public void run(int iterations, int resolution) {
        // for each iteration
        for (int i = 0; i < iterations; i++) {
            // print grid at some resolution
//            if (i % resolution == 0) {
//                System.out.println("AFTER "+i+" ITERATIONS:");
//                grid.printGrid();
//                System.out.println();
//            }
//            if (i == iterations -1 && Math.random() < 0.005) {
//                System.out.println("AFTER "+i+" ITERATIONS:");
//                grid.printGrid();
//                System.out.println();
//            }
            // for each ant
            for (int j = 0; j < ants.length; j++) {
                for (int k = 0; k < speedOfAnts; k++) {
                    // get valid move position
                    int[] pos = ants[j].getValidMove();
                    ants[j].move(pos);
                }
            }
        }
    }
}
