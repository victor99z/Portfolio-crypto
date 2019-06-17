package controller;

import api.DataExchange;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
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
                if(ci.baseSymbol.equals("TUSD")){
                    GridPane p = new GridPane();
                    /*JFXTextField t = new JFXTextField();
                    t.setPromptText("Quantia");*/
                    JFXSlider t = new JFXSlider();
                    t.setValue(0);
                    t.setMax(Math.floor(userCoins.getCoinBySymbol("TUSD").qtd));
                    t.setMin(0);

                    JFXButton b = new JFXButton();
                    b.setText("Retirar");
                    b.setOnAction(event -> {
                            try {
                                CoinInfo nc = new CoinInfo(ci.baseSymbol, Double.valueOf(ci.priceUsd), Double.valueOf(t.getValue()));
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

                    JFXButton b2 = new JFXButton();
                    b2.setText("Retirar tudo");
                    b2.setOnAction(event -> {
                        try {
                            //CoinInfo nc = new CoinInfo(ci.baseSymbol, Double.valueOf(ci.priceUsd), userCoins.getCoinBySymbol(ci.baseSymbol).qtd );
                            CoinInfo nc = userCoins.getCoinBySymbol(ci.baseSymbol);
                            if(userCoins.deleteCoin(nc)){
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
                    p.addColumn(4, b2);
                    c++;
                    listCoins.add(p);
                }
            }
        }
        coinDisplay.setItems(listCoins);
    }
}
