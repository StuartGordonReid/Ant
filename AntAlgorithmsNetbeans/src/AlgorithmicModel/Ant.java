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
public class Ant extends GridObject {

    public boolean gotItem = false;

    Ant(Grid g) {
        super(g);
        this.objectType = "A";
    }

    Ant(int x, int y) {
        super(x, y, "A");
        this.x = x;
        this.y = y;
    }

    public int[] getValidMove() {
        int new_x = 0;
        int new_y = 0;
        boolean validMove = false;

        while (validMove == false) {
            new_x = new_y = 0;
            int chooser = (int) (1 + Math.random() * 4);
            //System.out.println(chooser);
            //System.out.println(x + ":" + y);
            switch (chooser) {
                case 1: // Move Up 1
                    new_y = this.y + 1;
                    new_x = this.x;
                    break;
                case 2: // Move Down 1
                    new_y = this.y - 1;
                    new_x = this.x;
                    break;
                case 3: // Move Right 1
                    new_y = this.y;
                    new_x = this.x + 1;
                    break;
                case 4: // Move left 1
                    new_y = this.y;
                    new_x = this.x - 1;
                    break;
            }
            validMove = validMove(new_x, new_y);
        }

        // move is [x,y]
        int[] move = new int[2];
        move[0] = new_x;
        move[1] = new_y;
        return move;
    }

    /* Pseudocode:
     * -------------------------------------------------------------------------
     * Update old cell
     * -------------------------------------------------------------------------
     * If before moving the ant is currently sharing a cell with an item
     *      Make the cell a new item
     * Otherwise if the cell only contained an ant before
     *      Mark the cell as being empty
     * Update the internal reference of the ant
     * -------------------------------------------------------------------------
     * Update new cell
     * -------------------------------------------------------------------------
     * If the cell the ant is moving to contains and item
     *      Move to that cell and mark cell as having both an ant and an item
     * Otherwise if the cell the ant is moving to is empty
     *      Move to the cell and mark the cell as having just an ant
     * -------------------------------------------------------------------------
     * Perform pickup / drop
     * -------------------------------------------------------------------------
     * If the ant is carrying an item
     *      And if the cell doesn't have an items on it
     *          Try drop the item
     * If the ant it not carrying an item
     *      And if the cell has an item on it
     *          Try pickup the item
     */
    public void move(int[] move) {
        int new_x = move[0];
        int new_y = move[1];

        //If moving on from cell with both ant and item leave item and move ant
        GridObject oldPosition = (GridObject) grid.getGrid()[x][y];
        if (oldPosition.getObjectType().equals("B")) {
            grid.getGrid()[x][y] = new Item(x, y);
        } else {
            grid.getGrid()[x][y] = new GridObject(x, y, "E");
        }

        //Update the ants internal reference
        this.x = new_x;
        this.y = new_y;

        GridObject newPosition = (GridObject) grid.getGrid()[x][y];
        if (newPosition.getObjectType().equals("I")) {
            //There was an item at the block now there is an ant and an item there
            this.setObjectType("B");
            grid.getGrid()[x][y] = this;
        } else {
            //There was no item at the block now there is an ant there
            this.setObjectType("A");
            grid.getGrid()[x][y] = this;
        }

        if (this.gotItem == false) {
            //Make sure there is an item there to pickup
            if (this.getObjectType().equals("B")) {
                //Either pickup item or do nothing
                double prob_Pickup = newPickupProbability();
                if (Math.random() < prob_Pickup) {
                    this.setObjectType("A");
                    this.gotItem = true;
                    grid.getGrid()[x][y] = this;
                }
            }
        } else {
            //Make sure there isn't already an item there
            if (this.getObjectType().equals("A")) {
                //Either drop item or do nothing
                double prob_Drop = newPickupProbability();
                if (Math.random() < prob_Drop) {
                    this.setObjectType("B");
                    this.gotItem = false;
                    grid.getGrid()[x][y] = this;
                }
            }
        }
    }

    private double newPickupProbability() {
        //int lamda = getItemsSurroundingAnt();
        //return 1/(1+lamda);
        return 0.5;
    }

    private double newDropProbability() {
        //int lamda = getItemsSurroundingAnt();
        //return lamda/(1+lamda);
        return 0.5;
    }

    public int getItemsSurroundingAnt() {
        int items = 0;
        int[] xOffsets = {-1, 0, 1};
        int[] yOffsets = {-1, 0, 1};

        for (int i = 0; i < xOffsets.length; i++) {
            for (int j = 0; j < yOffsets.length; j++) {
                items += getNeighbour(xOffsets[i], yOffsets[j]);
            }
        }
        return items;
    }

    public int getNeighbour(int xOffset, int yOffset) {
        try {
            if (grid.getObjectType(x + xOffset, y + yOffset).equals("I")) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception err) {
            return 0;
        }
    }

    private boolean validMove(int xCoord, int yCoord) {
        boolean valid = true;
        // check bounds
        if (xCoord < 0) {
            valid = false;
        }
        if (yCoord < 0) {
            valid = false;
        }
        if (xCoord >= grid.getGridSize()) {
            valid = false;
        }
        if (yCoord >= grid.getGridSize()) {
            valid = false;
        }
        // detect collisions with other ants
        if (grid.getObjectType(xCoord, yCoord).equals("A") || grid.getObjectType(xCoord, yCoord).equals("B")) {
            valid = false;
        }

        return valid;
    }
}
