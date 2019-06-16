package controller;

import api.DataArray;
import api.DataExchange;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import database.CoinInfo;
import database.UserCoin;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class HomeController extends ControllerClassType implements Initializable {

    private UserCoin userCoins;
    @FXML
    private PieChart GraficoPizza;

    @FXML
    private JFXButton btAddCoin;

    //Home do git
    @FXML
    private VBox vBoxRoot;
    @FXML
    private Label labelMoneyTotal;
    @FXML
    private Label labelMoneyLucro;

    private double moneyTotal = 0;
    private double moneyInicial = 0;

    //==============

    public UserCoin setUserCoins(UserCoin userCoins) { this.userCoins = userCoins;return userCoins; }
    public UserCoin getUserCoins() { return userCoins; }

    @FXML
    void addCoinPanel(ActionEvent event) throws IOException {
        //Adicionar moedas na conta do usuário
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addCoin.fxml"));
            Parent root = (Parent) loader.load();
            AddCoinController coinCtr = loader.getController();

            Scene sc = new Scene(root);
            sc.getStylesheets().add("../src/css/homeStyle.css");
            Stage st = new Stage();
            st.setTitle("Add coins");
            st.setScene(sc);
            st.show();

            st.setOnCloseRequest(e -> {
                updateJSON();
                setGraficoMoedas();
            });

            coinCtr.setParentController(this);
            coinCtr.setUserCoinObject(userCoins);
            coinCtr.setApiData(this.getApiData());

            coinCtr.gridCoinSet();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*
        Pega os dados do nosso querido arquivo json la em /database/
    */


    public void getJsonFromFile(){
        Gson gson = new Gson();
        Reader reader;
        try {
            reader = new FileReader(System.getProperty("user.dir")+"\\portfolioDB.json");
            //reader = new FileReader(new File(ClassLoader.getSystemResource("portfolioDB.json").getFile()));
            //userCoins = gson.fromJson(reader,UserCoin.class);
            setUserCoins(gson.fromJson(reader,UserCoin.class));
        } catch (Exception e) {
            System.out.println("GetJSONHomeController: Error reading");
            createJSON();
            //e.printStackTrace();
        }
    }

    public boolean createJSON() {
        Gson g = new Gson();
        FileWriter write;
        try {
            UserCoin uc = new UserCoin();
            uc.username=getUserData().getUserInfo().getUsername();
            String json = g.toJson(uc);
            write = new FileWriter(System.getProperty("user.dir")+"\\portfolioDB.json");
            //File f = new File(String.valueOf(ClassLoader.getSystemResource("portfolioDB.json")));
            //write = new FileWriter(f);
            write.write(json);
            write.close();
            getJsonFromFile();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("CreateJSON: Error writing");
            return false;
        }

    }

    public boolean updateJSON(){
        Gson g = new Gson();
        try {
            String json = g.toJson(userCoins);
            //System.out.println(json);
            FileWriter write = new FileWriter(System.getProperty("user.dir")+"\\portfolioDB.json");
            write.write(json);
            write.close();
            return true;
        } catch (IOException e) {
            System.err.println("Database not updated!");
            e.printStackTrace();
            return false;
        }
    }

    /*
    *  Faz uma contagem total de quanto o cara tem em dolar e a porcentagem de cada moeda, preciso disso pra jogar os dados no pieChart
    * */

    public HashMap<String, Double> CountAllMoney(){
        getJsonFromFile();
        if(getUserCoins()!= null){
            double count = 0;
            HashMap<String, Double> coinPercentage  = new HashMap<>();
            for(CoinInfo dt : userCoins.arrayCoins){
                count += dt.qtd * dt.buyPrice;
            }
            for(CoinInfo dt : userCoins.arrayCoins){
                coinPercentage.put(dt.idCoin, (dt.buyPrice*dt.qtd)/count*100);
            }
            return coinPercentage;
        }else{
            return null;
        }
    }

    boolean dataWasSet = false;
    public void setGraficoMoedas(){
        dataWasSet=false;
        Platform.runLater(new Thread(){
            @Override
            public void run() {
                while(!dataWasSet){
                    try{
                        HashMap<String, Double> structure = CountAllMoney();
                        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                        coinValueNow();
                        if(structure != null){
                            Iterator it = structure.entrySet().iterator();
                            while(it.hasNext()){
                                Map.Entry pair = (Map.Entry)it.next();
                                String s = (String)pair.getKey();
                                Double v = Double.parseDouble(pair.getValue().toString());
                                pieChartData.add(new PieChart.Data(s,v));
                            }
                            dataWasSet=true;
                            GraficoPizza.setData(pieChartData);
                            GraficoPizza.setStartAngle(90);
                            labelMoneyTotal.setText("$ "+new DecimalFormat("##.##").format(moneyTotal));
                            if(moneyTotal-moneyInicial >= 0){
                                labelMoneyLucro.setStyle("-fx-text-fill: green");
                                labelMoneyLucro.setText("$ "+new DecimalFormat("##.##").format(moneyTotal-moneyInicial));
                            }else{
                                labelMoneyLucro.setStyle("-fx-text-fill: red");
                                labelMoneyLucro.setText("$ "+new DecimalFormat("##.##").format(moneyTotal-moneyInicial));
                            }
                        }else{
                            System.err.println("structure empty");
                            GraficoPizza.setData(pieChartData);
                            GraficoPizza.setStartAngle(90);
                            labelMoneyLucro.setStyle("-fx-text-fill: red");

                            sleep(5000);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //Home do GIT

    public void coinValueNow(){
        if(getApiData()!= null && getApiData().isDataReady()){
            for(CoinInfo dt : userCoins.arrayCoins){
                for(DataExchange dE : getApiData().getDataArray().data){
                    if(dt.idCoin.equals(dE.baseSymbol)){
                        Double value = Double.parseDouble(dE.priceUsd);
                        setCoinCells(dt.idCoin,dt.qtd,value);
                        moneyTotal += value*dt.qtd;
                    }
                }
            }
        }
    }
    public void setCoinCells(String nome, Double qtd, Double valorAtual){
        vBoxRoot.getChildren().removeAll();
        ObservableList<JFXListCell> cellView = FXCollections.observableArrayList();
        JFXListCell cell1 = new JFXListCell<>();
        cell1.setText(" "+nome+" [ "+qtd+" ] = $"+(new DecimalFormat("##.###").format(valorAtual*qtd)));
        cellView.add(cell1);
        vBoxRoot.getChildren().addAll(cellView);
    }

    //========================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGraficoMoedas();
    }

}

    /*Thread t1 = new Thread() {
        @Override
        public void run() {
            while(!dataWasSet){
                try{
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                    HashMap<String, Double> structure = CountAllMoney();
                    if(structure != null){
                        Iterator it = structure.entrySet().iterator();
                        while(it.hasNext()){
                            Map.Entry pair = (Map.Entry)it.next();
                            String s = (String)pair.getKey();
                            Double v = Double.parseDouble(pair.getValue().toString());

                            pieChartData.add(new PieChart.Data(s,v));
                        }
                        dataWasSet=true;
                        GraficoPizza.setData(pieChartData);
                        GraficoPizza.setStartAngle(90);
                    }else{
                        System.err.println("structure empty");
                        GraficoPizza.setData(pieChartData);
                        GraficoPizza.setStartAngle(90);
                        sleep(5000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };*/