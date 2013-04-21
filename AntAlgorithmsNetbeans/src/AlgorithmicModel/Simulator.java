/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuart
 */
public class Simulator {

    public static void main(String args[]) {

        int numExperiments = 1;
        int gridSize = 100;
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

        for (int i = 0; i < numExperiments; i++) {
            AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, numants, memorysize);
            ants.run(iterations, resolution);
            ants.getGrid().printGrid();

            ClusterFinder cf = new ClusterFinder(ants.getGrid(),minClusterSize);
            ClusterF clusters = cf.scanGrid();
            for (int j = 0; j < clusters.list.size(); j++) {
                for (int k = 0; k < clusters.list.get(j).size(); k++) {
                    Item item = clusters.list.get(j).get(k);
                    System.out.print(item.getX()+":"+item.getY()+",");
                }
                System.out.println();
            }
        }

        //DBScan the grid
    }
}
