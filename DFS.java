import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

class Node<T extends Comparable<?>> {
    Node<T> left = null;
    Node<T> right = null;
    T data;

    public Node(T val) {
        this.data = val;
    }


   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }




    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("rawtypes")
        Node other = (Node) obj;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }




    @Override
    public String toString() {
        return "Node<Integer>[left=" + left + ", right=" + right + ", data=" + data + "]";
    }
}

class NodePair {
    Node<Integer> head = null;
    Node<Integer> tail = null;

    public NodePair(Node<Integer> head, Node<Integer> tail) {
        this.tail = tail;
        this.head = head;
    }

    public void traverse() {
        Node<Integer> ptr = head;
        while (ptr != null) {
            System.out.print(ptr.data + "->");
            ptr = ptr.right;
        }
        System.out.println();
    }

    public String toString() {
        return "H-" + head + " - " + "T-" + tail;
    }

    public Node<Integer> getHead() {
        return head;
    }

    public void setHead(Node<Integer> head) {
        this.head = head;
    }

    public Node<Integer> getTail() {
        return tail;
    }

    public void setTail(Node<Integer> tail) {
        this.tail = tail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        result = prime * result + ((tail == null) ? 0 : tail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodePair other = (NodePair) obj;
        if (head == null) {
            if (other.head != null)
                return false;
        } else if (!head.equals(other.head))
            return false;
        if (tail == null) {
            if (other.tail != null)
                return false;
        } else if (!tail.equals(other.tail))
            return false;
        return true;
    }
}

public class DFS {
    Node<Integer> root;

    public static void main(String[] args) {
        DFS d = new DFS();
        d.root = new Node<Integer>(10);
        d.root.left = new Node<Integer>(7);

        d.root.left.left = new Node<Integer>(5);
        d.root.right = new Node<Integer>(16);
        d.root.right.left = new Node<Integer>(11);
        d.root.right.right = new Node<Integer>(18);

        System.out.println("In order");
        d.travInorderNoRec();
        System.out.println("Post order");
        d.travPostNoRec();
        System.out.println("Pre Order");
        d.travPreNoRec();
        System.out.println("CPU - " + System.currentTimeMillis());
        System.out.println("IsBalaced " + d.isBalanacedNlogN(d.root));
        System.out.println("CPU - " + System.currentTimeMillis());
        System.out.println("IsBalaced " + d.isBalanaced(d.root));
        System.out.println("CPU - " + System.currentTimeMillis());
        System.out.println("CheckHeight " + d.checkHieght(d.root));
        System.out.println("CPU - " + System.currentTimeMillis());
        System.out.println("Cycle " + d.cycleDetection(d.root));
        System.out.println("BST " + d.checkBST(d.root));
        System.out.println("BSTMM " + d.checkBSTMM(d.root));
        System.out.println("Inorder succ " + d.inorderSucc(d.root));
        d.addVertex(0);
        d.addEdge(0, 1);
        d.addEdge(0, 3);

        d.addVertex(1);
        d.addEdge(1, 0);
        d.addVertex(2);
        d.addEdge(2, 0);
        d.addEdge(1, 2);
        d.addEdge(2, 3);
        d.addEdge(2, 1);
        d.addVertex(3);
        d.addEdge(3, 0);
        d.addEdge(3, 1);
        d.addEdge(3, 3);
        d.addVertex(4);
        d.addEdge(0, 4);
        d.addEdge(4, 4);
        d.dfs(0);
        d.containsNode(4);
        System.out.println("\nTopo-");
        System.out.println(d.topologicalSort(0));
        System.out.println("Convert to Doubly-");
        NodePair pair = d.convert(d.root);
        pair.traverse();
        // System.out.println("Convert to circular - "+d.convertToCircular(d.root));
       // BTreePrinter.printNode(d.root);
    }

    // Works in O(N) time and O(H) space; where H is the height
    int checkHieght(Node<Integer> root) {
        if (root == null)
            return -1;
        int leftHieght = checkHieght(root.left);
        if (leftHieght == Integer.MIN_VALUE)
            return Integer.MIN_VALUE; // pass error up

        int rightHieght = checkHieght(root.right);
        if (rightHieght == Integer.MIN_VALUE)
            return Integer.MIN_VALUE; // pass error up
        int heightDiff = leftHieght - rightHieght;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // pass error up
        } else {
            return Math.max(leftHieght, rightHieght) + 1;
        }
    }

    boolean isBalanaced(Node<Integer> root) {
        return checkHieght(root) != Integer.MIN_VALUE;
    }

