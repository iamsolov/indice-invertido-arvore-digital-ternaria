import java.util.ArrayList;
import java.util.List;

public class No{

    // Attributes
    private char caractere;
    private No esquerdo;
    private No meio;
    private No direito;
    private boolean fimDaPalavra;
    private List<String> arquivosAssociados;

    //Constructor
    public No(char caractere) { 
        this.caractere = caractere; 
        this.esquerdo = null; 
        this.meio = null; 
        this.direito = null; 
        this.fimDaPalavra = false; 
        this.arquivosAssociados = new ArrayList<>(); 

    }

    // Getters and Setters
    public char getCaractere() {
        return caractere;

    }

    public No getEsquerdo() {
        return esquerdo;

    }

    public No getMeio() {
        return meio;

    }

    public No getDireito() {
        return direito;

    }

    public boolean getFimDaPalavra(){
        return fimDaPalavra;

    }

    public List<String> getArquivosAssociados() {
        return arquivosAssociados;

    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;

    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;

    }

    public void setMeio(No meio) {
        this.meio = meio;

    }

    public void setDireito(No direito) {
        this.direito = direito;

    }

    public void setFimDaPalavra(boolean fimDaPalavra) {
        this.fimDaPalavra = fimDaPalavra;

    }

    public void setArquivosAssociados(List<String> arquivosAssociados) {
        this.arquivosAssociados = arquivosAssociados;

    }
}