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
    public int version;
    public int speedOfAnts;

    public static void main(String args[]) {
        Simulator sims = new Simulator();
        sims.runner();
    }

    public void runner() {
        //Constants

        speedOfAnts = 1;
        numExperiments = 25;
        gridSize = 35;
        iterations = 700;
        minClusterSize = 5;
        resolution = 50000;
        numitems = (int) (gridSize * gridSize) / 20;
        version = 0;

        AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, 5, 0, 0, 1);
        ants.getGrid().printGrid();
        ants.run(10000, resolution);
        ants.getGrid().printGrid();
        /*
         //EXPERIMENT 1
         //================================================================================
         //---------------------------------------------------------------------------------
         //SMART ANT VERSION 1
         version = 0;
         System.out.println("Experiment 1 - {Ants:Items} memory ant");
         setOfResults = new ArrayList();
         simulations = new ArrayList();

         //Variable - test effect of #ants on board
         experimentOne();
         printResultsAntRatio();

         //---------------------------------------------------------------------------------
         //SMART ANT VERSION 2   

         version = 1;
         System.out.println("\nExperiment 1 - {Ants:Items} memory location ant");
         setOfResults = new ArrayList();
         simulations = new ArrayList();

         experimentOne();
         printResultsAntRatio();

         //EXPERIMENT 2
         //================================================================================
         //---------------------------------------------------------------------------------
         //SMART ANT VERSION 1

         //New constant value
         numants = (int) numitems / 10;

         version = 0;
         System.out.println("\nExperiment 2 - {Speed of Ants} memory ant");
         setOfResults = new ArrayList();
         simulations = new ArrayList();

         experimentTwo();
         printResultsSpeed();

         //---------------------------------------------------------------------------------
         //SMART ANT VERSION 2   

         version = 1;
         System.out.println("\nExperiment 2 - {Speed of Ants} memory location ant");
         setOfResults = new ArrayList();
         simulations = new ArrayList();

         experimentTwo();
         printResultsSpeed();
         */
    }

    public void experimentOne() {
        numants = (int) numitems / 21;
        runMemorySims();
        numants = (int) numitems / 18;
        runMemorySims();
        numants = (int) numitems / 15;
        runMemorySims();
        numants = (int) numitems / 12;
        runMemorySims();
        numants = (int) numitems / 9;
        runMemorySims();
        numants = (int) numitems / 6;
        runMemorySims();
        numants = (int) numitems / 3;
        runMemorySims();
    }

    public void experimentTwo() {
        speedOfAnts = 1;
        runMemorySims();
        speedOfAnts = 2;
        runMemorySims();
        speedOfAnts = 3;
        runMemorySims();
        speedOfAnts = 4;
        runMemorySims();
    }

    public void runMemorySims() {
        int[] testMemorySize = {0, 1, 10, 100};
        for (int i = 0; i < testMemorySize.length; i++) {

            memorysize = testMemorySize[i];

            String params = "\n{Sim:"
                    //+ "Iterations: " + iterations + "\n"
                    + " #I=" + numitems
                    + " Mem=" + memorysize + "}, ";
            simulations.add(params);

            runSims();
        }
    }

    public void runSims() {
        ArrayList<Result> results = new ArrayList();

        for (int i = 0; i < numExperiments; i++) {
            //System.out.println("Starting simulation " + i);

            AntAlgorithm ants = new AntAlgorithm(gridSize, numitems, numants, memorysize, version, speedOfAnts);
            ants.run(iterations, resolution);

            ClusterFinder cf = new ClusterFinder(ants.getGrid(), minClusterSize);
            ClusterF clusters = cf.scanGrid();

            ClusterAnalyzer analyzer = new ClusterAnalyzer(clusters, ants.getGrid());
            analyzer.analyzeClusters();

            results.add(new Result(analyzer.numberOfClusters,
                    analyzer.getClusteredItems(),
                    analyzer.getAverageInterClusterDistances(),
                    analyzer.getAverageIntraClusterDistances(),
                    numitems, numants, speedOfAnts));
        }

        setOfResults.add(results);
    }

    public void printResultsSpeed() {
        ArrayList<Result> results;
        for (int g = 0; g < 4; g++) {
            System.out.println(simulations.get(g));
            for (int h = g; h < setOfResults.size(); h += 4) {

                results = setOfResults.get(h);

                System.out.print(results.get(0).speed + ",\t");

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);

                int avgClusters = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgClusters += results.get(i).numClustersFormed;
                }
                avgClusters = avgClusters / results.size();
                System.out.print(avgClusters + ", ");

                int avgSize = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgSize += results.get(i).avgClustersSizes;
                }
                avgSize = avgSize / results.size();
                System.out.print(avgSize + ", ");

                double avgIntraDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgIntraDistance += results.get(i).avgIntraDistance;
                }
                avgIntraDistance = avgIntraDistance / results.size();
                System.out.print(df.format(avgIntraDistance) + ", ");

                double avgInterDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgInterDistance += results.get(i).avgInterDistance;
                }
                avgInterDistance = avgInterDistance / results.size();
                System.out.print(df.format(avgInterDistance) + ", ");
                System.out.println("");
            }
        }
    }

    public void printResultsAntRatio() {
        ArrayList<Result> results;
        for (int g = 0; g < 4; g++) {
            System.out.println(simulations.get(g));
            for (int h = g; h < setOfResults.size(); h += 4) {

                results = setOfResults.get(h);

                System.out.print(results.get(0).numAnts + ":" + results.get(0).numItems + ",\t");

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);

                int avgClusters = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgClusters += results.get(i).numClustersFormed;
                }
                avgClusters = avgClusters / results.size();
                System.out.print(avgClusters + ", ");

                int avgSize = 0;
                for (int i = 0; i < results.size(); i++) {
                    avgSize += results.get(i).avgClustersSizes;
                }
                avgSize = avgSize / results.size();
                System.out.print(avgSize + ", ");

                double avgIntraDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgIntraDistance += results.get(i).avgIntraDistance;
                }
                avgIntraDistance = avgIntraDistance / results.size();
                System.out.print(df.format(avgIntraDistance) + ", ");

                double avgInterDistance = 0.0;
                for (int i = 0; i < results.size(); i++) {
                    avgInterDistance += results.get(i).avgInterDistance;
                }
                avgInterDistance = avgInterDistance / results.size();
                System.out.print(df.format(avgInterDistance) + ", ");
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
    int numAnts;
    int numItems;
    int speed;

    Result(int n, int s, double inter, double intra, int A, int I, int sp) {
        numClustersFormed = n;
        avgClustersSizes = s;

        avgInterDistance = inter;
        avgIntraDistance = intra;

        numAnts = A;
        numItems = I;
        speed = sp;
    }
}
