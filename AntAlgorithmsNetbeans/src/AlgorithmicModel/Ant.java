/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class Ant extends GridObject {
    
    public boolean gotItem   = false;
    
    Ant(Grid g) {
        super(g);
        this.objectType = "A";
    }
    
    Ant(int x, int y) {
        super(x,y,"A");
        this.x = x;
        this.y = y;
    }

    public void move() {
        int new_x = 0;
        int new_y = 0;
        boolean validMove = false;

        while (validMove == false) {
            new_x = new_y = 0;
            int chooser = (int)(1 + Math.random() * 4);
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
        
        // save position we're going to, before overwriting it!
        GridObject oldPos = (GridObject) grid.getGrid()[new_x][new_y];
        
        // update "from position" on grid
        if (this.objectType == "B"){
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
        if (!gotItem && oldPos.getObjectType().equals("I")){
            pickup(x, y);
        } else if (gotItem){
            drop(x, y);
        }
    }
    
    private void drop(int xCoord, int yCoord) {
        if (newDropProbability() >= Math.random()){
            // drop item
            this.objectType = "A";
            this.gotItem = false;
        }
    }
    
    private void pickup(int xCoord, int yCoord) {
        if (newPickupProbability() >= Math.random()){
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
        return 0.7;
    }
    
    private double newDropProbability() {
        // calculate drop probability
        return 5.0;
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
        if (grid.getObjectType(xCoord, yCoord).equals("A") || grid.getObjectType(xCoord, yCoord).equals("B"))
            valid = false;
        
        return valid;
    }
}
