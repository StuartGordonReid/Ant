/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.ArrayList;

/**
 *
 * @author stuart
 */
public class Simulator {

    public static void main(String args[]) {

        int gridSize = 50;
        int iterations = 100000;
        int numitems = (int) (gridSize * gridSize) / 20;
        int numants = (int) numitems / 10;
        int memorysize = 0;
        int resolution = 1000;
        int minClusterSize = 3;

        System.out.println("Starting simulation with: \n"
                + "Iterations: " + iterations + "\n"
                + "Grid Size: " + gridSize + "x" + gridSize + "\n"
                + "Number of items (5% of GridSize): " + numitems + "\n"
                + "Number of ants (1 Ant: 10 Items): " + numants + "\n"
                + "Memory Size: " + memorysize);
        
        AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, numants, memorysize);
        ants.run(iterations, resolution);
        
        // DBScan the grid
//        DBScan scan = new DBScan(ants.getGrid(), minClusterSize);
//        ArrayList<Cluster> clusters = scan.scanGrid();
//        for (Cluster c : clusters){
//            System.out.println(c);
//        }
    }
}
