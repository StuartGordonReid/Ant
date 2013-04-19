/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class GridObject {

    int x;
    int y;
    String objectType;
    Grid grid;

    GridObject(Grid g) {
        grid = g;
        x = 0;
        y = 0;
        objectType = "E";
    }

    GridObject(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.objectType = type;
    }
    
    public int getLocationX() {
        return x;
    }

    public void setLocationX(int x) {
        this.x = x;
    }

    public int getLocationY() {
        return y;
    }

    public void setLocationY(int y) {
        this.y = y;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
