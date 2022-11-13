import utils.BTreePrinter;
import tree.Node;
import utils.DateFormat;

public class Main {
    public static void main(String[] args) {
        //TODO resolver depois como fica quando o nome é duplicado, imagino que se use CPF como desempate

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


        Node dateRoot = new Node(DateFormat.parse("01/01/2000"));
        dateRoot.insert(DateFormat.parse("01/01/2000"));
        dateRoot.insert(DateFormat.parse("01/01/2000"));
        dateRoot.insert(DateFormat.parse("01/01/2000"));
        dateRoot.insert(DateFormat.parse("03/02/2002"));
        dateRoot.insert(DateFormat.parse("01/01/2003"));
        dateRoot.insert(DateFormat.parse("02/01/2003"));

        BTreePrinter dateTreePrinter = new BTreePrinter(dateRoot, dateRoot.getChildren());
        System.out.println(dateTreePrinter.toString());


    }
}