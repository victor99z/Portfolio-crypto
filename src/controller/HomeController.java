package controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import database.CoinInfo;
import database.UserCoin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController extends ControllerClassType implements Initializable {

    UserCoin c1;
    @FXML
    private PieChart GraficoPizza;

    @FXML
    private JFXButton btAddCoin;

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
            coinCtr.setUserCoinObject(c1);
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
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(System.getProperty("user.dir")+"\\portfolioDB.json");
            c1 = gson.fromJson(reader,UserCoin.class);
            reader.close();
        } catch (IOException e) {
            createJSON();
            e.printStackTrace();
        }
    }

    public boolean createJSON() {
        FileWriter write = null;
        try {
            write = new FileWriter(System.getProperty("user.dir")+"\\portfolioDB.json");
            write.close();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }

    }

    public boolean updateJSON(){
        Gson g = new Gson();
        try {
            String json = g.toJson(c1);
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
        try{
            double count = 0;
            HashMap<String, Double> coinPercentage  = new HashMap<>();
            for(CoinInfo dt : c1.ArrayCoins){
                count += dt.qtd * dt.buyPrice;
            }

            for(CoinInfo dt : c1.ArrayCoins){
                coinPercentage.put(dt.idCoin, (dt.buyPrice*dt.qtd)/count*100);
            }

            return coinPercentage;
        }catch (Exception e){
            return null;
        }
    }

    public void setGraficoMoedas(){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGraficoMoedas();
    }

}