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
public class ClusterAnalyzer {

    ClusterF clusters;
    Grid grid;
    int numberOfClusters;
    ArrayList<Integer> clusterSizes; //Size of each cluster
    ArrayList<Double> intraClusterDistances; //Density of each cluster
    Double interClusterDistance; //Distances across all clusters

    ClusterAnalyzer(ClusterF c, Grid g) {
        clusters = c;
        //analyzeClusters(clusters);
    }

    public void analyzeClusters() {
        for (int i = 0; i < clusters.list.size(); i++) {
            clusterSizes.add(clusters.list.get(i).size());
            intraClusterDistances.add(calcIntraClusterDistances(clusters.list.get(i)));
        }
        interClusterDistance = calcInterClusterDistance();
    }

    public double calcIntraClusterDistances(ArrayList<Item> cluster) {
        return 0.0;
    }

    public double calcInterClusterDistance() {
        return 0.0;
    }

    public ArrayList<Double> getIntraClusterDistances() {
        return intraClusterDistances;
    }

    public Double getInterClusterDistances() {
        return interClusterDistance;
    }

    public String exportResults() {
        String results = "";
        results += "Number of clusters: " + numberOfClusters + "\n";
        results += "Inter cluster distance: " + interClusterDistance + "\n";
        results += "<Cluster>,<Items in cluster>,<Intra cluster distance>" + "\n";
        for (int i = 0; i < numberOfClusters; i++) {
            results += "Cluster " + i + ", ";
            results += clusterSizes.get(i) + ", ";
            results += intraClusterDistances.get(i) + "\n";
        }
        results += "\n";
        return results;
    }
}
