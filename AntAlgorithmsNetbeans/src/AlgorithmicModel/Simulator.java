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
        int iterations = 150;
        AntAlgorithm ants = new AntAlgorithm(20, 35, 10);

        ants.run(iterations);
    }
}
