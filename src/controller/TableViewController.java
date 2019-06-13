package controller;

import com.api.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public class TableViewController extends TableRoot {

    public TableViewController(){
        super("https://api.coincap.io/v2/markets?exchangeId=binance&quoteId=tether&limit=50");
    }

    public void initialize(){


        TableColumn<DataExchange, String> col2 = new TableColumn<>("Rank");
        TableColumn<DataExchange, String> col3 = new TableColumn<>("Symbol");
        TableColumn<DataExchange, String> col4 = new TableColumn<>("Nome");
        TableColumn<DataExchange, String> col5 = new TableColumn<>("Par");
        TableColumn<DataExchange, String> col6 = new TableColumn<>("priceQUOTE");
        TableColumn<DataExchange, String> col7 = new TableColumn<>("priceUSD");
        TableColumn<DataExchange, String> col8 = new TableColumn<>("VOLUME24HR");
        TableColumn<DataExchange, String> col9 = new TableColumn<>("ExchangeVolume");
        TableColumn<DataExchange, String> col10 = new TableColumn<>("TradesCount");


        col2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().rank));
        col3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().baseSymbol));
        col4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().baseId));
        col5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().quoteSymbol));
        col6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().priceQuote));
        col7.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().priceUsd));
        col8.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().volumeUsd24Hr));
        col9.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().percentExchangeVolume));
        col10.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().tradesCount24Hr));

        tvDados.getColumns().addAll(col2,col3,col4,col5,col6,col7,col8,col9,col10);

        t1.start();

    }

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(true){
                try {

                    getResponseFromAPI(getUrl());

                    tvDados.getItems().clear();
                    for(DataExchange dt : dataArray.data) {
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
