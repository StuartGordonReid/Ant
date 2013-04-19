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
        int iterations = 50;
        AntAlgorithm ants = new AntAlgorithm(10, 50, 15);
        
        ants.getGrid().printGrid();
        
        for (int i = 0; i < iterations; ++i){
            ants.moveAnts();
            if (i % 1 == 0){
                ants.getGrid().printGrid();
                System.out.println();
            }
        }
        
        ants.getGrid().printGrid();
    }
}
