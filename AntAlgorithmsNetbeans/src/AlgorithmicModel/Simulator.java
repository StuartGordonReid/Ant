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
    ArrayList<String> simulations;
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
        simulations = new ArrayList();

        //Constants
        numExperiments = 10;
        gridSize = 30;
        iterations = 8000;
        minClusterSize = 3;
        resolution = 1000;
        numitems = (int) (gridSize * gridSize) / 10;

        //SMART ANT VERSION 1
        System.out.println("Experiment 1 - {Ants:Items} memory ant");

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
        System.out.println("\nExperiment 1 - {Ants:Items} memory location ant");

    }

    public void runMemorySims() {
        int[] testMemorySize = {0, 1, 10, 100};
        for (int i = 0; i < testMemorySize.length; i++) {

            memorysize = testMemorySize[i];

            String params = "\n{Sim:"
                    //+ "Iterations: " + iterations + "\n"
                    + " #I=" + numitems
                    + " #A=" + numants
                    + " Mem=" + memorysize + "}, ";
            simulations.add(params);
            
            runSims();
        }
    }

    public void runSims() {
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
                    analyzer.getAverageIntraClusterDistances()));
        }

        setOfResults.add(results);
    }

    public void printResults() {
        ArrayList<Result> results;
        for (int g = 0; g < 4; g++) {
            System.out.println(simulations.get(g));
            for (int h = g; h < setOfResults.size(); h += 4) {

                results = setOfResults.get(h);

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);

                int avgClusters = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgClusters += results.get(i).numClustersFormed;
                }
                avgClusters = avgClusters / results.size();
                System.out.print(avgClusters + ",");

                int avgSize = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgSize += results.get(i).avgClustersSizes;
                }
                avgSize = avgSize / results.size();
                System.out.print(avgSize + ",");

                double avgIntraDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgIntraDistance += results.get(i).avgIntraDistance;
                }
                avgIntraDistance = avgIntraDistance / results.size();
                System.out.print(df.format(avgIntraDistance) + ",");

                double avgInterDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgInterDistance += results.get(i).avgInterDistance;
                }
                avgInterDistance = avgInterDistance / results.size();
                System.out.print(df.format(avgInterDistance) + ",");
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

    Result(int n, int s, double inter, double intra) {
        numClustersFormed = n;
        avgClustersSizes = s;
        avgInterDistance = inter;
        avgIntraDistance = intra;
    }
}
