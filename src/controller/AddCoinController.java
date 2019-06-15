package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import database.CoinInfo;
import database.UserCoin;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.awt.*;

public class AddCoinController extends ControllerClassType{
    @FXML
    private JFXListView<GridPane> coinDisplay;
    @FXML
    private StackPane coinStackPane;

    private UserCoin uc;
    public void setUserCoinObject(UserCoin uc){ this.uc = uc;}
    public UserCoin getUserCoinObject(){ return uc; }

    public void initialize(){ }




    public void gridCoinSet(){
        int c=0;
        ObservableList<GridPane> listCoins = FXCollections.observableArrayList();

        for (CoinInfo ci:uc.ArrayCoins) {
            GridPane p = new GridPane();

            JFXTextField t=new JFXTextField();
            t.setPromptText("Quantia");
            JFXButton b=new JFXButton();
            b.setText("Adicionar");
            b.setOnAction(event -> {
                if(t.getText().length() > 0)
                    try{
                        ci.qtd+=Integer.valueOf(t.getText());
                    }catch (Exception e){
                        MessageDialog(coinStackPane,"Quantia inválida","Erro");
                        /*HomeController parentController = (HomeController) getParentController();
                        parentController.updateJSON();*/
                    }
            });

            p.addColumn(0,new Text(String.valueOf(c)));
            p.addColumn(1,new Text(ci.idCoin));
            p.addColumn(2,t);
            p.addColumn(3,b);
            c++;
            listCoins.add(p);
        }
        coinDisplay.setItems(listCoins);
    }
}
