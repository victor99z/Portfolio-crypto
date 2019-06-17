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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class SellCoinController extends ControllerClassType{
    @FXML
    private JFXListView<GridPane> coinDisplay;
    @FXML
    private StackPane coinStackPane;

    @FXML
    private Label currentSliderValue;

    private UserCoin userCoins;
    public void setUserCoinObject(UserCoin uc){ this.userCoins = uc;}
    public UserCoin getUserCoinObject(){ return userCoins; }

    public void initialize(){ }


    public void gridCoinSet(){
        int c=0;
        ObservableList<GridPane> listCoins = FXCollections.observableArrayList();
        if(getApiData()!= null && getApiData().isDataReady()) {
            for (CoinInfo ci : userCoins.arrayCoins) {
                if (!ci.idCoin.equals("TUSD")){
                    GridPane p = new GridPane();
                    p.setMinHeight(100);
                    /*JFXTextField t = new JFXTextField();
                    t.setPromptText("Quantia");*/
                    JFXSlider t = new JFXSlider();
                    t.setMinWidth(220);
                    t.setMax(ci.qtd);
                    t.setValue(0);
                    t.setMin(0);
                    t.setMinorTickCount(0);
                    t.setMajorTickUnit(0.5);
                    t.setShowTickLabels(true);
                    t.setBlockIncrement(0.00000001);
                    t.setOnDragDetected(event -> {
                        currentSliderValue.setText(String.valueOf(t.getValue()));
                    });
                    t.setOnMouseDragged(event -> {
                        currentSliderValue.setText(String.valueOf(t.getValue()));
                    });
                    t.setOnMouseClicked(event -> {
                        currentSliderValue.setText(String.valueOf(t.getValue()));
                    });

                    JFXButton b = new JFXButton();
                    b.setText("Vender");
                    b.setOnAction(event -> {
                        try {
                            CoinInfo nc = new CoinInfo(ci.idCoin, Double.valueOf(ci.buyPrice), Double.valueOf(t.getValue()));
                            if (userCoins.sellCoin(nc, getApiData().getDataArray().data)) {
                                //feito
                            } else {
                                messageDialog(coinStackPane, "Operação malsucedida", "Erro");
                            }
                        } catch (Exception e) {
                            messageDialog(coinStackPane, "Quantia inválida", "Erro");
                        /*HomeController parentController = (HomeController) getParentController();
                        parentController.updateJSON();*/
                        }
                    });

                    p.setAlignment(Pos.CENTER);
                    p.setHgap(10);
                    p.addColumn(1, new Text(ci.idCoin));
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
