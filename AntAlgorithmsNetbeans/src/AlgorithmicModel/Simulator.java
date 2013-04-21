/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuart
 */
public class Simulator {

    ArrayList<ArrayList<Result>> setOfResults;
    public int numExperiments;
    public int gridSize;
    public int iterations;
    public int numitems;
    public int numants;
    public int memorysize;
    public int resolution;
    public int minClusterSize;

    public static void main(String args[]) {
        Simulator sims = new Simulator();
        sims.runner();
    }

    public void runner() {
        setOfResults = new ArrayList();

        //SMART ANT VERSION 1
        //Constants
        numExperiments = 30;
        gridSize = 30;
        iterations = 8000;
        minClusterSize = 3;
        resolution = 1000;
        numants = (int) (gridSize * gridSize) / 100;

        //Variable - test effect of #items on board
        numitems = (int) (gridSize * gridSize) / 20;
        runMemorySims();
        numitems = (int) (gridSize * gridSize) / 10;
        runMemorySims();
        numitems = (int) (gridSize * gridSize) / 5;
        runMemorySims();
        
        printResults();
        setOfResults = new ArrayList();
        
        //New constant
        numitems = (int) (gridSize * gridSize) / 10;

        //Variable - test effect of #ants on board
        numants = (int) numitems / 20;
        runMemorySims();
        numants = (int) numitems / 10;
        runMemorySims();
        numants = (int) numitems / 5;
        runMemorySims();

        printResults();
        setOfResults = new ArrayList();

        //SMART ANT VERSION 2    
    }

    public void runMemorySims() {
        int[] testMemorySize = {0, 1, 10, 100};
        for (int i = 0; i < testMemorySize.length; i++) {
            memorysize = testMemorySize[i];
            runSims();
        }
    }

    public void runSims() {
        String params = "\nStarting ant algorithm: "
                //+ "Iterations: " + iterations + "\n"
                + " Grid Size- " + gridSize + "x" + gridSize + 
                " # items- " + numitems + 
                " # ants- " + numants + 
                " Memory- " + memorysize;

        ArrayList<Result> results = new ArrayList();

        for (int i = 0; i < numExperiments; i++) {
            //System.out.println("Starting simulation " + i);

            AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, numants, memorysize);
            ants.run(iterations, resolution);

            ClusterFinder cf = new ClusterFinder(ants.getGrid(), minClusterSize);
            ClusterF clusters = cf.scanGrid();

            ClusterAnalyzer analyzer = new ClusterAnalyzer(clusters, ants.getGrid());
            analyzer.analyzeClusters();

            results.add(new Result(analyzer.numberOfClusters,
                    analyzer.getAverageClusterSize(),
                    analyzer.getAverageInterClusterDistances(),
                    analyzer.getAverageIntraClusterDistances(),
                    params));
        }

        setOfResults.add(results);
    }

    public void printResults() {
        ArrayList<Result> results;
        for (int g = 0; g < 4; g++) {
            for (int h = g; h < setOfResults.size(); h += 4) {

                results = setOfResults.get(h);

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);

                System.out.println(results.get(h).tuning);

                System.out.print("#Clusters,");
                for (int i = 0; i < results.size(); i++) {
                    System.out.print(results.get(i).numClustersFormed + ",");
                }
                System.out.println("");
                System.out.print("#Avg Size,");
                for (int i = 0; i < results.size(); i++) {
                    System.out.print(results.get(i).avgClustersSizes + ",");
                }
                System.out.println("");
                System.out.print("#Intra d,");
                for (int i = 0; i < results.size(); i++) {
                    System.out.print(df.format(results.get(i).avgIntraDistance) + ",");
                }
                System.out.println("");
                System.out.print("#Inter d,");
                for (int i = 0; i < results.size(); i++) {
                    System.out.print(df.format(results.get(i).avgInterDistance) + ",");
                }
                System.out.println("");
            }
        }
    }
}

class Result {

    //Particular experiment
    int MemorySize;
    String tuning;
    //Performance metrics
    int numClustersFormed;
    int avgClustersSizes;
    double avgInterDistance;
    double avgIntraDistance;

    Result(int n, int s, double inter, double intra, String params) {
        tuning = params;
        numClustersFormed = n;
        avgClustersSizes = s;
        avgInterDistance = inter;
        avgIntraDistance = intra;
    }
}
