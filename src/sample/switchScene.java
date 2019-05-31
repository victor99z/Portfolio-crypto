package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class switchScene {

    private final Stage stage;

    public switchScene(Stage targ){
        this.stage = targ;
    }
    public void switch_(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile+".fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();//
            this.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*if (fxmlFile.equals("calculator.fxml")) {
                BasicCalculatorView controller = (BasicCalculatorView) loader.getController();
                controller.setModel(new BasicCalculatorModelTest(controller));
                controller.setLogic(this);
            } else if (fxmlFile.equals("TestSwitch.fxml")) {
                TestSwitch controller = (TestSwitch) loader.getController();
                controller.setLogic(this);
            }*/