
package grafos2bimestre;

import Classes.Funcao;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class TelaPincipalController implements Initializable {
    
    private Label label;
    @FXML
    private TextArea txtAreaMa;
    @FXML
    private TextArea txtAreaOut;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirArquivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
         File file = fc.showOpenDialog(null);
         Funcao.geraMA(file);
         
         txtAreaMa.setText(Funcao.maToString());
         
    }

    @FXML
    private void algoritmo2(ActionEvent event) {
       if( Funcao.algoritmoPrim()){
           String outTela = Funcao.getOutPut();
           
           txtAreaOut.setText(outTela);
       }
       else{
           
           Alert a = new Alert(AlertType.ERROR);
           a.setHeaderText("Erro ao executar Algoritmo de Prim");
           
           a.setContentText(Funcao.getErro());
           a.showAndWait();
       }
            
    }

    @FXML
    private void algoritmokruskal(ActionEvent event) {
        
         if( Funcao.algoritKruskal()){
           String outTela = Funcao.getOutKruskal();
           
           txtAreaOut.setText(outTela);
       }
       else{
           
           Alert a = new Alert(AlertType.ERROR);
           a.setHeaderText("Erro ao executar Algoritmo de Kruskal");
           
           a.setContentText(Funcao.getErro());
           a.showAndWait();
       }
    }
    
}
