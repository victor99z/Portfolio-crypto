package controller;

import com.api.*;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import database.CoinInfo;
import database.UserCoin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class HomeController implements Initializable {

    private UserCoin c1;
    @FXML
    private PieChart graficoPizza;
    @FXML
    private VBox vBoxRoot;

    private ObservableList<String> data;

    @FXML
    private Label labelMoneyTotal;

    private DataArray dataArray;

    private double moneyTotal = 0;

    /*
        Pega os dados do nosso querido arquivo json la em /database/
    */


    public void getJsonFromFile(){
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(new File(ClassLoader.getSystemResource("database/portfolioDB.json").getFile()));
            c1 = gson.fromJson(reader,UserCoin.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    *  Faz uma contagem total de quanto o cara tem em dolar e a porcentagem de cada moeda, preciso disso pra jogar os dados no pieChart
    * */

    public HashMap<String, Double> CountAllMoney(){
        getJsonFromFile();
        double count = 0;
        HashMap<String, Double> coinPercentage  = new HashMap<>();
        for(CoinInfo dt : c1.ArrayCoins){
            count += dt.qtd * dt.buyPrice;
        }

        for(CoinInfo dt : c1.ArrayCoins){
            coinPercentage.put(dt.idCoin, (dt.buyPrice*dt.qtd)/count*100);
        }

        return coinPercentage;
    }

    public void getResponse(){
        try{
            ApiReader response = new ApiReader("https://api.coincap.io/v2/markets?exchangeId=binance&quoteId=tether&limit=50");
            Gson gson = new Gson();
            dataArray = gson.fromJson(response.toString(), DataArray.class);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void coinValueNow(){
        for(CoinInfo dt : c1.ArrayCoins){
            for(DataExchange dE : dataArray.data){
                if(dt.idCoin.equals(dE.baseSymbol)){
                    Double value = Double.parseDouble(dE.priceUsd);
                    setCoinCells(dt.idCoin,dt.qtd,value);
                    moneyTotal += value*dt.qtd;
                }
            }
        }
    }

    public void setCoinCells(String nome, Double qtd, Double valorAtual){
        ObservableList<JFXListCell> cellView = FXCollections.observableArrayList();
        JFXListCell cell1 = new JFXListCell<>();
        cell1.setText(" "+nome+" [ "+qtd+" ] = $"+(new DecimalFormat("##.###").format(valorAtual*qtd)));
        cellView.add(cell1);

        vBoxRoot.getChildren().addAll(cellView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        getResponse();

        HashMap<String, Double> structure = CountAllMoney();

        coinValueNow();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        Iterator it = structure.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            String s = (String)pair.getKey();
            Double v = Double.parseDouble(pair.getValue().toString());
            pieChartData.add(new PieChart.Data(s,v));

        }

        graficoPizza.setData(pieChartData);
        graficoPizza.setStartAngle(90);

        labelMoneyTotal.setText("$ "+new DecimalFormat("##.##").format(moneyTotal));

    }
}