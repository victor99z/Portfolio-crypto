package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterController extends ControllerClassType {

    @FXML
    private Stage stage;

    @FXML
    private StackPane stackPane;

    @FXML
    private SwitchScene switchScene;

    @FXML
    private JFXToggleButton dialogToggle;

    @FXML
    void showDialog(ActionEvent event) {
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setBody(new Text("HAEUAHEUAE"));
        JFXButton bt_dis = new JFXButton("Java daora");

        JFXDialog dialog = new JFXDialog(stackPane,dl,JFXDialog.DialogTransition.CENTER);

        bt_dis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dl.setActions(bt_dis);
        dialog.show(stackPane);
    }

    @FXML
    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"loginScreen",false);
    }

    @FXML
    void display(ActionEvent event) {
        System.out.println("Nicee");
    }
}
