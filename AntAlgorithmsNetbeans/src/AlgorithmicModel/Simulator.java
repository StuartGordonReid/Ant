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
        int iterations = 100000;
        AntAlgorithm ants = new AntAlgorithm(100, 100, 15);

        ants.run(iterations,500);
    }
}
