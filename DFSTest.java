import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DFSTest {
    @Test
    void testAddEdge() {
    DFS dfs = new DFS();
    dfs.root = new Node(100);
    dfs.root.left = new Node(90);
    dfs.root.right = new Node(110);
    Assertions.assertFalse(dfs.cycleDetection(dfs.root));
    }

    @Test
    void testAddVertex() {

    }

    @Test
    void testConcat() {

    }

    @Test
    void testConvert() {

    }

    @Test
    void testConvertToCircular() {

    }

    @Test
    void testDfs() {

    }

    @Test
    void testDfsWithoutRecursion() {

    }

    @Test
    void testIn() {

    }

    @Test
    void testMerge() {

    }

    @Test
    void testPost() {

    }

    @Test
    void testPre() {

    }

    @Test
    void testTopologicalSort() {

    }

    @Test
    void testTravInorderNoRec() {

    }

    @Test
    void testTravPostNoRec() {

    }

    @Test
    void testTravPreNoRec() {

    }

    @Test
    void testVisit() {

    }
}
