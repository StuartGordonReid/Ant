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
        int gridsize   = 100;
        int numitems   = 700;
        int numants    = 50;
        int memorysize = 0;
        AntAlgorithm ants = new AntAlgorithm(gridsize, numitems, numants, memorysize);

        ants.run(iterations);
    }
}
