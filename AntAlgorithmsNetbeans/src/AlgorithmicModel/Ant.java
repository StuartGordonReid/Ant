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

    public void move(int[] move) {
//        int new_x = 0;
//        int new_y = 0;
//        boolean validMove = false;
//
//        while (validMove == false) {
//            new_x = new_y = 0;
//            int chooser = (int)(1 + Math.random() * 4);
//            //System.out.println(chooser);
//            //System.out.println(x + ":" + y);
//            switch (chooser) {
//                case 1: // Move Up 1
//                    new_y = this.y + 1;
//                    new_x = this.x;
//                    break;
//                case 2: // Move Down 1
//                    new_y = this.y - 1;
//                    new_x = this.x;
//                    break;
//                case 3: // Move Right 1
//                    new_y = this.y;
//                    new_x = this.x + 1;
//                    break;
//                case 4: // Move left 1
//                    new_y = this.y;
//                    new_x = this.x - 1;
//                    break;
//            }
//            validMove = validMove(new_x, new_y);
//        }
        int new_x = move[0];
        int new_y = move[1];
        
        // save position we're going to, before overwriting it!
        //GridObject oldPos = (GridObject) grid.getGrid()[new_x][new_y];

        // update "from position" on grid
        if (this.objectType == "B") {
            //ant leaves an item behind
            grid.getGrid()[x][y] = new Item(grid);
        } else {
            grid.getGrid()[x][y] = new GridObject(grid);
        }
        // update "to position" on grid
        grid.getGrid()[new_x][new_y] = this;

        // update ants internal position
        this.x = new_x;
        this.y = new_y;

        // pickup if moving onto an item
//        if (!gotItem && oldPos.getObjectType().equals("I")){
//            pickup();
//        } else if (gotItem){
//            drop();
//        }
    }

    public void drop() {
        if (newDropProbability() >= Math.random()) {
            // drop item
            this.objectType = "A";
            this.gotItem = false;
        }
    }

    public void pickup() {
        if (newPickupProbability() >= Math.random()) {
            // pickup item
            System.out.println("Ant " + x + "," + y + " Picked up item");
            gotItem = true;
        } else {
            // update grid position!
            this.objectType = "B";
        }
    }

    private double newPickupProbability() {
        // calculate pickup probability
        int denominator = getItemsSurroundingAnt();
        return 0.7;
    }

    private double newDropProbability() {
        // calculate drop probability
        int denominator = getItemsSurroundingAnt();
        return 5.0;
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
