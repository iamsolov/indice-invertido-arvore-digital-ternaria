import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IndiceInvertido {

    // Attributes
    private ArvoreDigitalTernaria arvore;
    private List<String> arquivos;

    // Constructor
    public IndiceInvertido() {
        arvore = new ArvoreDigitalTernaria();
        arquivos = new ArrayList<>();
    }

    // Methods
    public void indexarArquivo(String caminho) {
        File arquivo = new File(caminho);

        if (!arquivo.exists() || !arquivo.isFile()) {
            return;

        }

        if (!arquivos.contains(arquivo.getName())) {
            arquivos.add(arquivo.getName());
        
        }

        try {
            String texto = Files.readString(arquivo.toPath());

            String[] palavras = texto.split("\\s+");

            for (String palavra : palavras) {
                palavra = normalizarPalavra(palavra);

                if (!palavra.isEmpty()) {
                    arvore.inserir(palavra);

                    No no = arvore.buscar(palavra);

                    if (no != null && !no.getArquivosAssociados().contains(arquivo.getName())) {
                        no.getArquivosAssociados().add(arquivo.getName());

                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + arquivo.getName());
            
        }
    }

    private String normalizarPalavra(String palavra) {
        palavra = palavra.toLowerCase();

        palavra = palavra.replaceAll("[^a-záéíóúàâêôãõçü]", "");

        return palavra;
    }

    public List<String> buscarPalavra(String palavra) {
        palavra = normalizarPalavra(palavra);

        No no = arvore.buscar(palavra);

        if (no == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(no.getArquivosAssociados());
    }

    // Getters
    public List<String> getArquivos() {
        return arquivos;

    }

    public ArvoreDigitalTernaria getArvore() {
        return arvore;

    }
}