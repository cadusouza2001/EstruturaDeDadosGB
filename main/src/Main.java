import person.Pessoa;
import tree.Node;
import utils.BTreePrinter;
import utils.DateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        int count = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(";");

            pessoas.add(new Pessoa(Long.parseLong(line[0]), Long.parseLong(line[1]), line[2], DateFormat.parse(line[3]), line[4]));
            if (firstLine) {
                firstLine = false;
                cpfRoot = new Node(Long.parseLong(line[0]), pessoas.get(count));
                nameRoot = new Node(line[2], pessoas.get(count));
                birthRoot = new Node(DateFormat.parse(line[3]), pessoas.get(count));
                count++;
                continue;
            }
            //TODO ver com o sor se proibimos registros duplicados ou criamos tratamento
            if(cpfRoot.find(Long.parseLong(line[0])) != null) {
                System.out.println("CPF duplicado: " + line[0]);
            }
            else if(nameRoot.find(line[2]) != null) {
                System.out.println("Nome duplicado: " + line[2]);
            }
            else if(birthRoot.find(DateFormat.parse(line[3])) != null) {
                System.out.println("Data de nascimento duplicada: " + line[3]);
            }
            else {
                cpfRoot.insert(Long.parseLong(line[0]), pessoas.get(count));
                nameRoot.insert(line[2], pessoas.get(count));
                birthRoot.insert(DateFormat.parse(line[3]), pessoas.get(count));
                count++;
            }
        }
        scanner.close();

        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("1 - Buscar por CPF");
            System.out.println("2 - Buscar por nomes que iniciam com");
            System.out.println("3 - Buscar por pessoas que nasceram entre duas datas");
            System.out.println("4 - Visualizar árvore de CPF");
            System.out.println("5 - Visualizar árvore de nome");
            System.out.println("6 - Visualizar árvore de data de nascimento");
            System.out.println("0 - Sair");
            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Digite o CPF:");
                    long cpf = input.nextLong();
                    if (cpfRoot.find(cpf) != null) {
                        System.out.println(cpfRoot.find(cpf).personInfo());
                    } else {
                        System.out.println("CPF não encontrado");
                    }
                    break;
                case 2:
                    System.out.println("Digite as iniciais do nome:");
                    String name = input.next().toLowerCase();
                    ArrayList<Node> nodesThatStartWith = new ArrayList<>();
                    nodesThatStartWith = nameRoot.startsWith(name, nodesThatStartWith);
                    if (nodesThatStartWith.size() > 0) {
                        for (Node node : nodesThatStartWith) {
                            System.out.println(node.personInfo());
                        }
                    } else {
                        System.out.println("Nenhum nome encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Digite a data inicial: (dd/mm/aaaa)");
                    String startDate = input.next();
                    System.out.println("Digite a data final: (dd/mm/aaaa)");
                    String endDate = input.next();
                    ArrayList<Node> nodesBetweenDates = new ArrayList<>();
                    nodesBetweenDates = birthRoot.dateBetween(DateFormat.parse(startDate), DateFormat.parse(endDate), nodesBetweenDates);
                    if (nodesBetweenDates.size() > 0) {
                        for (Node node : nodesBetweenDates) {
                            System.out.println(node.personInfo());
                        }
                    } else {
                        System.out.println("Nenhuma pessoa encontrada");
                    }
                    break;
                case 4:
                    BTreePrinter cpfTreePrinter = new BTreePrinter(cpfRoot, cpfRoot.getChildren());
                    System.out.println(cpfTreePrinter.toString());
                    break;
                case 5:
                    BTreePrinter nameTreePrinter = new BTreePrinter(nameRoot, nameRoot.getChildren());
                    System.out.println(nameTreePrinter.toString());
                    break;
                case 6:
                    BTreePrinter birthTreePrinter = new BTreePrinter(birthRoot, birthRoot.getChildren());
                    System.out.println(birthTreePrinter.toString());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}