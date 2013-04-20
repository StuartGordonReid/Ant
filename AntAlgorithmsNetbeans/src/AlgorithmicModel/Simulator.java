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
        int iterations = 100;
        AntAlgorithm ants = new AntAlgorithm(10, 20, 5);
        
        ants.run(iterations);
    }
}
