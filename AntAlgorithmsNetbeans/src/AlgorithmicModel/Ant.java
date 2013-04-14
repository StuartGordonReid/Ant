/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class Ant {
    
    Grid grid;
    int locationX;
    int locationY;
    
    Ant(Grid g) {
        grid = g;
    }
    
    Ant(int x, int y) {
        locationX = x;
        locationY = y;
    }
    
    public void move() {
        int new_locationY = locationY;
        int new_locationX = locationX;
        
        double rand = Math.random();//Up         Down                 Left   Right
        int direction = rand < 0.25 ? 1 : rand < 0.5 ? -1 : rand < 0.75 ? -1 : 1;
        
        if(rand > 0.5) {
            new_locationX += direction;
        } else {
            new_locationY += direction;
        }
        
        if(grid.validMove(new_locationX, new_locationY)) {
            grid.placeObject(null, locationX, locationX);
            grid.placeObject(this, new_locationX, new_locationX);
        } else {
            this.move();
        }
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
    
    
}
