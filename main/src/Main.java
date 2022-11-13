import tree.Node;
import utils.BTreePrinter;
import utils.DateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO resolver depois como fica quando o nome é duplicado, imagino que se use CPF como desempate

        //Leitura do arquivo CSV
        Scanner scanner = null;
        String path = "main\\src\\dados.csv";

        try {
            scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado em " + path);
            throw new RuntimeException(e);
        }
        scanner.useDelimiter(";");
        boolean firstLine = true;
        Node cpfRoot = null;
        Node nameRoot = null;
        Node birthRoot = null;

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(";");
            //TODO criar objetos pessoa e passar para a árvore a referência

            if (firstLine) {
                firstLine = false;
                cpfRoot = new Node(Long.parseLong(line[0]));
                nameRoot = new Node(line[2]);
                birthRoot = new Node(DateFormat.parse(line[3]));
                continue;
            }
            cpfRoot.insert(Long.parseLong(line[0]));
            nameRoot.insert(line[2]);
            birthRoot.insert(DateFormat.parse(line[3]));
        }
        scanner.close();
        BTreePrinter cpfTreePrinter = new BTreePrinter(cpfRoot, cpfRoot.getChildren());
        System.out.println(cpfTreePrinter.toString());
        BTreePrinter nameTreePrinter = new BTreePrinter(nameRoot, nameRoot.getChildren());
        System.out.println(nameTreePrinter.toString());
        BTreePrinter birthTreePrinter = new BTreePrinter(birthRoot, birthRoot.getChildren());
        System.out.println(birthTreePrinter.toString());
    }
}