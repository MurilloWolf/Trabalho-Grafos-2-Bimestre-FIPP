
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
     
     public static boolean algoritmo2(){
         boolean result = false, vet[];
         int menor =-1 ;
         
         /*vetor que contem a pos da matriz da qual o 
          elemento foi capturado 
          posmenor[0] = i
          posmenor[1] = j
         */
         int posmenor[] = new int[2];
         String aresta = "";
         if(MA!=null){
             try{
                 
     
             /*INICIA O VETOR */
             vet = new boolean[MA.size()];
             for (int i = 0; i < vet.length; i++) {
                 vet[i]= false;
             }
             
             /*INICIA A PRIMEIRA LINHA*/
             vet[0]= true;
             
             /*PERCORRE A MATRIZ*/
             for (int k = 0; k<vet.length; k++) {
                 
          
                /*PERCORRE O VETOR NORMAL*/
                for (int i = 0; i < vet.length; i++) {


                    /*PERCORRE O VETOR NEGADO*/
                    for (int j = 0; j < vet.length; j++) {

                        /*Se vet[n]== true (linha valida)
                          Se !vet[n]== true (coluna valida)
                        */
                        /*mudar o valor do menor */
                        if(vet[i] && !vet[j] ){
                             if(menor <0){
                                 menor = MA.get(i).get(j).getValor();
                                 aresta = rotulosMA.get(i)+rotulosMA.get(j);
                                    posmenor[0]=i;
                                       posmenor[1]=j;
                                       
                              
                                 
                             }else{
                                 if(MA.get(i).get(j).getValor() !=0 && MA.get(i).get(j).getValor()<menor){
                                       menor = MA.get(i).get(j).getValor();
                                        aresta = rotulosMA.get(i)+rotulosMA.get(j);
                                           posmenor[0]=i;
                                             posmenor[1]=j;
                                 }
 
                             }
                          
                             
                        }
                    }
                }//
                
                if(vet[posmenor[0]])
                    vet[posmenor[1]]=true;
                else
                    vet[posmenor[0]]=true;
                
                 System.out.printf("vetor: ");
                 for (int i = 0; i < vet.length; i++) {
                     if (vet[i]) {
                           System.out.printf(" 1");
                     }
                     else
                         System.out.printf(" 0");
                   
                 }
                 System.out.println("");
                System.out.println("Aresta: "+aresta);
                result = true;
             }
             }catch(Exception ex){
                 System.out.println("erro: "+ex.getMessage());
                 return false;
             }
         }
         
         return result ;
     }
}
