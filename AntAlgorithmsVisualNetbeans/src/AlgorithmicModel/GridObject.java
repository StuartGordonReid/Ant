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
    public int status = 0; //indicates 0 for unvisted, 1 for visited, 2 for noise

    GridObject(Grid g) {
        grid = g;
        objectType = "E";
    }

    GridObject(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.objectType = type;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
