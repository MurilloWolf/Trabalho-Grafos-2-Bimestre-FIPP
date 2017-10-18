
package grafos2bimestre;

import Classes.Funcao;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
       if( Funcao.algoritmo2())
            System.out.println("sucesso");
       else
            System.out.println("erro no algoritmo 2");
    }
    
}
