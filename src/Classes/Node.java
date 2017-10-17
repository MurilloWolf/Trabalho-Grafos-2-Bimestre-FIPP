
package Classes;

public class Node {
    private String coluna, linha;
    private int valor;
    
    public Node(){
        coluna = linha = "";
        valor = 0;
    }
    
    public Node(String col, String lin){
        coluna = col;
        linha = lin ;
        
        valor = 0;
    }
    
    public Node(int valor){
        this.valor = valor;
        coluna = linha = "";
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
            if(coluna.equals("") && linha.equals(""))
                return getValor()+" ";
            else
                if(!coluna.equals(""))
                        return getColuna();
            
            return getLinha();
    }
    
    
}
