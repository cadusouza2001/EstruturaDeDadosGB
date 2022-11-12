import tree.BTreePrinter;
import tree.Node;

public class Main {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.insert(15);
        root.insert(7);
        root.insert(25);
        root.insert(30);
        root.insert(27);
        root.insert(49);
        BTreePrinter treePrinter = new BTreePrinter(root, root.getChildren());
        System.out.println(treePrinter.toString());

    }
}