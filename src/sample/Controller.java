package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Controller {

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXTextField InputUsername;

    @FXML
    private JFXPasswordField InputPassword;

    @FXML
    void display(ActionEvent event) {
        if(InputUsername.getText().equals("admin") && InputPassword.getText().equals("admin")){
            String fullName = InputUsername.getText() + " : " + InputPassword.getText() + "\nDeu bom";
            // MessageDialog(fullName);
        }else{
            String fullName = InputUsername.getText() + " : " + InputPassword.getText() + "\nDeu ruim";
            // MessageDialog(fullName);
        }

    }

    /*
    void MessageDialog(String info){
        JFXDialog dialog = new JFXDialog(stackpane, new Label("AEHOOOOOOOO PORRA POO FDP"), JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }
    */

}
