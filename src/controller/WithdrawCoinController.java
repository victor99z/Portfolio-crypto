package controller;

import api.DataExchange;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import database.CoinInfo;
import database.UserCoin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class WithdrawCoinController extends ControllerClassType{
    @FXML
    private JFXListView<GridPane> coinDisplay;
    @FXML
    private StackPane coinStackPane;

    private UserCoin userCoins;
    public void setUserCoinObject(UserCoin uc){ this.userCoins = uc;}
    public UserCoin getUserCoinObject(){ return userCoins; }

    public void initialize(){ }


    public void gridCoinSet(){
        int c=0;
        ObservableList<GridPane> listCoins = FXCollections.observableArrayList();
        if(getApiData()!= null && getApiData().isDataReady()) {
            for (DataExchange ci : getApiData().getDataArray().data) {
                if(ci.baseSymbol.equals("USDT")){
                    GridPane p = new GridPane();
                    JFXTextField t = new JFXTextField();
                    t.setPromptText("Quantia");
                    JFXButton b = new JFXButton();
                    b.setText("Retirar");
                    b.setOnAction(event -> {
                        if (t.getText().length() > 0)
                            try {
                                CoinInfo nc = new CoinInfo(ci.baseSymbol, Double.valueOf(ci.priceUsd), Double.valueOf(t.getText()));
                                if(userCoins.withdrawCoin(nc,getApiData().getDataArray().data)){
                                    //feito
                                }else {
                                    messageDialog(coinStackPane, "Operação malsucedida, erro na entrada de dados", "Erro");
                                }
                            } catch (Exception e) {
                                messageDialog(coinStackPane, "Quantia inválida", "Erro");
                        /*HomeController parentController = (HomeController) getParentController();
                        parentController.updateJSON();*/
                            }
                    });

                    p.setAlignment(Pos.CENTER);
                    p.setHgap(10);
                    p.addColumn(1, new Text(ci.baseSymbol));
                    p.addColumn(2, t);
                    p.addColumn(3, b);
                    c++;
                    listCoins.add(p);
                }
            }
        }
        coinDisplay.setItems(listCoins);
    }
}
