package controller;

import com.api.ApiReader;
import com.api.*;
import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TableViewController{

    private DataArray dataarray;
    @FXML
    private TableView<data> tvDados;

    public void initialize(){

        TableColumn<data, String> col1 = new TableColumn<>("#");
        TableColumn<data, String> col2 = new TableColumn<>("#1");
        TableColumn<data, String> col3 = new TableColumn<>("#2");
        TableColumn<data, String> col4 = new TableColumn<>("#3");
        TableColumn<data, String> col5 = new TableColumn<>("#4");
        TableColumn<data, String> col6 = new TableColumn<>("#5");
        TableColumn<data, String> col7 = new TableColumn<>("#6");
        TableColumn<data, String> col8 = new TableColumn<>("#8");
        TableColumn<data, String> col9 = new TableColumn<>("#9");
        TableColumn<data, String> col10 = new TableColumn<>("0#");

        col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().exchangeId));
        col2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().rank));
        col3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().baseSymbol));
        col4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().baseId));
        col5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().quoteSymbol));
        col6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().priceQuote));
        col7.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().priceUsd));
        col8.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().volumeUsd24Hr));
        col9.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().percentExchangeVolume));
        col10.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().tradesCount24Hr));

        tvDados.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10);

        t1.start();

    }

    public void getResponse(){
        ApiReader response = new ApiReader("https://api.coincap.io/v2/markets/?exchangeId=binance&limit=10");
        Gson gson = new Gson();
        this.dataarray = gson.fromJson(response.toString(),DataArray.class);
    }

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(true){
                try {

                    getResponse();
                    tvDados.getItems().clear();
                    for(data dt : dataarray.data) {
                        tvDados.getItems().addAll(dt);
                    }
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
