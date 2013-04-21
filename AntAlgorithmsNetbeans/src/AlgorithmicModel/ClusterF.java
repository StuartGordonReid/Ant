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
public class ClusterF {

    ArrayList<ArrayList<Item>> list;

    ClusterF() {
        list = new ArrayList();
    }

    public void add(ArrayList<Item> x) {
        list.add(x);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        try {
            String s = "";
            for (int i = 0; i < list.size() - 1; ++i) {
                s += list.get(i).toString() + "; ";
            }
            s += list.get(list.size() - 1).toString();
            return s + "\n";
        } catch (Exception e) {
            return "";
        }
    }
}
