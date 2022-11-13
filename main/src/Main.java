import person.Pessoa;
import tree.Node;
import utils.BTreePrinter;
import utils.DateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        Pessoa pessoas[] = new Pessoa[1000];
        int count = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(";");
            pessoas[count] = new Pessoa(Long.parseLong(line[0]), Long.parseLong(line[1]), line[2], DateFormat.parse(line[3]), line[4]);
            if (firstLine) {
                firstLine = false;
                cpfRoot = new Node(Long.parseLong(line[0]),pessoas[count]);
                nameRoot = new Node(line[2],pessoas[count]);
                birthRoot = new Node(DateFormat.parse(line[3]),pessoas[count]);
                count++;
                continue;
            }
            cpfRoot.insert(Long.parseLong(line[0]),pessoas[count]);
            nameRoot.insert(line[2],pessoas[count]);
            birthRoot.insert(DateFormat.parse(line[3]),pessoas[count]);
            count++;
        }
        scanner.close();

        BTreePrinter cpfTreePrinter = new BTreePrinter(cpfRoot, cpfRoot.getChildren());
        System.out.println(cpfTreePrinter.toString());
        BTreePrinter nameTreePrinter = new BTreePrinter(nameRoot, nameRoot.getChildren());
        System.out.println(nameTreePrinter.toString());
        BTreePrinter birthTreePrinter = new BTreePrinter(birthRoot, birthRoot.getChildren());
        System.out.println(birthTreePrinter.toString());
        //TODO resolver depois como fica quando o nome é duplicado, imagino que se use CPF como desempate
        //TODO fazer menu
        //TODO fazer aquelas buscas diferenciadas

        System.out.println(cpfRoot.find(12345678910L).personInfo());
        System.out.println(nameRoot.find("Fulano de Tal").personInfo());
        System.out.println(birthRoot.find(DateFormat.parse("01/02/1958")).personInfo());
    }
}