package controller;

import com.jfoenix.controls.*;
import core.UserLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RegisterController extends ControllerClassType {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXPasswordField userPasswordField;

    @FXML
    private JFXButton cadBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField emailField;

    @FXML
    private Label titleLabel;

    private UserLogin register;

    public void initialize(){
        register = new UserLogin();
    }

    @FXML
    void display(ActionEvent event) {
        if(!register.getLoginState() && register.getUserInfo() == null){
            if(register.writeJSON(emailField.getText(),userField.getText(),userPasswordField.getText())){
                messageDialog(stackPane,"Cadastrado com sucesso!","Cadastro");
            }else{
                messageDialog(stackPane,"Cadastrado deu ruin!","Cadastro");
            }
        }else{
            messageDialog(stackPane,"Cadastrado ja existe","Cadastro");
        }
    }

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

}
