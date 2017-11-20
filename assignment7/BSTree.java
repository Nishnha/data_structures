/* Nishant Sinha
 * BSTree.java
 * Build a LinkedList based on a binary search tree
 * 27 November, 2017
 * CSc 2720
 * Bhola MW
 */

/* This program generates a random array of integers ranged [1, 99]
 * and then sorts them using a LinkedList implemented through a Binary Search Tree.
 * It then prints out the BST in sorted order
 */

import java.util.Random;
import java.util.Arrays;


/* Creates an array of 100 random ints range [1,99].
 * Adds each int in the array to a BST implemented thorugh a LinkedList
 */
public class BSTree {
    static int[] randInts = new int[100];

    public static void main(String[] args) {
        Random rand = new Random();
        BST linkedListBST = new BST();

        System.out.println("Nishant Sinha\n");

        for (int i = 0; i < randInts.length; i++) {
            randInts[i] = rand.nextInt(99) + 1; // Range [1, 99]
        }

        System.out.println("Unsorted array of integers");
        System.out.println(Arrays.toString(randInts)); // Print out the unsorted array

        for (int i = 0; i < randInts.length; i++) {
            linkedListBST.insert(randInts[i]);
        }

        System.out.println("\nBST traversed inorder");
        linkedListBST.traverseInorder();
    }
}


/* Define a node
 * The basic element of a BST.
 */
class Node {
    Node left, right;
    int value;

    public Node(int n) {
        left = null;
        right = null;
        value = n;
    }
}

/* Define a Binary Search Tree. */
class BST {
    private Node root;

    public BST() {
        root = null; // The default root node is null
    }

    // Initial function call for tail-recursive insert.
    public void insert(int value) {
        root = insert(root, value);
    }

    /* Tail-recursive insert to add a node to the BST.
     * @return the Node where value was inserted.
     */
    private Node insert(Node node, int value) {
        if (node == null)
            node = new Node(value);

        else {
            if (value <= node.value)
                node.left = insert(node.left, value);
            else
                node.right = insert(node.right, value);
        }

        return node;
    }

    /* Publically exposed method to traverse the BST */
    public void traverseInorder() {
        traverseInorder(root);
    }

    /* Prints out the left subtree recursively until it hits null.
     * Then prints out the right subtree.
     */
    private void traverseInorder(Node n) {
        if (n != null) {
            traverseInorder(n.left); // Traverse the left subtree until the node returns null
            System.out.print(n.value + " ");
            traverseInorder(n.right);
        }
    }
}
