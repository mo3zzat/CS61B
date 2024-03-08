
public class ArrayDeque<T> {
    private int size;
    private int first;
    private int last;
    private T[] Items;

    private static final int START_SIZE = 8;

    public ArrayDeque() {
        Items = (T[]) new Object[START_SIZE];
        first = 0;
        last = 0;
    }

    public void addFirst(T x) {
        if (size == 0) {
            first = 0;
            last = 0;
            Items[0] = x;
            size++;
            return;
        }
        if (size == Items.length) {
            resizeUp(); // resize my array here
        }
        if (first == 0) {
            first = Items.length - 1;
        } else {
            first--;
        }
        Items[first] = x;
        size++;
    }

    public void addLast(T x) {
        if (size == 0) {
            first = 0;
            last = 0;
            Items[0] = x;
            size++;
            return;
        }
        if (size == Items.length) {
            resizeUp(); // resize my array here
        }
        if (last == Items.length - 1) {
            last = 0;
        } else {
            last++;
        }
        Items[last] = x;
        size++;
    }

    public T get(int index) {
        return Items[(first + index) % Items.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < Items.length; i++) {
            System.out.print(Items[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedNode = Items[first];
        Items[first] = null;
        if (first == Items.length - 1) {
            first = 0;
        } else {
            first++;
        }
        size--;
        if (size == 0) {
            first = 0;
            last = 0;
        }
        if (size < Items.length / 4) {
            resizeDown();
        }
        return removedNode;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedNode = Items[last];
        Items[last] = null;
        if (last == 0) {
            last = Items.length - 1;
        } else {
            last--;
        }
        size--;
        if (size == 0) {
            first = 0;
            last = 0;
        }
        if (size < Items.length / 4) {
            resizeDown();
        }
        return removedNode;
    }

    private void resizeUp() {
        T[] resizedArray = (T[]) new Object[Items.length * 2];
        // copy array into new array
        int sizeOfFirstCopy = Items.length - first;
        System.arraycopy(Items, first, resizedArray, 0, sizeOfFirstCopy);
        System.arraycopy(Items, 0, resizedArray, sizeOfFirstCopy, size - sizeOfFirstCopy);
        Items = resizedArray;
        first = 0;
        last = size - 1;
    }

    private void resizeDown() {
        T[] resizedArray = (T[]) new Object[Items.length / 2];
//        int sizeOfFirstCopy = Items.length - first;
        if (last < first) {
            int sizeOfFirstCopy = Items.length - first;
            System.arraycopy(Items, first, resizedArray, 0, sizeOfFirstCopy);
            System.arraycopy(Items, 0, resizedArray, sizeOfFirstCopy,
                    size - sizeOfFirstCopy);

        } else {
            System.arraycopy(Items, first, resizedArray, 0, size);
        }
        Items = resizedArray;
        first = 0;
        last = size - 1;
    }

}
