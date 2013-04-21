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
        int gridSize = 50;
        int iterations = 10000;
        int numitems = (int) (gridSize * gridSize * 0.05);
        int numants = (int) numitems / 10;
        int memorysize = 2;
        int resolution = iterations;//(int) (iterations * 0.05);
        int minClusterSize = 3;
        int epsilon = 3;//(int) (gridSize * 0.08); // % of the gridsize defines a 'neighborhood'

        System.out.println("Starting simulation with: \n===\n"
                + "Iterations: " + iterations + "\n"
                + "Grid Size: " + gridSize + "x" + gridSize + "\n"
                + "Number of items (5% of GridSize): " + numitems + "\n"
                + "Number of ants (1 Ant: 10 Items): " + numants + "\n"
                + "Memory Size: " + memorysize + "\n"
                + "Epsilon neighbourhood: " + epsilon);
        
        for (int i = 0; i < numExperiments; i++) {
            // Simulation
            AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, numants, memorysize);
            ants.run(iterations, resolution);

            //////// ClusterFinder the grid ////////
            ClusterFinder cf = new ClusterFinder(ants.getGrid(),minClusterSize);
            ClusterF clusters = cf.scanGrid();
            // Print results to console
            System.out.println("ClusterFinder Results\n===\n");
            System.out.println("Number of clusters: " + clusters.list.size() + "\n");
            for (int j = 0; j < clusters.list.size(); j++) {
                for (int k = 0; k < clusters.list.get(j).size(); k++) {
                    Item item = clusters.list.get(j).get(k);
                    System.out.print(item.getX()+":"+item.getY()+",");
                }
                System.out.println();
            }
            
            // Clear grid visits
            ants.getGrid().unvistAll();
            
            //////// DBScan the grid ////////
            DBScan scan = new DBScan(ants.getGrid(), minClusterSize, epsilon);
            ArrayList<Cluster> clusterlist = scan.scanGrid();
            // Print results to console
            System.out.println("\n\nDBScan results\n===\n");
            System.out.println("Number of clusters: " + clusterlist.size() + "\n");
            for (Cluster c : clusterlist){
                System.out.println(c);
            }
        }
    }
}
