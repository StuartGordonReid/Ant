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
            System.out.println(chooser);
            System.out.println(x + ":" + y);
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

        grid.getGrid()[x][y] = new GridObject(grid);
        grid.getGrid()[new_x][new_y] = this;
        
        this.x = new_x;
        this.y = new_y;
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
