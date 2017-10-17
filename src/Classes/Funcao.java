
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Funcao {
    
    private static ArrayList<ArrayList<Node>> MA;
    private static ArrayList<String> rotulosMA;
    
    public Funcao(int length){
        
        rotulosMA = new ArrayList();
        MA = new ArrayList();
        
        for (int i = 0; i < length; i++) {
            MA.add(new ArrayList());
            
        }
     
    }
     public static void geraMA(File file) {
        String[] vet;
        boolean first = true;
        ArrayList< ArrayList< Node>> list = null;
        int lin = 0;
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);
            //le a primeira linha do arquivo;
            String linha = lerArq.readLine();

            while (linha != null) {

                //separa a linha em um vetor de String
                vet = linha.split("\t");

                //se for a primeira linha estancia a estrutura
                if (first) {

                     new Funcao(vet.length);

                    //estancia o arrayList de rotulos (caso o grafico seja rotulado)
                    for (int i = 0; i < vet.length; i++) {
                        rotulosMA.add(vet[i]);

                    }
                    first = false;
                    //pega a lista de lista 
                    list = MA;
                } else {
                    
                    //insere os elementos de vet(vetor de String ) na posição "lin" do arrayList de arrayList 
                    for (int i = 0; i < vet.length; i++) {

                        list.get(lin).add(new Node(Integer.parseInt(vet[i])));

                    }

                    lin++;

                }

                //le proxima linha do arquivo
                linha = lerArq.readLine();
            }
            //fecha o arquivo
            MA = list;
            arq.close();

            System.out.println("lista gerada com sucesso");
        } catch (IOException e) {
            System.out.println("erro ao gerar a lista ");
        }

    }
     
     public static String maToString(){
         String ret="";
            if(MA!=null){
                for (int i = 0; i < rotulosMA.size(); i++) {
                    ret= ret+"\t"+rotulosMA.get(i).toString().toUpperCase();
                }
                
                for (int i = 0; i < MA.size(); i++) {
                    ret = ret+"\n"+rotulosMA.get(i).toString().toUpperCase()+"\t";
                    for (int j = 0; j < MA.get(i).size(); j++) {
                        ret= ret+MA.get(i).get(j).toString()+"\t";
                    }
                }
                
            }
         
         return ret;
     }
}
