
class BinarySearchTree {

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree.
     *
     * @param x the item to insert. Assumes x is not already in the tree
     */
    public void insert(int x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree..
     *
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove(int x) {
        root = remove(x, root);
    }

    /**
     * Remove minimum item from the tree.
     *
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin() {
        root = removeMin(root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public int findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item or null if empty.
     */
    public int findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public int find(int x) {
        return elementAt(find(x, root));
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private int elementAt(BinaryNode t) {
        return t == null ? 0 : t.getElement();
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root. @ assumes no duplicate value is inserted
     */
    protected BinaryNode insert(int x, BinaryNode t) {
        if (t == null) {
            t = new BinaryNode(x, null, null);
        } else if (x < t.getElement()) {
            t.setLeft(insert(x, t.getLeft()));
        } else if (x > t.getElement()) {
            t.setRight(insert(x, t.getRight()));
        }
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root. assumes x is in the tree
     */
    protected BinaryNode remove(int x, BinaryNode t) {
        if (t != null) {

            if (x < t.getElement()) {
                t.setLeft(remove(x, t.getLeft()));
            } else if (x > t.getElement()) {
                t.setRight(remove(x, t.getRight()));
            } else if (t.getLeft() != null && t.getRight() != null) // Two children
            {
                t.setElement(findMin(t.getRight()).getElement());
                t.setRight(removeMin(t.getRight()));
            } else {
                t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
            }
        }
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     *
     * @param t the node that roots the tree.
     * @return the new root.
     */
    protected BinaryNode removeMin(BinaryNode t) {
        if (t != null) {
            if (t.getLeft() != null) {
                t.setLeft(removeMin(t.getLeft()));
                return t;
            } else {
                return t.getRight();
            }
        } else {
            return null;
        }
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode findMin(BinaryNode t) {
        if (t != null) {
            while (t.getLeft() != null) {
                t = t.getLeft();
            }
        }

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax(BinaryNode t) {
        if (t != null) {
            while (t.getRight() != null) {
                t = t.getRight();
            }
        }

        return t;
    }

    /**
     * Method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    protected BinaryNode find(int x, BinaryNode t) {
        while (t != null) {
            if (x < t.getElement()) {
                t = t.getLeft();
            } else if (x > t.getElement()) {
                t = t.getRight();
            } else {
                return t; // Match
            }
        }

        return null; // Not found
    }

    public BinaryNode root;
}