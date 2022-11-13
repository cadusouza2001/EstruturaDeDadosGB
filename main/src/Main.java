import tree.BTreePrinter;
import tree.Node;

public class Main {
    public static void main(String[] args) {
        Node intRoot = new Node(10);
        intRoot.insert(15);
        intRoot.insert(7);
        intRoot.insert(25);
        intRoot.insert(30);
        intRoot.insert(27);
        intRoot.insert(49);
        BTreePrinter intTreePrinter = new BTreePrinter(intRoot, intRoot.getChildren());
        System.out.println(intTreePrinter.toString());

        Node stringRoot = new Node("Alunos");
        stringRoot.insert("Adriele");
        stringRoot.insert("Carlos");
        stringRoot.insert("Clara");
        stringRoot.insert("Professor");
        stringRoot.insert("Marcio");
        stringRoot.insert("Disciplina");
        stringRoot.insert("Algoritmos");
        stringRoot.insert("Programacao");
        stringRoot.insert("Trabalho");
        stringRoot.insert("Grau");
        stringRoot.insert("B");

        BTreePrinter stringTreePrinter = new BTreePrinter(stringRoot, stringRoot.getChildren());
        System.out.println(stringTreePrinter.toString());

    }
}