package controller;

import api.*;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import static java.lang.Thread.sleep;

public class TableViewController extends ControllerClassType {

    //==========================================================
    //TableRoot aqui
    @FXML
    private StackPane tvStackPane;
    @FXML
    protected TableView<DataExchange> tvDados;
    protected DataArray dataArray;
    protected String url;
    private boolean connectionIssues=false;

    public void getResponseFromAPI(String url) throws Exception{
        try {
            ApiReader response = new ApiReader(url);
            Gson gson = new Gson();
            this.dataArray = gson.fromJson(response.toString(), DataArray.class);
        }catch (Exception e){
            throw e;
        }
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }

    //=======================================================

    private boolean threadStop = false;
    public TableViewController(){
        setUrl("https://api.coincap.io/v2/markets?exchangeId=binance&quoteId=tether&limit=50");
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
                getParentController().MessageDialog(getParentStackPane(),info,name);
            }
        });
    }




    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(!threadStop){
                try {
                    getResponseFromAPI(getUrl());
                    tvDados.getItems().clear();
                    for(DataExchange dt : dataArray.data) {
                        tvDados.getItems().addAll(dt);
                    }
                    connectionIssues=false;
                    sleep(8000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.err.println("Parsing Error!");
                }catch (Exception e){
                    System.err.println("Connection Failed!> "+e.toString() );
                    if(!connectionIssues){
                        connectionIssues=true;
                        ThreadParseMessage("Check your Internet Connection","Connection Issues");
                    }
                    try {
                        sleep(8000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
    };
}


/*======================================

    public Thread t;
    private Task<Void> tarefaCargaPg = new Task<Void>() {
        @Override
        protected Void call(){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    while(!threadStop && !isCancelled()){
                        try {
                            getResponseFromAPI(getUrl());
                            tvDados.getItems().clear();
                            for(DataExchange dt : dataArray.data) {
                                tvDados.getItems().addAll(dt);
                            }
                            connectionIssues=false;
                            sleep(8000);
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            System.err.println("Parsing Error!");
                        }catch (Exception e){
                            System.err.println("Connection Failed!> "+e.toString() );
                            if(!connectionIssues){
                                System.out.println("Thread issue");
                                connectionIssues=true;
                                ThreadParseMessage("Check your Internet Connection","Connection Issues");
                            }
                            try {
                                sleep(8000);
                            } catch (InterruptedException ex) {
                                //ex.printStackTrace();
                            }
                        }

                    }
                }
            });

            return null;
        }
    };


======================================*/