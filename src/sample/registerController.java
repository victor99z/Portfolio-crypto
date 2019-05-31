package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class registerController {

    @FXML
    private StackPane anchorDiag;

    @FXML
    private JFXToggleButton dialogToggle;

    @FXML
    void showDialog(ActionEvent event) {
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setBody(new Text("KKEAEMEN"));
        JFXButton bt_dis = new JFXButton("Java Lishu");

        JFXDialog dialog = new JFXDialog(anchorDiag,dl,JFXDialog.DialogTransition.CENTER);

        bt_dis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dl.setActions(bt_dis);
        dialog.show(anchorDiag);
    }

}
