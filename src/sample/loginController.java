package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginController {

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
    private switchScene switchScene;

    @FXML
    private Stage stage;


    @FXML
    public void initialize(){
        this.stage = (Stage) stackPane.getScene().getWindow();
        this.switchScene = new switchScene(this.stage);
    }

    @FXML
    void display(ActionEvent event) {
        System.out.println("Nicee");
        if(InputUsername.getText().equals("admin") && InputPassword.getText().equals("admin")){
            String fullName = InputUsername.getText() + " : " + InputPassword.getText() + "\nDeu bom";
            MessageDialog(fullName,InputUsername.getText());
        }else{
            String fullName = InputUsername.getText() + " : " + InputPassword.getText() + "\nDeu ruim";
            MessageDialog(fullName,InputUsername.getText());
        }

    }

    @FXML
    void switch_cad(ActionEvent event){
        switchScene.switch_("register");
        //stage.close();

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
