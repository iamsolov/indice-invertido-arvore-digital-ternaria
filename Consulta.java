import java.util.ArrayList;
import java.util.List;

public class Consulta {

    // Attributes
    private IndiceInvertido indice;

    // Constructor
    public Consulta(IndiceInvertido indice) {
        this.indice = indice;

    }

    // Methods
    public List<String> executar(String consulta) {
        if (consulta == null || consulta.trim().isEmpty()) {
            return new ArrayList<>();

        }

        String[] tokens = consulta.trim().split("\\s+");

        return processar(tokens, 0, tokens.length - 1);
    }

    private List<String> processar(String[] tokens, int inicio, int fim) {

        // OU - menor precedencia
        for (int i = fim; i >= inicio; i--) {
            if (tokens[i].equalsIgnoreCase("OU")) {

                List<String> esquerda = processar(tokens, inicio, i - 1);
                List<String> direita = processar(tokens, i + 1, fim);

                return unir(esquerda, direita);
            }
        }

        // E
        for (int i = fim; i >= inicio; i--) {
            if (tokens[i].equalsIgnoreCase("E")) {

                List<String> esquerda = processar(tokens, inicio, i - 1);
                List<String> direita = processar(tokens, i + 1, fim);

                return interseccao(esquerda, direita);
            }
        }

        // NAO
        if (inicio <= fim && tokens[inicio].equalsIgnoreCase("NAO")) {

            List<String> resultado = processar(tokens, inicio + 1, fim);

            return complemento(resultado);
        }

        // Palavra simples
        if (inicio == fim) {
            return indice.buscarPalavra(normalizarPalavra(tokens[inicio]));

        }

        return new ArrayList<>();
    }

    private List<String> unir(List<String> lista1, List<String> lista2) {

        List<String> resultado = new ArrayList<>();

        for (String arquivo : lista1) {
            if (!resultado.contains(arquivo)) {
                resultado.add(arquivo);

            }
        }

        for (String arquivo : lista2) {
            if (!resultado.contains(arquivo)) {
                resultado.add(arquivo);

            }
        }

        return resultado;
    }

    private List<String> interseccao(List<String> lista1, List<String> lista2) {

        List<String> resultado = new ArrayList<>();

        for (String arquivo : lista1) {
            if (lista2.contains(arquivo)) {
                resultado.add(arquivo);

            }
        }

        return resultado;
    }

    private List<String> complemento(List<String> lista) {

        List<String> resultado = new ArrayList<>();

        for (String arquivo : indice.getArquivos()) {
            if (!lista.contains(arquivo)) {
                resultado.add(arquivo);

            }
        }

        return resultado;
    }

    private String normalizarPalavra(String palavra) {

        palavra = palavra.toLowerCase();

        palavra = palavra.replaceAll("[^a-záéíóúàâêôãõçü]", "");

        return palavra;
    }
}