package controller;

import com.api.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TableViewController extends Thread{

    private ApiReader response;

    @FXML
    private JFXButton BtnAction;




    /*

    @FXML
    public void RecebeText(String response){
        //TextArea2.setText(response);
    }

    @FXML
    public void chamaApi(ActionEvent event) {
        new Thread(){
            public void run(){
                try{
                    ApiReader response = new ApiReader("https://api.coincap.io/v2/markets/?exchangeId=binance&baseSymbol=TUSD");
                    RecebeText(response.toString());
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }*/

}
