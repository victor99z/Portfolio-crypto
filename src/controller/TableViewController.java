package controller;

import com.api.*;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.awt.event.MouseEvent;

public class TableViewController extends Thread{

    private ApiReader response;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    private JFXButton xama;

    @FXML
    void callApi(ActionEvent event) {
        t1.start();
    }

    Thread t1 = new Thread(){
        public void run(){
            while(true){
                try{
                    response = new ApiReader("https://api.coincap.io/v2/markets/?exchangeId=binance&limit=5");
                    Gson gson = new Gson();
                    DataArray dataarray = gson.fromJson(response.toString(),DataArray.class);
                    for(data sc : dataarray.data){
                        label1.setText(sc.quoteSymbol+"/"+sc.baseSymbol);
                        label2.setText("price : "+sc.priceQuote);
                    }
                    sleep(6000);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    };

}
