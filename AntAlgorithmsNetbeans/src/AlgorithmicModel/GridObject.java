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

    int locationX;
    int locationY;
    String objectType;
    Grid grid;

    GridObject(Grid g) {
        grid = g;
        locationX = 0;
        locationY = 0;
        objectType = "E";
    }

    GridObject(int x, int y, String type) {
        this.locationX = x;
        this.locationY = y;
        this.objectType = type;
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
