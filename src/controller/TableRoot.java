package controller;

import api.ApiReader;
import api.DataArray;
import api.DataExchange;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public abstract class TableRoot {
    @FXML
    protected TableView<DataExchange> tvDados;
    protected DataArray dataArray;
    protected String url;

    public TableRoot(String url){
        setUrl(url);
    }

    public void getResponseFromAPI(String url) throws Exception {
        ApiReader response = new ApiReader(url);
        Gson gson = new Gson();
        this.dataArray = gson.fromJson(response.toString(),DataArray.class);
    }

    public abstract void initialize();

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }

}