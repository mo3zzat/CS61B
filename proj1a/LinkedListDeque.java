public class LinkedListDeque<T> {
    private TypeNode sentinel;
    private TypeNode lastSentinel;
    private int size;

    private class TypeNode {
        private T item;
        private TypeNode next;
        private TypeNode prev;

        public TypeNode(T i, TypeNode n, TypeNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() {
        sentinel = new TypeNode(null, null, null);
        lastSentinel = new TypeNode(null, null, sentinel);
        sentinel.next = lastSentinel;

        size = 0;
    }

    public T getRecursive(int index) {
        TypeNode currentNode = sentinel.next;
        return getRecursiveHelper(currentNode, index);
    }

    private T getRecursiveHelper(TypeNode currentNode, int currentIndex) {
        if (currentIndex < 0 || currentIndex > size || currentNode == null) {
            return null;
        } else if (currentIndex == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, currentIndex - 1);
    }

    public void addFirst(T x) {
        TypeNode newNode = new TypeNode(x, sentinel.next, sentinel);
        sentinel.next = newNode;
        newNode.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T x) {
        size += 1;
        TypeNode newNode = new TypeNode(x, lastSentinel, lastSentinel.prev);
        lastSentinel.prev = newNode;
        newNode.prev.next = newNode;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = sentinel.next;
        removedNode.next.prev = sentinel;
        sentinel.next = removedNode.next;
        return removedNode.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = lastSentinel.prev;
        lastSentinel.prev = removedNode.prev;
        removedNode.prev.next = lastSentinel;
        return removedNode.item;
    }

    public T get(int index) {
        TypeNode currentNode = sentinel.next;
        if (index >= size) {
            return null;
        }
        while (index > 0) {
            currentNode = currentNode.next;
            index--;
        }
        return currentNode.item;
    }


    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TypeNode currentNode = sentinel.next;
        if (currentNode != null) {
            return;
        }
        while (currentNode != lastSentinel) {
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;

        }
        System.out.println();
    }
}
