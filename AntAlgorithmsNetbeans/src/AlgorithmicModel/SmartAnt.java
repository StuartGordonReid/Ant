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
public class SmartAnt extends Ant {

    SmartAnt(Grid g, int antMemorySize) {
        super(g, antMemorySize);
    }

    @Override
    public int[] getValidMove() {
        int new_x = 0;
        int new_y = 0;
        int[] move = new int[2];
        boolean validMove = false;

        int counter = 0;
        while (validMove == false) {
            double bias = Math.random();
            counter++;
            if (this.gotItem == true && bias <= 0.5 && memory.size() > 0 && counter <= 1) {
                Item closest = memory.get(0);
                for (int i = 1; i < memory.size(); i++) {
                    closest = closer(closest, memory.get(i));
                }

                int xDiff = Math.max((this.x - closest.getX()), (closest.getX() - this.x));
                int yDiff = Math.max((this.y - closest.getY()), (closest.getY() - this.y));

                int choice = (int) Math.random();
                if (choice == 1) {
                    if (xDiff > yDiff) { // move up or down
                        if (closest.getY() > y) {
                            new_y = this.y + 1;
                            new_x = this.x;
                        } else {
                            new_y = this.y - 1;
                            new_x = this.x;
                        }
                    } else { //move left or right
                        if (closest.getX() > x) {
                            new_y = this.y;
                            new_x = this.x + 1;
                        } else {
                            new_y = this.y;
                            new_x = this.x - 1;
                        }
                    }
                } else {
                     if (xDiff >= yDiff) { // move up or down
                        if (closest.getY() > y) {
                            new_y = this.y + 1;
                            new_x = this.x;
                        } else {
                            new_y = this.y - 1;
                            new_x = this.x;
                        }
                    } else { //move left or right
                        if (closest.getX() > x) {
                            new_y = this.y;
                            new_x = this.x + 1;
                        } else {
                            new_y = this.y;
                            new_x = this.x - 1;
                        }
                    }
                }
                validMove = validMove(new_x, new_y);

            } else {
                int chooser = (int) (1 + Math.random() * 8);
                //System.out.println(chooser);
                //System.out.println(x + ":" + y);
                switch (chooser) {
                    case 1: // Move Up 1
                        new_y = this.y + 1;
                        new_x = this.x;
                        break;
                    case 2: // Move Down 1
                        new_y = this.y - 1;
                        new_x = this.x;
                        break;
                    case 3: // Move Right 1
                        new_y = this.y;
                        new_x = this.x + 1;
                        break;
                    case 4: // Move left 1
                        new_y = this.y;
                        new_x = this.x - 1;
                        break;
                    case 5:
                        new_y = this.y + 1;
                        new_x = this.x + 1;
                        break;
                    case 6:
                        new_y = this.y + 1;
                        new_x = this.x - 1;
                        break;
                    case 7:
                        new_y = this.y - 1;
                        new_x = this.x + 1;
                        break;
                    case 8:
                        new_y = this.y - 1;
                        new_x = this.x - 1;
                        break;
                }
                validMove = validMove(new_x, new_y);
            }
        }
        //System.out.println(new_x + ":" + new_y);

        move[0] = new_x;
        move[1] = new_y;
        return move;
    }

    //Euclidian distance of two objects in memory
    public Item closer(Item one, Item two) {

        //RANDOM

        /* double random = Math.random();
         if(random > 0.5) {
         return one;
         } else {
         return two;
         }*/

        //EUCLIDIAN

        int x1 = one.getX();
        int y1 = one.getY();
        double diff1 = Math.sqrt(Math.pow((this.x - x1), 2.0) + Math.pow((this.y - y1), 2.0));

        int x2 = two.getX();
        int y2 = two.getY();
        double diff2 = Math.sqrt(Math.pow((this.x - x2), 2.0) + Math.pow((this.y - y2), 2.0));

        if (diff1 > diff2) {
            return two;
        } else {
            return one;
        }
    }
}
