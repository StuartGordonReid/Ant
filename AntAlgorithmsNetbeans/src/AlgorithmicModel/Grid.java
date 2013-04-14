/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class Grid {

    Object[][] grid;
    int gridSize;

    Grid(int gS) {
        gridSize = gS;
        grid = new Object[gridSize][gridSize];
    }

    public void placeObject(Object obj, int xCoord, int yCoord) {
        grid[xCoord][yCoord] = obj;
    }

    public String getObjectType(int xCoord, int yCoord) {
        try {
            return "" + grid[xCoord][yCoord].getClass();
        } catch (ArrayIndexOutOfBoundsException oobe) {
            System.out.println("Array out of bounds exception");
            return "Error";
        } catch (Exception err) {
            return "Error";
        }
    }

    public boolean validMove(int xCoord, int yCoord) {
        boolean valid = true;
        if (xCoord < 0) {
            valid = false;
        }
        if (yCoord < 0) {
            valid = false;
        }
        if (xCoord >= gridSize) {
            valid = false;
        }
        if (yCoord >= gridSize) {
            valid = false;
        }
        return valid;
    }
}
