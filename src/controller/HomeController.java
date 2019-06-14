package controller;

import com.google.gson.Gson;
import database.CoinInfo;
import database.UserCoin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    UserCoin c1;
    @FXML
    private PieChart GraficoPizza;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        HashMap<String, Double> structure = CountAllMoney();

        Iterator it = structure.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            String s = (String)pair.getKey();
            Double v = Double.parseDouble(pair.getValue().toString());

            pieChartData.add(new PieChart.Data(s,v));
        }


        GraficoPizza.setData(pieChartData);
        GraficoPizza.setStartAngle(90);

    }

}