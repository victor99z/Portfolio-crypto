package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private PieChart GraficoPizza;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("BTC",40),
                new PieChart.Data("LTC",25),
                new PieChart.Data("ADA",25),
                new PieChart.Data("ETH",10));


        GraficoPizza.setData(pieChartData);
        GraficoPizza.setStartAngle(90);

    }
}