    int getHeight(Node<Integer> root) {
        if (root == null)
            return -1;
        // System.out.println("Height" +
        // Math.max(getHeight(root.left),getHeight(root.right))+1);
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    // works in nlog(n) since each Node<Integer>is touched once per
    // Node<Integer>above it
    boolean isBalanacedNlogN(Node<Integer> root) {
        if (root == null)
            return true;// base case
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {// recurse
            return isBalanacedNlogN(root.left) && isBalanacedNlogN(root.right);
        }
    }

    void pre(Node<Integer> n) {
        if (n != null) {
            visit(n);
            pre(n.left);
            pre(n.right);
        }
    }

    void post(Node<Integer> n) {
        if (n != null) {
            pre(n.left);
            pre(n.right);
            visit(n);
        }
    }

    void in(Node<Integer> n) {
        if (n != null) {

            pre(n.left);
            visit(n);
            pre(n.right);

        }
    }

    void visit(Node<Integer> n) {
        System.out.println(n.data);
    }

    void travPreNoRec() {
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        Node<Integer> current = root;
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            visit(current);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    void travInorderNoRec() {
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        Node<Integer> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node<Integer> top = stack.pop();
            visit(top);
            current = top.right;
        }
    }

    void travPostNoRec() {
        Stack<Node<Integer>> stack = new Stack<>();
        Node<Integer> prev = root;
        Node<Integer> current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));
            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                visit(current);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }
    /*
     * Graphs
     */

    private Map<Integer, List<Integer>> adjVertices;

    public DFS() {
        this.adjVertices = new HashMap<Integer, List<Integer>>();
    }

    public void addVertex(int vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int src, int dest) {
        adjVertices.get(src).add(dest);
    }

    public boolean[] dfsWithoutRecursion(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;
                visit(current);
                for (int dest : adjVertices.get(current)) {
                    if (!isVisited[dest])
                        stack.push(dest);
                }
            }
        }
        return isVisited;
    }

    public boolean[] dfs(int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        return dfsRecursive(start, isVisited);
    }

    private boolean[] dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        visit(current);
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
        return isVisited;
    }

    public List<Integer> topologicalSort(int start) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        topologicalSortRecursive(start, isVisited, result);
        return result;
    }

    private void topologicalSortRecursive(int current, boolean[] isVisited, LinkedList<Integer> result) {
        isVisited[current] = true;
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                topologicalSortRecursive(dest, isVisited, result);
        }
        result.addFirst(current);
    }

    private void visit(int value) {
        System.out.print(" " + value);
    }

    // cnoverts binary tree to doubly linked list
    NodePair convert(Node<Integer> root) {
        if (root == null)
            return null;
        NodePair part1 = convert(root.left);
        NodePair part2 = convert(root.right);
        // merge(left,root, right);
        if (part1 != null) {
            concat(part1.tail, root);
        }
        if (part2 != null) {
            concat(root, part2.head);
        }
        return new NodePair(part1 == null ? root : part1.head, part2 == null ? root : part2.tail);
    }

    public static void concat(Node<Integer> x, Node<Integer> y) {
        x.right = y;
        y.left = x;
    }

    void merge(Node<Integer> left, Node<Integer> root, Node<Integer> right) {

    }

    @SuppressWarnings({ "null", "unused" })
    Node<Integer> convertToCircular(Node<Integer> root) {
        if (root == null)
            return null;
        Node<Integer> part1 = convertToCircular(root.left);
        Node<Integer> part3 = convertToCircular(root.right);
        if (part1 == null && part3 == null) {
            root.left = root;
            root.right = root;
            return root;
        }
        Node<Integer> tail3 = (part3 == null) ? null : part3.left;
        if (part3 == null) {
            concat(part3.left, root);
        } else {
            concat(part1.left, root);
        }

        if (part3 == null) {
            concat(root, part1);
        } else {
            concat(root, part3);
        }
        // join right to left
        if (part1 != null && part3 != null) {
            concat(tail3, part3);
        }
        return part1 == null ? root : part1;
    }

    boolean cycleDetection(Node<Integer> root) {
        Node<Integer> slow = root;
        Node<Integer> fast = root;
        while (fast != null && fast.right != null) {
            fast = fast.right.right;
            slow = slow.right;
            if (fast == slow)
                return true;

        }
        return false;
    }

    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public static double cosineSimilarityAdj(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node<Integer> current) {
        return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node<Integer> current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.data) {
            return true;
        }

        return value < current.data
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node<Integer> deleteRecursive(Node<Integer> current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.data) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.data) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node<Integer> root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    // check if BST using inorder traversal ,using static instead of array and
    // finding if its sorted as it cannot deal with duplicates
    // left.data<==current.data<=right.data
    // time o(N) space o(log n)
    Integer lastprinted = null;

    // inorder traversal
    boolean checkBST(Node<Integer> n) {
        if (n == null)
            return true;
        // check /recurse left
        if (!checkBST(n.left))
            return false;
        // check current
        if (lastprinted != null && n.data <= lastprinted)
            return false;
        lastprinted = n.data;
        // check /recurse right
        if (!checkBST(n.right))
            return false;

        return true;// All good
    }

    // check min max as we traverser down the tree. Left update max and right update
    // min
    boolean checkBSTMM(Node<Integer> n) {
        return checkBSTMM(n, null, null);

    }

    boolean checkBSTMM(Node<Integer> n, Integer min, Integer max) {
        if (n == null)
            return true;
        if ((min != null && n.data < min) || (max != null && n.data > max)) {
            return false;
        }
        if (!checkBSTMM(n.left, min, n.data) || !checkBSTMM(n.right, n.data, max)) {
            return false;
        }
        return true;

    }

    Node<Integer> inorderSucc(Node<Integer> n) {
        if (n == null)
            return null;
        // found right children-> return leftmost node
        if (n.right != null) {
            return leftmostChild(n.right);
        } else {
            Node<Integer> q = n;
            Node<Integer> x = q;
            while (x != null && x.left != q) {
                q = x;
                x = x.left;
            }
            return x;
        }
    }

    Node<Integer> leftmostChild(Node<Integer> n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }
}
