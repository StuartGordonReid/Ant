/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class Item extends GridObject {

    Grid g;

    Item(int x, int y) {
        super(x, y, "I");
    }

    Item(Grid g) {
        super(g);
        this.objectType = "I";
    }
    
    @Override
    public String toString(){
        return x + "," + y;
    }
}
