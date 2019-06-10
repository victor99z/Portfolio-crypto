package controller;

import com.jfoenix.controls.*;
import core.UserLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends ControllerClassType{

    @FXML
    private StackPane stackPane;

    @FXML
    private Label titleLabel;

    @FXML
    private JFXTextField InputUsername;

    @FXML
    private JFXPasswordField InputPassword;

    @FXML
    private JFXButton cadBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private SwitchScene switchScene;

    @FXML
    private Stage stage;


    @FXML
    public void initialize(){

    }

    @FXML
    void display(ActionEvent event) {
        System.out.println("Nicee");
        UserLogin ul = new UserLogin(InputUsername.getText(), InputPassword.getText());
        if(ul.getLoginState() == true){
            super.switch_cad(event,stackPane,"RootPane",false);
        }else{
            MessageDialog(InputUsername.getText() + ": " + InputPassword.getText(),InputUsername.getText());
        }
    }

    @FXML
    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"registerScreen",false);
    }

    void MessageDialog(String info,String name){

        System.out.println("Noice");
        titleLabel.setText(name);


        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setHeading(new Text("KK eae men"));
        dl.setBody(new Text(info));
        JFXButton bt_dis = new JFXButton("BLZ");

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
}
