public class ArvoreDigitalTernaria{

    // Attributes
    private No raiz;

    //Methods
    public void inserir(String palavra) { 
        if (palavra == null || palavra.isEmpty()) {
            return;
             
        }
        raiz = inserirRecursivo(raiz, palavra, 0);

    }

    private No inserirRecursivo(No no, String palavra, int posicao) { 
        char caractere = palavra.charAt(posicao); 

        if (no == null) { 
            no = new No(caractere); 
        } 
        
        if (caractere < no.getCaractere()) { 
            no.setEsquerdo( inserirRecursivo(no.getEsquerdo(), palavra, posicao)); 
            
        } else if (caractere > no.getCaractere()) { 
            no.setDireito( inserirRecursivo(no.getDireito(), palavra, posicao));

        } else { 
            if (posicao < palavra.length() - 1) {
                no.setMeio( inserirRecursivo(no.getMeio(), palavra, posicao + 1));

            } else {
                no.setFimDaPalavra(true); 

            } 
        } 
        return no; 
    
    } 
    
    public No buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) { 
            return null;

        }

        return buscarRecursivo(raiz, palavra, 0);
    }

    private No buscarRecursivo(No no, String palavra, int posicao) { 
        if (no == null) { 
            return null; 
        }

        char caractere = palavra.charAt(posicao);
        
        if (caractere < no.getCaractere()) { 
            return buscarRecursivo(no.getEsquerdo(), palavra, posicao);

        } else if (caractere > no.getCaractere()) { 
            return buscarRecursivo( no.getDireito(), palavra, posicao);

        } else {
            if (posicao == palavra.length() - 1) { 
                return no; 
            } 
            return buscarRecursivo( no.getMeio(), palavra, posicao + 1); 
        }

    }

    // Getters and Setters
    public No getRaiz() {
        return raiz;

    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;

    }
}