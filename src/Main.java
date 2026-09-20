package src;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("           INDICE INVERTIDO");
        System.out.println("========================================");

        File pasta = new File("textos");

        File[] arquivos = pasta.listFiles();

        int quantidadeArquivos = 0;

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".txt")) {
                    quantidadeArquivos++;

                }
            }
        }

        System.out.println("Arquivos encontrados: " + quantidadeArquivos);
        System.out.println();

        System.out.println("Construindo indice...");

        IndiceInvertido indice = new IndiceInvertido();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".txt")) {
                    indice.indexarArquivo(arquivo.getPath());

                }
            }
        }

        System.out.println("Indice construido com sucesso.");
        System.out.println();

        Consulta consulta = new Consulta(indice);

        while (true) {

            System.out.println("Digite uma consulta:");
            System.out.print("> ");

            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println();
                System.out.println("Programa encerrado.");
                break;
            }

            List<String> resultado = consulta.executar(entrada);

            System.out.println();
            System.out.println("Arquivos encontrados:");

            for (String arquivo : resultado) {
                System.out.println(arquivo);

            }

            System.out.println();
        }

        scanner.close();
    }
}