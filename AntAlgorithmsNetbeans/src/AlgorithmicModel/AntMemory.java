

package AlgorithmicModel;

import java.util.LinkedList;

public class AntMemory<E> extends LinkedList<E> {

    public int size;

    public AntMemory(int size) {
        this.size = size;
    }

    @Override
    public boolean add(E object) {
        boolean added = super.add(object);
        while (added && size() > size) {
            super.remove();
        }
        return added;
    }
}