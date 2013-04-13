/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgorithmicModel;

/**
 *
 * @author stuart
 */
public class Simulator {
    
    public static void main(String args[]) {
        
        AntAlgorithm ants = new AntAlgorithm(15,10,22);
        ants.initAntAlgorithm();
        ants.printGrid();
    }
}
