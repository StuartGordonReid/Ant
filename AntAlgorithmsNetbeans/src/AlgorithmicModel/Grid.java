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

    private GridObject[][] grid;
    private int gridSize;

    Grid(int gS) {
        gridSize = gS;
        grid = new GridObject[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GridObject(this);
            }
        }
    }

    public String getObjectType(int xCoord, int yCoord) {
        try {
            String type = grid[xCoord][yCoord].getObjectType();
            return type;
        } catch (Exception e) {
            return "E";
        }
    }

    public String printStringGrid() {
        String output = "";
        for (int x = 0; x < gridSize; x++) {
            output+="\n";
            for (int y = 0; y < gridSize; y++) {
                if(grid[x][y].getObjectType().equals("E")) {
                    output += ". ";
                } else {
                    output += grid[x][y].getObjectType() + " ";
                }
            }
        }        
        return output;
    }
    
    public void printGrid() {
        for (int x = 0; x < gridSize; x++) {
            System.out.println("");
            for (int y = 0; y < gridSize; y++) {
                if (gridSize <= 10){
                    if (y == 0)
                        System.out.print(x + " ");
                }
                if(grid[x][y].getObjectType().equals("E")) {
                    System.out.print("." + " ");
                } else {
                    System.out.print(grid[x][y].getObjectType() + " ");
                }
            }
        }
        System.out.println();
    }
    
    public void unvistAll(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].status = 0;
            }
        }
    }

    public Object[][] getGrid() {
        return grid;
    }

    public void setGrid(GridObject[][] grid) {
        this.grid = grid;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
}
