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
    
    Ant(Grid g) {
        super(g);
        this.objectType = "A";
    }
    
    Ant(int x, int y) {
        super(x,y,"A");
        locationX = x;
        locationY = y;
    }

    public void move() {
        int new_locationX = 0;
        int new_locationY = 0;
        boolean validMove = false;

        while (validMove == false) {
            new_locationX = new_locationY = 0;
            int chooser = (int)(1 + Math.random() * 4);
            System.out.println(chooser);
            System.out.println(locationX + ":" + locationY);
            switch (chooser) {
                case 1: // Move Up 1
                    new_locationY = this.locationY + 1;
                    new_locationX = this.locationX;
                    break;
                case 2: // Move Down 1
                    new_locationY = this.locationY - 1;
                    new_locationX = this.locationX;
                    break;
                case 3: // Move Right 1
                    new_locationY = this.locationY;
                    new_locationX = this.locationX + 1;
                    break;
                case 4: // Move left 1
                    new_locationY = this.locationY;
                    new_locationX = this.locationX - 1;
                    break;
            }
            validMove = validMove(new_locationX, new_locationY);
        }

        grid.getGrid()[locationX][locationY] = new GridObject(grid);
        grid.getGrid()[new_locationX][new_locationY] = this;
        
        this.locationX = new_locationX;
        this.locationY = new_locationY;
    }

    public boolean validMove(int xCoord, int yCoord) {
        boolean valid = true;
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
        return valid;
    }
}
