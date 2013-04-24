/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

import java.util.LinkedList;

/**
 *
 * @author stuart
 */
public class AntMemory {
    LinkedList<Item> list;
    int size;
    
    AntMemory(int s) {
        size = s;
        list = new LinkedList();
    }
    
    public int size() {
        return list.size();
    }
    
    public void add(Item item) {
        if(list.size() > size) {
            list.remove();
        }
        list.add(item);
    }
    
    public Item get(int index) {
        return list.get(index);
    }
}
