package controller;

import api.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import javax.xml.crypto.Data;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TableViewController extends ControllerClassType {

    //==========================================================
    //TableRoot aqui
    @FXML
    private StackPane tvStackPane;

    /*@FXML
    protected TableView<DataExchange> tvDados;*/

    private ApiObject apiData;
    @FXML
    private TableView<DataExchange> tvDados;
    //=======================================================


    public void setApiData(ApiObject apiData) { this.apiData = apiData; }
    public ApiObject getApiData() { return apiData; }

    private boolean threadStop = false;
    public TableViewController(){ }

    public void initialize(){

        ArrayList<TableColumn> tC = new ArrayList<>();
        TableColumn<DataExchange, String> col1 = new TableColumn<>("Exchange");
        TableColumn<DataExchange, String> col2 = new TableColumn<>("Rank");
        TableColumn<DataExchange, String> col3 = new TableColumn<>("Simbolo");
        TableColumn<DataExchange, String> col4 = new TableColumn<>("Nome");
        TableColumn<DataExchange, String> col5 = new TableColumn<>("Par");
        TableColumn<DataExchange, String> col6 = new TableColumn<>("Preço do par");
        TableColumn<DataExchange, String> col7 = new TableColumn<>("Preço em dólar");
        TableColumn<DataExchange, String> col8 = new TableColumn<>("Volume da moeda em 24hrs");
        TableColumn<DataExchange, String> col9 = new TableColumn<>("Volume da Exchange");
        TableColumn<DataExchange, String> col10 = new TableColumn<>("Qtd. trades");
        tC.add(col1);tC.add(col2);tC.add(col3);tC.add(col4);tC.add(col5);tC.add(col6);
        tC.add(col7);tC.add(col8);tC.add(col9);tC.add(col10);
        for(TableColumn<DataExchange,String> sp:tC){
            sp.getStyleClass().add("ColumnClass");
        }
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

        threadStart();
        /*Thread t = new Thread(tarefaCargaPg);
        t.setDaemon(true);
        t.start();*/
    }

    public void threadShutdown(){ threadStop = true; }
    public void threadStart(){ threadStop = false;t1.start(); }

    public void ThreadParseMessage(String info,String name){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getParentController().messageDialog(getParentStackPane(),info,name);
            }
        });
    }




    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(true){
                if(apiData != null && apiData.isDataReady()){
                    tvDados.getItems().clear();
                    for(DataExchange dt : apiData.getDataArray().data) {
                        tvDados.getItems().addAll(dt);
                    }
                    try {
                        sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}