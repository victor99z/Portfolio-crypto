package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    private JFXButton btn;

    @FXML
    void display(ActionEvent event) {
        System.out.println("SHOE DE BOLA");
    }

}
