/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class AntAlgorithm {
    
    int gridSize, numItems, numAnts;
    Grid grid;
    Item[] items;
    Ant[] ants;
    
    AntAlgorithm(int gS, int nI, int nA) {
        gridSize = gS;
        grid = new Grid(gridSize);
        
        numItems = nI;
        items = new Item[numItems];
        for(int i=0; i<numItems; i++) {
            items[i] = new Item();
        }

        numAnts = nA;
        ants = new Ant[numAnts];
        for(int i=0; i<numAnts; i++) {
            ants[i] = new Ant();
        }
    }
    
    public void initAntAlgorithm() {
        initializeGrid();
        initializeAnts();
    }
    
    public void initializeGrid() throws NullPointerException {
        int count = 0;
        int itemsToPlace = numItems;
        
        while(itemsToPlace != 0) {
            //Generate random X and Y coordinate for items
            int xCoord = (int)(Math.random() * gridSize);
            int yCoord = (int)(Math.random() * gridSize);
            
            if(grid.getObjectType(xCoord, yCoord).equals("Error")) {
                grid.placeObject(items[count], xCoord, yCoord);
                items[count].setLocationX(xCoord);
                items[count].setLocationY(yCoord);
                itemsToPlace--;
                count++;
            } 
        }
    }
    
    public void initializeAnts() throws NullPointerException {
        int count = 0;
        int antsToPlace = numAnts;
        
        while(antsToPlace != 0) {
            //Generate random X and Y coordinate for items
            int xCoord = (int)(Math.random() * gridSize);
            int yCoord = (int)(Math.random() * gridSize);
            
            if(grid.getObjectType(xCoord, yCoord).equals("Error")) {
                grid.placeObject(ants[count], xCoord, yCoord);
                ants[count].setLocationX(xCoord);
                ants[count].setLocationY(yCoord);
                antsToPlace--;
                count++;
            } 
        }
    }
    
    public void printGrid() {
        for(int i=0; i<gridSize; i++) {
            System.out.println();
            for(int j=0; j<gridSize; j++) {
                if(grid.getObjectType(i, j).equals("class AlgorithmicModel.Ant")) {
                    System.out.print("A ");
                } else if(grid.getObjectType(i, j).equals("class AlgorithmicModel.Item")) {
                    System.out.print("I ");
                } else {
                    System.out.print("E ");
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Ant[] getAnts() {
        return ants;
    }

    public void setAnts(Ant[] ants) {
        this.ants = ants;
    }
}
