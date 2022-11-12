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
        System.out.println(root.preOrder());
        root.delete(25);
        root.delete(30);
        System.out.println(root.preOrder());
        System.out.println(root.find(27));
        System.out.println(root.find(30));

    }
}