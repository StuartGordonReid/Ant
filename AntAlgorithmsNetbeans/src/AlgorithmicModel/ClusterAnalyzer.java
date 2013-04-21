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
    int clusteredItems;
    ArrayList<Integer> clusterSizes; //Size of each cluster
    ArrayList<Double> intraClusterDistances; //Density of each cluster
    double interClusterDistance; //Distances across all clusters

    ClusterAnalyzer(ClusterF c, Grid g) {
        clusters = c;
        clusterSizes = new ArrayList();
        intraClusterDistances = new ArrayList();

        //analyzeClusters(clusters);
    }

    public int getClusteredItems() {
        int clustered = 0;
        for(int i=0; i<clusterSizes.size(); i++){
            clustered += clusterSizes.get(i);
        }
        return clustered;
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
        int distances = 0;
        //For each item in cluster
        for (int i = 0; i < cluster.size() - 1; i++) {
            for (int j = 1; j < cluster.size(); j++) {
                distances++;
                //intraClusterDistance += Math.abs(distance(cluster.get(i), cluster.get(j)));
                intraClusterDistance += Math.pow(distance(cluster.get(i), cluster.get(j)), 2.0);
            }
        }
        return intraClusterDistance / distances;
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

        double distance = 0.0;

        //For all the clusters
        for (int i = 0; i < numberOfClusters - 1; i++) {
            //Select item in the first cluster
            int select = (int) Math.random() * clusters.list.get(i).size();
            Item selected = clusters.list.get(i).get(select);

            //Find the closest item in the next cluster
            Item closest = clusters.list.get(i + 1).get(0);
            for (int j = 1; j < clusters.list.get(i + 1).size(); j++) {
                //If the distance between the next item in the next cluster is closer, update closest
                if (distance(selected, closest) > distance(selected, clusters.list.get(i + 1).get(j))) {
                    closest = clusters.list.get(i + 1).get(j);
                }
            }

            //Add the squared distance between those two items
            distance += Math.pow(distance(selected, closest), 2.0);
            //distance += distance(selected, closest);
        }

        return distance;
    }

    public ArrayList<Double> getIntraClusterDistances() {
        return intraClusterDistances;
    }

    public double getInterClusterDistances() {
        return interClusterDistance;
    }

    public double averageDouble(ArrayList<Double> source) {
        if (!source.isEmpty()) {
            double average = 0.0;
            for (int i = 0; i < source.size(); i++) {
                average += source.get(i);
            }
            return average / source.size();
        } else {
            return 0;
        }
    }

    public int averageInt(ArrayList<Integer> source) {
        if (!source.isEmpty()) {
            int average = 0;
            for (int i = 0; i < source.size(); i++) {
                average += source.get(i);
            }
            return average / source.size();
        } else {
            return 0;
        }
    }

    public String exportResults() {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        String results = "";
        results += "\nNumber of clusters, " + numberOfClusters + "\n";
        results += "Inter cluster distance, " + df.format(interClusterDistance) + "\n\n";
        //results += "<Cluster>, <Items in cluster>, <Intra cluster distance>, "
        //        + "<cluster contents>" + "\n\n";
        for (int i = 0; i < numberOfClusters; i++) {
            if (i < 10) {
                results += "Cluster0" + i + ", ";
            } else {
                results += "Cluster" + i + ", ";
            }
            results += clusterSizes.get(i) + ",\t";
            results += df.format(intraClusterDistances.get(i)) + ", ";
            results += "\t";
            for (int j = 0; j < clusters.list.get(i).size(); j++) {
                Item item = clusters.list.get(i).get(j);
                results += item.getX() + ":" + item.getY() + ",";
            }
            results += "\n";
        }
        results += "\nAverage00, " + averageInt(clusterSizes) + ",\t"
                + df.format(averageDouble(intraClusterDistances)) + "\n";
        results += "\n";
        return results;
    }

    public int getAverageClusterSize() {
        return averageInt(clusterSizes);
    }

    public double getAverageIntraClusterDistances() {
        return averageDouble(intraClusterDistances);
    }

    public double getAverageInterClusterDistances() {
        return interClusterDistance;
    }
}
