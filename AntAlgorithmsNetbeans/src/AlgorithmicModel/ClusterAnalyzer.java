/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.text.DecimalFormat;
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
        clusterSizes = new ArrayList();
        intraClusterDistances = new ArrayList();

        //analyzeClusters(clusters);
    }

    public void analyzeClusters() {
        numberOfClusters = clusters.list.size();
        for (int i = 0; i < numberOfClusters; i++) {
            clusterSizes.add(clusters.list.get(i).size());
            intraClusterDistances.add(calcIntraClusterDistances(clusters.list.get(i)));
        }
        interClusterDistance = calcInterClusterDistance();
    }

    public double calcIntraClusterDistances(ArrayList<Item> cluster) {
        double intraClusterDistance = 0.0;
        //For each item in cluster
        for (int i = 0; i < cluster.size() - 1; i++) {
            for (int j = 1; j < cluster.size(); j++) {
                intraClusterDistance += Math.pow(Math.abs(distance(cluster.get(i), cluster.get(j))), 2.0);
            }
        }
        return intraClusterDistance / cluster.size();
    }

    public double distance(Item one, Item two) {
        int x1 = one.getX();
        int x2 = two.getX();
        int y1 = one.getY();
        int y2 = two.getY();
        double distance = Math.sqrt(Math.pow((x1 - x2), 2.0) + Math.pow((y1 - y2), 2.0));
        return distance;
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

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        String results = "";
        results += "\nNumber of clusters, " + numberOfClusters + "\n";
        results += "Inter cluster distance, " + interClusterDistance + "\n\n";
        results += "<Cluster>, <Items in cluster>, <Intra cluster distance>, <cluster contents>" + "\n\n";
        for (int i = 0; i < numberOfClusters; i++) {
            if(i<10){
                results += "Cluster0" + i + ", ";
            } else {
                results += "Cluster" + i + ", ";
            }
            results += clusterSizes.get(i) + ",\t";
            results += df.format(intraClusterDistances.get(i)) + ", ";
            results += "\t";
            for (int j = 0; j < clusters.list.get(i).size(); j++) {
                Item item = clusters.list.get(i).get(j);
                results += item.getX()+":"+item.getY()+",";
            }
            results += "\n";
        }
        results += "\n";
        return results;
    }
}
