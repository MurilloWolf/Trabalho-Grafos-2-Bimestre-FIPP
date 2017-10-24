
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Funcao {
    
    private static ArrayList<ArrayList<Node>> MA;
    private static ArrayList<String> rotulosMA;
    private static ArrayList<String> arestasPrin;
    private static ArrayList<String> ordemExecVetor;
    private static String erro;
    private static int totalPrin;
    private static ArrayList<String> arestasKruskal;
    private static ArrayList<String> arestasMinimas;
    private static int custoKruskal;
    
    public Funcao(int length){
        erro="";
        ordemExecVetor = new ArrayList();
        totalPrin= 0;
        arestasPrin= new ArrayList();
        rotulosMA = new ArrayList();
        MA = new ArrayList();
        
        for (int i = 0; i < length; i++) {
            MA.add(new ArrayList());
            
        }
        arestasKruskal = new ArrayList();
        arestasMinimas = new ArrayList();
        custoKruskal=0;
    }

    public static int getTotalPrin() {
        return totalPrin;
    }

    public static void setTotalPrin(int totalPrin) {
        Funcao.totalPrin = totalPrin;
    }

    public static ArrayList<String> getArestasPrin() {
        return arestasPrin;
    }

    public static void setArestasPrin(ArrayList<String> arestasPrin) {
        Funcao.arestasPrin = arestasPrin;
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
     
     public static boolean algoritmoPrim(){
         boolean result = false, vet[];
         int menor =-1 ;
         String aux;
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
             for (int k = 0; k<vet.length -1; k++) {
                 
          
                /*PERCORRE O VETOR NORMAL*/
                for (int i = 0; i < vet.length; i++) {
                  //  System.out.println("linha observada: "+rotulosMA.get(i));
                  //  System.out.println("colunas :");
                    /*PERCORRE O VETOR NEGADO*/
                    for (int j = 0; j < vet.length; j++) {

                        /*Se vet[n]== true (linha valida)
                          Se !vet[n]== true (coluna valida)
                        */
                        /*mudar o valor do menor */
                        if(vet[i] && !vet[j] ){
                             if(menor <=0){
                                 menor = MA.get(i).get(j).getValor();
                                 aresta = rotulosMA.get(i)+rotulosMA.get(j);
                                    posmenor[0]=i;
                                       posmenor[1]=j;
                                       
                              
                                 
                             }else{
                                 if(MA.get(i).get(j).getValor() >0 && MA.get(i).get(j).getValor()<=menor){
                                       menor = MA.get(i).get(j).getValor();
                                        aresta = rotulosMA.get(i)+rotulosMA.get(j);
                                           posmenor[0]=i;
                                             posmenor[1]=j;
                                 }
 
                             }
                          //  System.out.println("\t"+rotulosMA.get(j));
                             
                        }
                       
                    }
                 //   System.out.println("Menor numero encontrado: "+menor);
                }//
                
                if(vet[posmenor[0]])
                    vet[posmenor[1]]=true;
                else
                    vet[posmenor[0]]=true;
                
                totalPrin +=menor;
                menor = -1;
                
                aux="";
                if(ordemExecVetor!=null){
                    
                   aux = "vetor :{";
                    for (int i = 0; i < vet.length; i++) {
                        if (vet[i]) {

                              aux +=",1";
                        }
                        else
                            aux+=",0";
                        

                    }
                    aux +="}";
                    ordemExecVetor.add(aux);
                }
               
                if(arestasPrin!=null)
                    arestasPrin.add(aresta);
                
                result = true;
             }
             }catch(Exception ex){
                erro = ex.getMessage();
                 return false;
             }
         }
         
         return result ;
     }

    public static ArrayList<String> getOrdemExecVetor() {
        return ordemExecVetor;
    }

    public static String getErro() {
        return erro;
    }

    public static void setErro(String erro) {
        Funcao.erro = erro;
    }

    public static void setOrdemExecVetor(ArrayList<String> ordemExecVetor) {
        Funcao.ordemExecVetor = ordemExecVetor;
    }
    
    public static String getOutPut(){
        String out="";
        if(ordemExecVetor!=null){
            out="Ordem das Aresta Geradas: \n";
            for (int i = 0; i < arestasPrin.size(); i++) {
                out += arestasPrin.get(i)+" ";
            }
            out +="\n\nSoma das distancias da aresta :"+totalPrin+"\n\n";
            out +="Ordem de execução do vetor :\n\n";
            
            for (int i = 0; i < ordemExecVetor.size(); i++) {
                out+=ordemExecVetor.get(i)+"\n";
            }
        }
        return out;
    }
    

    public static boolean algoritKruskal()
    {
        boolean result = false, linha[],coluna[];
        String aux ="";
        String[] vet;
        int l,c;
        //rotula todos os vertices da matriz
        for(int i=0; i<rotulosMA.size(); i++)
        {
            for(int j=0; j<rotulosMA.size(); j++)
            {
                if(MA.get(i).get(j).getValor()!=0)
                {
                    aux = rotulosMA.get(i)+","+rotulosMA.get(j)+","+MA.get(i).get(j).getValor();
                    if(!arestasKruskal.contains(aux))
                        arestasKruskal.add(aux);
                    aux="";
                }
            }
        }
        //cria dois vetores para as verificaçoes
        linha= new boolean[MA.size()];
        coluna= new boolean[MA.size()];
        for(int i=0; i<linha.length; i++)
        {
            linha[i]=false;
            coluna[i]=false;
        }
        //add as arestas
        for(int i=0;i<arestasKruskal.size();i++)
        {
            vet = arestasKruskal.get(i).split(",");
            //seleciona a primeira letra
            for(l=0;!vet[0].equals(rotulosMA.get(l))&&l<rotulosMA.size();l++)
            {}
            //seleciona a segunda letra
            for(c=0;!vet[1].equals(rotulosMA.get(c))&&c<rotulosMA.size();c++)
            {}
            //ve se pode colocar a aresta
            if(!linha[l] || !coluna[c])
            {
                linha[l]=true;
                coluna[c]=true;
                aux= arestasKruskal.get(i).toString();
                arestasMinimas.add(aux);//ta aqui o problema ???????????????????????????????????????
                
                custoKruskal=Integer.parseInt(vet[2]);
                aux="";
            }
        }
        return result = true;
    }
    public static String getOutKruskal()
    {
        String[] vet;
        String out ="";
        out="Arestas geradas:\n | ";
        for(int i=0; i<arestasKruskal.size(); i++)
        {
            vet = arestasKruskal.get(i).split(",");
            out+=vet[0]+","+vet[1]+" | ";
        }
        out+="\n\nArestas Minimas\n | ";
        for(int i=0; i<arestasMinimas.size(); i++)
        {
            vet = arestasMinimas.get(i).split(",");
            out+=vet[0]+","+vet[1]+" | ";
        }
        out+="\n\n\nCusto: "+custoKruskal;

        return out;
    }
    
}
